package GestionProduit;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CategorieIHM extends Application{
	private Label titre;
	private Label labelId;
	private Label labelNom;

	private Label lb;
	private TextField textFieldId;
	private TextField textFieldNom;
	private TextField textFieldSearch;
	private BorderPane root;
	private BorderPane root2;
	private HBox header;
	private VBox right;
	private VBox left;
	private GridPane grid;
	private Button ajouter;
	private Button modifier;
	private Button supprimer;
	private TableView<Categorie>  table ;
	private TableColumn<Categorie,String> idCol ;
    private TableColumn<Categorie,String>  nomCol ;
    private CategorieDAO p=new CategorieDAOIMPL();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gestion des categories");
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1900);
		Stage stageTwo =new Stage();
		//Stage stageSupprimer=new Stage();
		initPanes();
		initelements();
		Scene scene=new Scene(root, 800,600);
		Scene scene2=new Scene(root2);
		stageTwo.setScene(scene2);
		menu m=new menu();
		//root2.getChildren().add(m.hboxreturn());
		header.getChildren().add(m.hboxreturn(primaryStage));
		left.getChildren().addAll(ajouter, modifier,supprimer);
		right.getChildren().add(textFieldSearch);
		right.getChildren().add(table);
		grid.add(labelId, 0, 0);
		grid.add(textFieldId, 1, 0);
		grid.add(labelNom, 0, 1);
		grid.add(textFieldNom, 1, 1);
		root.setTop(header);
		root.setLeft(grid);
		root.setRight(right);
		root.setCenter(left);
		root.setBottom(m.hboxreturn1());
		textFieldSearch.setOnKeyPressed((e)->{
			
			String key=textFieldSearch.getText();
			
				 List<Categorie> list=p.findAll(key);
			        final ObservableList<Categorie> data =FXCollections.observableArrayList( list );
			        table.setItems(data);
			  
	        
		});
		
		table.setOnMouseClicked(e->{
			Categorie p1=table.getSelectionModel().getSelectedItem();
			textFieldId.setText(p1.getId()+"");
			textFieldId.setDisable(true);
			textFieldNom.setText(p1.getNom());
		
		
			
	
		});
		
		modifier.setOnAction(e->{
			if(textFieldId.getText() !=" "  &&  textFieldNom.getText() !=" " ) {
				String Nom=textFieldNom.getText();
				int id=Integer.parseInt(textFieldId.getText());
			
				
				Categorie prod=new Categorie(id, Nom);
				p.update(prod);
				textFieldId.setText(" ");
				textFieldId.setDisable(false);
				textFieldNom.setText(" ");
			
				
				refresh();
				
				
				
			}
		});
		
		
		supprimer.setOnAction(e->{
			 Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprision Confirmation");
		      alert.setHeaderText("Vous etre sur que vous vollez supprimer le client: "+textFieldNom.getText()+"?");
		     
		 
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK){
		  		if(textFieldId.getText() !=" "  &&  textFieldNom.getText() !=" ") {
					String Nom=textFieldNom.getText();
					int id=Integer.parseInt(textFieldId.getText());
				
					Categorie prod=new Categorie(id, Nom);
					p.delete(prod);
					textFieldId.setText(" ");
					textFieldId.setDisable(false);
					textFieldNom.setText(" ");
				
					refresh();
					
					
					
				}
		    	} 
	
		});
			
			
			
		
		
		
		ajouter.setOnAction(e->{
		     
	              if(textFieldNom.getText().isEmpty()) {
	                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter votre nom de Categorie");
	                  return;
	              }
	              if(!isValidnumstring(textFieldNom.getText())) {
	                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " votre nom de Categorie n'est pas valid");
	                  return;
	              }
	              showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!",textFieldNom.getText()+ " est bien Ajouter ");

			String Nom=textFieldNom.getText();
			long id=p.getid();
	
			Categorie p=new Categorie(id, Nom);
			CategorieDAO pdao=new CategorieDAOIMPL();
			pdao.create(p);
			textFieldId.setText(p.getId()+1+" ");
			textFieldId.setDisable(true);
			textFieldNom.setText(" ");


		     refresh();
		   
		
		});
		
		
		scene.getStylesheets().add("mycss.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	  private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
    public static boolean isValidnumstring(String email)
    {
        String emailRegex = "[a-z A-Z 0-9]*";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	public void refresh() {
		List<Categorie> list=p.findAll();
        final ObservableList<Categorie> data =FXCollections.observableArrayList( list );
        table.setItems(data);
		
	}
	
	public void initPanes() {
		 root=new BorderPane();
		 root2=new BorderPane();

		 header=new HBox();
		header.setPadding(new Insets(30));
		 header.setAlignment(Pos.CENTER);
		 header.getStyleClass().add("header");
		
		 right=new VBox();
		 right.setSpacing(30);
		 right.setPadding(new Insets(20));
		 
		 left=new VBox();
		 left.getStyleClass().add("class-left");
		 left.setSpacing(30);
		 left.setPadding(new Insets(20));
		 
		 grid=new GridPane();
		 grid.getStyleClass().add("custom-pane-center");
		 grid.setPadding(new Insets(10));

		
	}
	public void initelements() {
		//initialiser labels
		titre=new Label("Gestion de categorie");
		labelId=new Label("Id");
		labelNom=new Label("Nom");

		labelId.getStyleClass().add("custom-label");
		labelNom.getStyleClass().add("custom-label");
	;
		lb=new Label();
		
		table=new TableView<Categorie>();
		idCol = new TableColumn<Categorie,String>("id");
        nomCol = new TableColumn<Categorie,String>("nom");
 
        
        table.getColumns().addAll(idCol, nomCol);
        
        
		refresh();

		
		//initialiser buttons
		ajouter=new Button("ajouter");
	    ajouter.getStyleClass().add("custom-button");
		modifier=new Button("modifier");
		modifier.getStyleClass().add("custom-button");
		supprimer=new Button("supprimer");
		supprimer.getStyleClass().add("custom-button");
		
		//initialiser textfields
		 textFieldSearch=new TextField();
		 
		 textFieldId=new TextField(p.getid()+"");
		 System.out.println(p.getid());
		 textFieldId.setDisable(true);
		 textFieldNom=new TextField();
	
		 textFieldId.getStyleClass().add("text-field ");
		 
		    //title.bind(titleTextField.textProperty());
        
		 
		 //init colum
		 idCol.setCellValueFactory(
				    new PropertyValueFactory<Categorie,String>("id"));
				nomCol.setCellValueFactory(
				    new PropertyValueFactory<Categorie, String>("nom"));
	
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}

