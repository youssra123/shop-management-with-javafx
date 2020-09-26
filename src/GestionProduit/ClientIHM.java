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

public class ClientIHM extends Application{
	private Label titre;
	private Label labelId;
	private Label labelNom;
	private Label labelPrenom;
	private Label labelTel;
	private Label labelAdress;
	private Label labelEmail;
	private Label lb;
	private TextField textFieldId;
	private TextField textFieldNom;
	private TextField textFieldPrenom;
	private TextField textFieldTel;
	private TextField textFieldAdress;
	private TextField textFieldEmail;
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
	private TableView<Client>  table ;
	private TableColumn<Client,String> idCol ;
    private TableColumn<Client,String>  nomCol ;
    private TableColumn<Client,String>  prenomCol ;
    private TableColumn<Client,String>  telCol ;
    private TableColumn<Client,String>  emailCol ;
    private TableColumn<Client,String>  adressCol ;
    private ClientDAO p=new ClientDAOIMPL();




	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gestion des client");
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1900);
		Stage stageTwo =new Stage();
		//Stage stageSupprimer=new Stage();
		initPanes();
		initelements();
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
		grid.add(labelNom, 0, 1);
		grid.add(textFieldNom, 1, 1);	
		grid.add(labelPrenom, 0, 2);
		grid.add(textFieldPrenom, 1, 2);
		grid.add(labelTel, 0, 3);
		grid.add(textFieldTel, 1, 3);
		grid.add(labelEmail, 0, 4);
		grid.add(textFieldEmail, 1, 4);
		grid.add(labelAdress, 0, 5);
		grid.add(textFieldAdress, 1, 5);
		root.setTop(header);
		root.setLeft(grid);
		root.setRight(right);
		root.setCenter(left);
		root.setBottom(m.hboxreturn1());
		
		textFieldSearch.setOnKeyPressed((e)->{
			
			String key=textFieldSearch.getText();
			
				 List<Client> list=p.findAll(key);
			        final ObservableList<Client> data =FXCollections.observableArrayList( list );
			        table.setItems(data);
			  
	        
		});
		
		table.setOnMouseClicked(e->{
			Client p1=table.getSelectionModel().getSelectedItem();
			textFieldId.setText(p1.getId()+"");
			textFieldId.setDisable(true);
			textFieldNom.setText(p1.getNom());
			textFieldPrenom.setText(p1.getPrenom()+"");
			textFieldTel.setText(p1.getTel()+"");
			textFieldEmail.setText(p1.getEmail()+"");
			textFieldAdress.setText(p1.getAdresse()+"");
		
			
	
		});
		
		modifier.setOnAction(e->{
			if(textFieldId.getText() !=" "  &&  textFieldNom.getText() !=" " && textFieldTel.getText() !=" "&& textFieldPrenom.getText() !=" "&& textFieldAdress.getText() !=" "&& textFieldEmail.getText() !=" ") {
				String Nom=textFieldNom.getText();
				int id=Integer.parseInt(textFieldId.getText());
				String tel=textFieldTel.getText();
				String Prenom=textFieldPrenom.getText();
				String email=textFieldEmail.getText();
				String adress=textFieldAdress.getText();
				
				Client prod=new Client(id, Nom,Prenom,tel,email,adress);
				p.update(prod);
				textFieldId.setText(p.getid()+1+" ");
				textFieldId.setDisable(true);
				textFieldNom.setText(" ");
				textFieldPrenom.setText(" ");
				textFieldTel.setText(" ");
				textFieldEmail.setText(" ");
				textFieldAdress.setText(" ");
				
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
		  		if(textFieldId.getText() !=" "  &&  textFieldNom.getText() !=" " && textFieldTel.getText() !=" " && textFieldPrenom.getText() !=" "&& textFieldAdress.getText() !=" "&& textFieldEmail.getText() !=" ") {
					String Nom=textFieldNom.getText();
					int id=Integer.parseInt(textFieldId.getText());
					String tel=textFieldTel.getText();
					String Prenom=textFieldPrenom.getText();
					String email=textFieldEmail.getText();
					String adress=textFieldAdress.getText();
					Client prod=new Client(id, Nom,Prenom,tel,email,adress);
					p.delete(prod);
					textFieldId.setText(p.getid()+1+" ");
					textFieldId.setDisable(true);
					textFieldNom.setText(" ");
					textFieldPrenom.setText(" ");
					textFieldTel.setText(" ");
					textFieldEmail.setText(" ");
					textFieldAdress.setText(" ");
					refresh();
					
					
					
				}
		    	} 
	
		});
			
			
			
		
		
		
		ajouter.setOnAction(e->{
			  if(textFieldNom.getText().isEmpty()) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter votre nom");
                  return;
              }
			  if(!isValidstring(textFieldNom.getText())) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " votre Nom n'est pas valid");
                  return;
              }
              if(textFieldPrenom.getText().isEmpty()) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter votre prenom");
                  return;
              }
              if(!isValidstring(textFieldPrenom.getText())) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " votre Prenom n'est pas valid");
                  return;
              }
              if(textFieldTel.getText().isEmpty()) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter votre numero de telephone");
                  return;
              }
              if(!isValidnum(textFieldTel.getText())) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " votre numero de telephone n'est pas valid");
                  return;
              }
              if(textFieldEmail.getText().isEmpty()) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter votre Email");
                  return;
              }
              if(!isValid(textFieldEmail.getText())) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " votre Email n'est pas valid");
                  return;
              }
              if(textFieldAdress.getText().isEmpty()) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait  enter votre Adress");
                  return;
              }
              if(!isValidnumstring(textFieldAdress.getText())) {
                  showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " votre Adress n'est pas valid");
                  return;
              }
              showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!",textFieldNom.getText()+ " est bien Ajouter ");

			String Nom=textFieldNom.getText();
			long id=p.getid();
			String Prenom=textFieldPrenom.getText();
			String tel=textFieldTel.getText();
			String email=textFieldEmail.getText();
			String adress=textFieldAdress.getText();
			Client p=new Client(id, Nom,Prenom,tel,email,adress);
			ClientDAO pdao=new ClientDAOIMPL();
			pdao.create(p);
			textFieldId.setText(p.getId()+1+" ");
			textFieldId.setDisable(true);
			textFieldNom.setText(" ");
			textFieldPrenom.setText(" ");
			textFieldTel.setText(" ");
			textFieldEmail.setText(" ");
			textFieldAdress.setText(" ");

		     refresh();
		   
		
		});
		
		
		scene.getStylesheets().add("mycss.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
   public static boolean isValidnum(String email)
    {
        String emailRegex = "[0-9]*";

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
		List<Client> list=p.findAll();
        final ObservableList<Client> data =FXCollections.observableArrayList( list );
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
		titre=new Label("Gestion de clients");
		labelId=new Label("Id");
		labelNom=new Label("Nom");
		labelPrenom=new Label("Prenom");
		labelTel=new Label("Tel");
		labelAdress=new Label("Adress");
		labelEmail=new Label("Email");
		labelId.getStyleClass().add("custom-label");
		labelNom.getStyleClass().add("custom-label");
		labelPrenom.getStyleClass().add("custom-label");
		labelTel.getStyleClass().add("custom-label");
		labelEmail.getStyleClass().add("custom-label");
		labelAdress.getStyleClass().add("custom-label");
		lb=new Label();
		
		table=new TableView<Client>();
		idCol = new TableColumn<Client,String>("id");
        nomCol = new TableColumn<Client,String>("nom");
        prenomCol = new TableColumn<Client,String>("prenom");
        telCol = new TableColumn<Client,String>("tel");
        emailCol = new TableColumn<Client,String>("email");
        adressCol = new TableColumn<Client,String>("adresse");
        
        table.getColumns().addAll(idCol, nomCol, prenomCol,telCol,emailCol,adressCol);
        
        
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
		 textFieldTel=new TextField();
		 textFieldPrenom=new TextField();
		 textFieldEmail=new TextField();
		 textFieldAdress=new TextField();
		 textFieldId.getStyleClass().add("text-field ");
		 
		    //title.bind(titleTextField.textProperty());
        
		 
		 //init colum
		 idCol.setCellValueFactory(
				    new PropertyValueFactory<Client,String>("id"));
				nomCol.setCellValueFactory(
				    new PropertyValueFactory<Client, String>("nom"));
				prenomCol.setCellValueFactory(
				    new PropertyValueFactory<Client,String>("prenom"));
				telCol.setCellValueFactory(
					    new PropertyValueFactory<Client,String>("tel"));
				emailCol.setCellValueFactory(
					    new PropertyValueFactory<Client,String>("email"));
				adressCol.setCellValueFactory(
					    new PropertyValueFactory<Client,String>("adresse"));
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}

