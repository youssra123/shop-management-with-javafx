package GestionProduit;
import java.util.Iterator;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
public class ProduitIHM extends Application{
	private Label titre;
	private Label labelId;
	private Label labelDesignation;
	private Label labelPrixV;
	private Label labelPrixA;
	private Label lb;
	private TextField textFieldId;
	private TextField textFieldDesignation;
	private TextField textFieldPrixV;
	private TextField textFieldPrixA;
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
	private TableView<Produit>  table ;
	private TableColumn<Produit,String> idCol ;
    private TableColumn<Produit,String>  desCol ;
    private TableColumn<Produit,String>  prixColA ;
    private TableColumn<Produit,String>  prixColV ;
    private TableColumn<Produit,String>  categorieColV ;
    private ProduitDAO p=new ProduitDAOIMPL();
    private CategorieDAO ca=new CategorieDAOIMPL();
    private ComboBox<String> comboboxca;
    private Label categorie;
    private List<Categorie> listca=ca.findAll();
    private int idca;
    final ObservableList option =FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gestion des produits");
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1900);
		Stage stageTwo =new Stage();
		//Stage stageSupprimer=new Stage();
		initPanes();
		initElements();
		Scene scene=new Scene(root, 800,600);
		Scene scene2=new Scene(root2, 400,300);
		stageTwo.setScene(scene2);
		menu m=new menu();
		header.getChildren().add(m.hboxreturn(primaryStage));
		left.getChildren().addAll(ajouter, modifier,supprimer);
		right.getChildren().add(textFieldSearch);
		right.getChildren().add(table);
		grid.add(labelId, 0, 0);
		grid.add(textFieldId, 1, 0);
		grid.add(labelDesignation, 0, 1);
		grid.add(textFieldDesignation, 1, 1);	
		grid.add(labelPrixA, 0, 2);
		grid.add(textFieldPrixA, 1, 2);
		grid.add(labelPrixV, 0, 3);
		grid.add(textFieldPrixV, 1, 3);
		grid.add(categorie, 0, 4);
		grid.add(comboboxca, 1, 4);
		root.setTop(header);
		root.setLeft(grid);
		root.setRight(right);
		root.setCenter(left);
		root.setBottom(m.hboxreturn1());
	
		
		textFieldSearch.setOnKeyPressed((e)->{
			
			String key=textFieldSearch.getText();
			
				 List<Produit> list=p.findAll(key);
			        final ObservableList<Produit> data =FXCollections.observableArrayList( list );
			        table.setItems(data);
			  
	        
		});
		comboboxca.setOnKeyPressed((e)->{
			idca=comboboxca.getSelectionModel().getSelectedIndex();
		
		});
		table.setOnMouseClicked(e->{
			Produit p1=table.getSelectionModel().getSelectedItem();
			textFieldId.setText(p1.getId()+"");
			textFieldId.setDisable(true);
			textFieldDesignation.setText(p1.getDesigniation());
			textFieldPrixA.setText(p1.getPrix_A()+"");
			textFieldPrixV.setText(p1.getPrix_V()+"");
			comboboxca.setValue(p1.getCa().getNom()+"");
			
	
		});
		
		modifier.setOnAction(e->{
			if(textFieldId.getText() !=" "  &&  textFieldDesignation.getText() !=" " && textFieldPrixA.getText() !=" "&& textFieldPrixV.getText() !=" ") {
				String designation=textFieldDesignation.getText();
				int id=Integer.parseInt(textFieldId.getText());
				double prixA=Double.parseDouble(textFieldPrixA.getText());
				double prixV=Double.parseDouble(textFieldPrixV.getText());
				String cat=comboboxca.getValue();
			
				Produit prod=new Produit(id, designation, prixA,prixV,ca.findAllcat(comboboxca.getValue()));
				p.update(prod);
				textFieldId.setText(p.getid()+"");
				textFieldId.setDisable(true);
				textFieldDesignation.setText(" ");
				textFieldPrixA.setText(" ");
				textFieldPrixV.setText(" ");
				refresh();
				
				
				
			}
		});
		
		
		supprimer.setOnAction(e->{
			 Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprision Confirmation");
		      alert.setHeaderText("Vous etre sur que vous vollez supprimer le produit: "+textFieldDesignation.getText()+"?");
		     
		 
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK){
		  		if(textFieldId.getText() !=" "  &&  textFieldDesignation.getText() !=" " && textFieldPrixA.getText() !=" " && textFieldPrixV.getText() !=" ") {
					String designation=textFieldDesignation.getText();
					int id=Integer.parseInt(textFieldId.getText());
					double prixA=Double.parseDouble(textFieldPrixA.getText());
					double prixV=Double.parseDouble(textFieldPrixV.getText());
					Produit prod=new Produit(id, designation, prixA,prixV,ca.findAllcat(comboboxca.getValue()));
					p.delete(prod);
					textFieldId.setText(p.getid()+"");
					textFieldId.setDisable(true);
					textFieldDesignation.setText(" ");
					textFieldPrixA.setText(" ");
					textFieldPrixV.setText(" ");
					refresh();
					
					
					
				}
		    	} 
	
		});
		
		
		ajouter.setOnAction(e->{
		    if(textFieldDesignation.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter la Designation");
                return;
            }
            if(!isValidnumstring(textFieldDesignation.getText())) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " la Designation n'est pas valid");
                return;
            }
            if(textFieldPrixA.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter le Prix d'Achat");
                return;
            }
            if(!isValiddouble(textFieldPrixA.getText())) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " le Prix d'Achat n'est pas valid");
                return;
            }
            if(textFieldPrixV.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter le Prix de Vente");
                return;
            }
            if(!isValiddouble(textFieldPrixV.getText())) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "le Prix de Vente n'est pas valid");
                return;
            }
            if(comboboxca.getValue()==null) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait choisi la categorie");
                return;
            }
         
            showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!",textFieldDesignation.getText()+ " est bien Ajouter ");

			String designation=textFieldDesignation.getText();
			long id=p.getid();
			double prixA=Double.parseDouble(textFieldPrixA.getText());
			double prixV=Double.parseDouble(textFieldPrixV.getText());
	System.out.println(ca.findAllcat(comboboxca.getValue()));
			Produit p=new Produit(id, designation, prixA,prixV,ca.findAllcat(comboboxca.getValue()));
			ProduitDAO pdao=new ProduitDAOIMPL();
			System.out.println(p);
			pdao.create(p);
			
			textFieldId.setText(p.getId()+1+"");
			textFieldId.setDisable(true);
			textFieldDesignation.setText(" ");
			textFieldPrixA.setText(" ");
			textFieldPrixV.setText(" ");

		     refresh();
		
		});
		
		
		scene.getStylesheets().add("mycss.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	 public static boolean isValidnum(String email)
	    {
	        String emailRegex = "[0-9]*";

	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
	    }
	 public static boolean isValiddouble(String email)
	    {
	        String emailRegex = "[0-9]*.?[0-9]*";

	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
	    }
	public static boolean isValidstring(String email)
	    {
	        String emailRegex = "[a-zA-Z]*";

	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
	    }
	    public static boolean isValidnumstring(String email)
	    {
	        String emailRegex = "[a-z A-Z 0-9]*";

	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
	    }
		  private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		        Alert alert = new Alert(alertType);
		        alert.setTitle(title);
		        alert.setHeaderText(null);
		        alert.setContentText(message);
		        alert.initOwner(owner);
		        alert.show();
		    }
		  
	public void refresh() {
		List<Produit> list=p.findAll();
        final ObservableList<Produit> data =FXCollections.observableArrayList( list );
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
	public void initElements() {
		//initialiser labels
		titre=new Label("Gestion de produits");
		labelId=new Label("Id");
		labelDesignation=new Label("Designation");
		labelPrixA=new Label("PrixA");
		labelPrixV=new Label("PrixV");
		categorie=new Label("Categorie");
		categorie.getStyleClass().add("custom-label");
		labelId.getStyleClass().add("custom-label");
		labelDesignation.getStyleClass().add("custom-label");
		labelPrixA.getStyleClass().add("custom-label");
		labelPrixV.getStyleClass().add("custom-label");
		lb=new Label();
		
		table=new TableView<Produit>();
		idCol = new TableColumn<Produit,String>("id");
        desCol = new TableColumn<Produit,String>("designiation");
        prixColA = new TableColumn<Produit,String>("Prix_A");
        prixColV = new TableColumn<Produit,String>("Prix_V");
        categorieColV = new TableColumn<Produit,String>("catt");
        comboboxca=new ComboBox<String>(option);        
        table.getColumns().addAll(idCol, desCol, prixColA,prixColV,categorieColV);
        
        
		refresh();

		
		//initialiser buttons
		ajouter=new Button("ajouter");
	    ajouter.getStyleClass().add("custom-button");
		modifier=new Button("modifier");
		modifier.getStyleClass().add("custom-button");
		supprimer=new Button("supprimer");
		supprimer.getStyleClass().add("custom-button");
		textFieldSearch=new TextField();
		//initialiser textfields
		textFieldId=new TextField(p.getid()+" ");
		textFieldId.setDisable(true);
		System.out.println(p.getid());
		
		 textFieldDesignation=new TextField();
		 textFieldPrixA=new TextField();
		 textFieldPrixV=new TextField();
		 for(int i=0;i<listca.size();i++) {
			 option.add(listca.get(i).getNom());
			 
		 }
		 comboboxca=new ComboBox(option);
		 comboboxca.getStyleClass().add("text-field ");
		 System.out.println(option);
		// System.out.println(listca);
		
		
		 textFieldId.getStyleClass().add("text-field ");
		 
		    //title.bind(titleTextField.textProperty());
        
		 
		 //init colum
		 idCol.setCellValueFactory(
				    new PropertyValueFactory<Produit,String>("id"));
				desCol.setCellValueFactory(
				    new PropertyValueFactory<Produit, String>("designiation"));
				prixColA.setCellValueFactory(
				    new PropertyValueFactory<Produit,String>("prix_A"));
				prixColV.setCellValueFactory(
					    new PropertyValueFactory<Produit,String>("prix_V"));
				categorieColV.setCellValueFactory(
					    new PropertyValueFactory<Produit,String>("catt"));
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
