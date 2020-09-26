package GestionProduit;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import banque.ServeurIHM;
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

public class VenteIHM extends Application{
	
	ProduitDAOIMPL imp=new ProduitDAOIMPL();
	private Label titre;
	private Label labelId;
	private Label labelqt;
	private Label labelProduit;
	private Label labelsubTotal;
	private Label labelIdv;
	private Label labeldate;
	private Label labelClient;
	private Label lb;
	private TextField textFieldId;
	private TextField textFieldqt;
	private TextField textFieldsubTotal;
	private TextField textFieldProduit;
	private TextField textFieldIdv;
	private TextField textFieldate;
	private TextField textFieldcli;
	private TextField textFieldSearch;
	private TextField textFieldSearchc;
	private TextField textFieldSearchv;
	private TextField textFieldSearchp;
	private BorderPane root;
	private BorderPane root2;

	private HBox header;
	private VBox right;
	private VBox left;
	private GridPane grid;
	private GridPane grid1;
	private GridPane grid2;
	private Button ajouter;
	private Button ajouterv;
	private Button modifier;
	private Button supprimer;
	private Button supprimerv;
	private TableView<LigneCommande> table;
	private TableColumn<LigneCommande,String> idCol ;
    private TableColumn<LigneCommande,String>  desCol ;
    private TableColumn<LigneCommande,String>  prixColA ;
    private TableColumn<LigneCommande,String>  prixColV ;
    private TableView<Produit>  tablep ;
	private TableColumn<Produit,String> idColp ;
    private TableColumn<Produit,String>  desColp ;
    private TableColumn<Produit,String>  prixColAp ;
    private TableColumn<Produit,String>  prixColVp ;
    private TableColumn<Produit,String>  categorieColVp ;
    private TableView<Vente>  tablev ;
   	private TableColumn<Vente,String> idColv ;
       private TableColumn<Vente,String>  totalCol ;
       private TableColumn<Vente,String>  dateCol ;
       private TableColumn<Vente,String>  cliCol ;
    private TableView<Client>  tablec ;
 	private TableColumn<Client,String> idColc ;
     private TableColumn<Client,String> nomCol ;
     private TableColumn<Client,String>  prenomCol ;
     private TableColumn<Client,String>  telCol ;
     private TableColumn<Client,String>  emailCol ;
     private TableColumn<Client,String>  adresseCol ;
    private LigneCommandeDAO l=new LigneCommandeDAOIMP();
    private VenteDAO v=new VenteDAOIMP();
    private ProduitDAO p=new ProduitDAOIMPL();
    private ClientDAOIMPL c=new ClientDAOIMPL();
    private long idl;
    private long idp;
    private long idc;
    private long idv;
    private List<LigneCommande> listlc=new ArrayList<LigneCommande>();
    private boolean bo=false;
    private long u=0;
    private HBox hbox1,hbox2;
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gestion des ligne de Commande");
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1900);
		Stage stageTwo =new Stage();
		menu m=new menu();
		//Stage stageSupprimer=new Stage();
		initPanes();
		initElements();
		Scene scene=new Scene(root, 1000,600);
		Scene scene2=new Scene(root2, 600,300);
		
		stageTwo.setScene(scene2);
		
		header.getChildren().add(m.hboxreturn(primaryStage));
		//left.getChildren().addAll(ajouter, modifier,supprimer);
		
		right.getChildren().add(textFieldSearchv);
		right.getChildren().add(tablev);
		grid.add(labelId, 0, 0);
		grid.add(textFieldId, 1, 0);
		grid.add(labelqt, 0, 1);
		grid.add(textFieldqt, 1, 1);	
		grid.add(labelProduit, 0, 2);
		//grid.add(textFieldProduit, 1, 2);
		grid.add(textFieldSearchp, 1, 2);
		grid.add(tablep, 1, 4);
		
		/*grid2.add(ajouter, 0, 0);
		grid2.add(modifier, 1, 0);
		grid2.add(supprimer, 2, 0);*/
		hbox1.getChildren().addAll(ajouter,modifier,supprimer);
		grid.add(hbox1, 1, 5);
		grid.add(textFieldSearch, 1, 6);
		grid.add(table, 1, 7);
		hbox2.getChildren().addAll(ajouterv,supprimerv);
		grid.add(hbox2, 1, 8);
		//grid.add(supprimerv, 2, 8);
		grid1.add(labelIdv, 0, 0);
		grid1.add(textFieldIdv, 1, 0);
		grid1.add(labeldate, 0, 1);
		grid1.add(textFieldate, 1, 1);	
		grid1.add(labelClient, 0, 2);
		grid1.add(textFieldSearchc, 1, 2);
	//	grid.add(textFieldSearchp, 0, 3);
		grid1.add(tablec, 1, 4);
		root.setTop(header);
		//root.setLeft(left);
		root.setLeft(grid1);
		root.setRight(right);
		root.setCenter(grid);
		root.setBottom(m.hboxreturn1());
		textFieldSearch.setOnKeyPressed((e)->{
			
			String key=textFieldSearch.getText();
			
				/* List<LigneCommande> list=listlc.findAll(key);
			        final ObservableList<LigneCommande> data =FXCollections.observableArrayList( list );
			        table.setItems(data);*/
			  
	        
		});
textFieldSearchv.setOnKeyPressed((e)->{
			
			String key=textFieldSearchv.getText();
			
				 List<Vente> list=v.findAll(key);
			        final ObservableList<Vente> data =FXCollections.observableArrayList( list );
			        tablev.setItems(data);
			  
	        
		});
textFieldSearchc.setOnKeyPressed((e)->{
	
	String key=textFieldSearchc.getText();
	
		 List<Client> list=c.findAll(key);
	        final ObservableList<Client> data =FXCollections.observableArrayList( list );
	        tablec.setItems(data);
	  
    
});
		
		textFieldSearchp.setOnKeyPressed((e)->{
			
			String key=textFieldSearchp.getText();
			
				 List<Produit> list=p.findAll(key);
			        final ObservableList<Produit> data =FXCollections.observableArrayList(list);
			        tablep.setItems(data);
			  
	        
		});
		
		table.setOnMouseClicked(e->{
			LigneCommande p1=table.getSelectionModel().getSelectedItem();
			textFieldId.setText(p1.getId()+"");
			textFieldId.setDisable(true);
			textFieldqt.setText(p1.getQt()+"");
			textFieldSearchp.setText(p1.getP().getDesigniation()+"");
			idl=p1.getP().getId();
			bo=true;
			
			
	
		});
		tablev.setOnMouseClicked(e->{
			Vente p1=tablev.getSelectionModel().getSelectedItem();
			textFieldIdv.setText(p1.getId()+"");
			textFieldIdv.setDisable(true);
			textFieldate.setText(p1.getDate()+"");
			textFieldSearchc.setText(p1.getC().getNom()+"");
			listlc=l.findAllc(p1.getId());
			System.out.println(listlc);
			idc=p1.getC().getId();
			refresh();
			
		});
		
		tablep.setOnMouseClicked(e->{
			Produit p1=tablep.getSelectionModel().getSelectedItem();
			textFieldSearchp.setText(p1.getDesigniation());
			idp=p1.getId();
			
		});
		tablec.setOnMouseClicked(e->{
			Client p1=tablec.getSelectionModel().getSelectedItem();
			textFieldSearchc.setText(p1.getNom());
			idc=p1.getId();
		
		});
		supprimerv.setOnAction(e->{
			 Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprision Confirmation");
		      alert.setHeaderText("Vous etre sur que vous vollez supprimer la ligne de commande: "+textFieldIdv.getText()+"?");
		     
		 
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK){
		  		if(textFieldIdv.getText() !=" "  &&  textFieldcli.getText() !=" " && textFieldate.getText() !=" " ) {
		  			int id=Integer.parseInt(textFieldIdv.getText());
					String date=textFieldate.getText();
					Client cli=c.find(idc);
					Vente prod=new Vente(id, date, cli);
					v.delete(prod);
					textFieldIdv.setText(v.getid()+"");
					textFieldIdv.setDisable(true);
					textFieldcli.setText("");
					textFieldate.setText("");
					
					refresh1();
					
					
					
				}
		    	} 
	
		});
		supprimer.setOnAction(e->{
			 Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprision Confirmation");
		      alert.setHeaderText("Vous etre sur que vous vollez supprimer la ligne de commande: "+textFieldId.getText()+"?");
		     
		 
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK){
		  		if(textFieldId.getText() !=" "  &&  textFieldProduit.getText() !=" " && textFieldqt.getText() !=" " ) {
		  			int id=Integer.parseInt(textFieldId.getText());
		  			for(int i=0;i<listlc.size();i++) {
		  				if(listlc.get(i).getId()==id) {
		  				if(l.find(listlc.get(i).getId())==null) {
		  					listlc.remove(i);
						}else {
							l.delete(listlc.get(i));
						}
		  				
		  					
		  				}}
					/*int qt=Integer.parseInt(textFieldqt.getText());
					Produit produit=imp.find(idl);
					
					LigneCommande prod=new LigneCommande(id, qt, produit,v.find(v.getid()));
					l.delete(prod);*/
					textFieldId.setText(l.getid()+"");
					textFieldId.setDisable(true);
					textFieldqt.setText("");
					textFieldProduit.setText("");
					
					refresh();
					
					
					
				}
		    	} 
	
		});
		ajouter.setOnAction(e->{
			 if(textFieldSearchp.getText().isEmpty()) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait veillez choisi un produit");
                 return;
             }
       
        	 if(textFieldqt.getText().isEmpty()) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter la quantite");
                 return;
             }
         
             if(!isValidnum(textFieldqt.getText())) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " la quantite n'est pas valid");
                 return;
             }
          
             showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!", "la ligne de vente est bien Ajouter ");

		    int id1=Integer.parseInt(textFieldId.getText());
				int qt=Integer.parseInt(textFieldqt.getText());
				Produit produit=imp.find(idp);
				LigneCommande prod=new LigneCommande(id1, qt, produit);
				listlc=verif(listlc, prod);
				refresh();
				/*System.out.println(p);
				System.out.println(produit.toString());
				LigneCommandeDAO pdao=new LigneCommandeDAOIMP();
				pdao.create(p);*/
				textFieldId.setText(l.getid()+u+"");
				textFieldId.setDisable(true);
				textFieldqt.setText("");
				textFieldProduit.setText("");
				refresh();
			
		
		});
		modifier.setOnAction(e->{
			if(textFieldId.getText() !=" "  &&  textFieldqt.getText() !=" " && textFieldProduit.getText() !=" ") {
			    int id1=Integer.parseInt(textFieldId.getText());
							int qt=Integer.parseInt(textFieldqt.getText());
							Produit produit=imp.find(idp);
				LigneCommande prod=new LigneCommande(id1, qt, produit);
				for(int i=0;i<listlc.size();i++) {
					if(listlc.get(i).getId()==prod.getId()) {
				listlc.get(i).setP(prod.getP());
				listlc.get(i).setQt(prod.getQt());
				listlc.get(i).setSubTotal(prod.getSubTotal());
				listlc=listlc;
				refresh();
				}}
				
				//l.update(prod);
				textFieldId.setText(l.getid()+"");
				textFieldId.setDisable(true);
				textFieldqt.setText("");
				textFieldProduit.setText("");
				refresh();
				
				
				
			}
			refresh();
		});
			
		ajouterv.setOnAction(e->{
			 if(textFieldSearchc.getText().isEmpty()) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait veillez choisi un client");
                 return;
             }
       
        	 if(textFieldate.getText().isEmpty()) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter la date exmple:yyyy/mm/dd");
                 return;
             }
         
             if(!isValiddate(textFieldate.getText())) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " la date n'est pas valid exmple:yyyy/mm/dd");
                 return;
             }
          
             showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!", "la vente est bien Ajouter ");

			if(textFieldId.getText() !=" "  &&  textFieldqt.getText() !=" " ) {
				int idv=Integer.parseInt(textFieldIdv.getText());
				String date1=textFieldate.getText();
				Client client=c.find(idc);
				double s=0;
				for(int j=0;j<listlc.size();j++) {
					s=s+listlc.get(j).getSubTotal();
				}
				Vente prod=new Vente(idv,s, date1,client);
				if(v.find(idv)==null) {
					v.create(prod);
					for(int j=0;j<listlc.size();j++) {
						System.out.println("idv="+idv);
						listlc.get(j).setV(prod);
						System.out.println(listlc.get(j));
						l.create(listlc.get(j));
					}
				}else {
					v.update(prod);
					for(int j=0;j<listlc.size();j++) {
						System.out.println("idv="+idv);
						listlc.get(j).setV(prod);
						System.out.println(listlc.get(j));
						if(l.find(listlc.get(j).getId())==null) {
							l.create(listlc.get(j));
						}else {
							l.update(listlc.get(j));
						}
					
					}
				}
				textFieldIdv.setText(v.getid()+"");
				textFieldIdv.setDisable(true);
				textFieldate.setText("");
				textFieldcli.setText("");
				listlc=new ArrayList<LigneCommande>();
				refresh1();
				refresh();
				
				
				
			}
		});
	

		
		
		scene.getStylesheets().add("mycss.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

    public static boolean isValiddate(String email)
    {
        String emailRegex = "^(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$";

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
		//List<LigneCommande> list=l.findAll();
        final ObservableList<LigneCommande> data =FXCollections.observableArrayList(listlc);
        table.setItems(data);
     
		
	}
	public void refreshlc(List<LigneCommande>listlc) {
		//List<LigneCommande> list=l.findAll();
        final ObservableList<LigneCommande> data =FXCollections.observableArrayList(listlc);
        table.setItems(data);
     
		
	}
	public void refresh1() {
		List<Vente> list=v.findAll();
        final ObservableList<Vente> data =FXCollections.observableArrayList(list);
        tablev.setItems(data);
     
		
	}
	public void refreshp() {
		List<Produit> list=p.findAll();
        final ObservableList<Produit> data =FXCollections.observableArrayList(list);
        tablep.setItems(data);
     
		
	}
	public void refreshc() {
		List<Client> list=c.findAll();
        final ObservableList<Client> data =FXCollections.observableArrayList(list);
        tablec.setItems(data);
     
		
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
		 grid1=new GridPane();
		 grid2=new GridPane();
		 grid.getStyleClass().add("custom-pane-center");
		 grid.setPadding(new Insets(10));

		
	}
	public List<LigneCommande> verif(List<LigneCommande> listlc,LigneCommande prod){
		for(int i=0;i<listlc.size();i++) {
		
			if(listlc.get(i).getP().getId()==prod.getP().getId()) {
				listlc.get(i).setQt(listlc.get(i).getQt()+prod.getQt());
				listlc.get(i).setSubTotal(listlc.get(i).getSubTotal()+prod.getSubTotal());
				System.out.println("");
				System.out.println(listlc.get(i));
				this.listlc=listlc;
				refreshlc(listlc);
				return listlc;
			}
			
			}
	
		//if(listlc.get(i).getId()!=prod.getId() && listlc.get(i).getP().getId()!=prod.getP().getId()) {
				listlc.add(prod);
				System.out.println("");
				refresh();
				//System.out.println(listlc.get(i));
				u=u+1;
				return listlc;
		
			//}
	//	}
		/*for(int i=0;i<listlc.size();i++) {
			if(listlc.get(i).getId()==prod.getId()) {
				listlc.get(i).setP(prod.getP());
				listlc.get(i).setQt(prod.getQt());
				listlc.get(i).setSubTotal(prod.getSubTotal());
				return listlc;
			}
			if(listlc.get(i).getP().getId()==prod.getP().getId()) {
				listlc.get(i).setQt(listlc.get(i).getQt()+prod.getQt());
				listlc.get(i).setSubTotal(listlc.get(i).getSubTotal()+prod.getSubTotal());
				return listlc;
			}
			
			if(listlc.get(i).getId()!=prod.getId() && listlc.get(i).getP().getId()!=prod.getP().getId()) {
				listlc.add(prod);
				u=u+1;
				return listlc;
			
			}
		}*/

	}
	public void initElements() {
		//initialiser labels
		hbox1=new HBox();
		hbox2=new HBox();
		titre=new Label("Gestion de produits");
		labelId=new Label("Id");
		labelqt=new Label("Qt");
		labelProduit=new Label("Produit");
		labelId.getStyleClass().add("custom-label");
		labelqt.getStyleClass().add("custom-label");
		labelProduit.getStyleClass().add("custom-label");
		labelIdv=new Label("ID_vente");
		labeldate=new Label("Date");
		labelClient=new Label("Client");
		labelIdv.getStyleClass().add("custom-label");
		labeldate.getStyleClass().add("custom-label");
		labelClient.getStyleClass().add("custom-label");
		labelId.getStyleClass().add("custom-label");
		labelqt.getStyleClass().add("custom-label");
		labelProduit.getStyleClass().add("custom-label");
		lb=new Label();
		tablep=new TableView<Produit>();
		idColp = new TableColumn<Produit,String>("id");
        desColp = new TableColumn<Produit,String>("designiation");
        prixColAp = new TableColumn<Produit,String>("Prix_A");
        prixColVp = new TableColumn<Produit,String>("Prix_V");
        categorieColVp = new TableColumn<Produit,String>("categorie");
        tablep.getColumns().addAll(idColp, desColp, prixColAp,prixColVp,categorieColVp);
        
        tablec=new TableView<Client>();
		idColc = new TableColumn<Client,String>("id");
		nomCol = new TableColumn<Client,String>("nom");
        prenomCol = new TableColumn<Client,String>("prenom");
        telCol = new TableColumn<Client,String>("tel");
        emailCol = new TableColumn<Client,String>("email");
        adresseCol = new TableColumn<Client,String>("adress");
        
        tablec.getColumns().addAll(idColc, nomCol, prenomCol,telCol,emailCol,adresseCol);
        
        
       // tablep.getColumns().addAll(idColp, desColp, prixColAp,prixColVp);
		table=new TableView<LigneCommande>();
		idCol = new TableColumn<LigneCommande,String>("id");
        desCol = new TableColumn<LigneCommande,String>("qt");
        prixColA = new TableColumn<LigneCommande,String>("SubTotal");
        prixColV = new TableColumn<LigneCommande,String>("Produit");
        
        table.getColumns().addAll(idCol, desCol, prixColA,prixColV);
        
        tablev=new TableView<Vente>();
		idColv = new TableColumn<Vente,String>("id");
        totalCol = new TableColumn<Vente,String>("total");
        dateCol = new TableColumn<Vente,String>("date");
        cliCol = new TableColumn<Vente,String>("c");
        
        tablev.getColumns().addAll(idColv, totalCol, dateCol,cliCol);
        
		
        refresh();
        refresh1();
        refreshc();
        refreshp();
		
		//initialiser buttons
		ajouter=new Button("Enregister ligne de commande");
	    ajouter.getStyleClass().add("custom-button");
	    ajouterv=new Button("Enregister vente");
	    ajouterv.getStyleClass().add("custom-button");
		modifier=new Button("modifier");
		modifier.getStyleClass().add("custom-button");
		supprimer=new Button("supprimer");
		supprimer.getStyleClass().add("custom-button");
		supprimerv=new Button("supprimer");
		supprimerv.getStyleClass().add("custom-button");
		//initialiser textfields
		 textFieldSearch=new TextField();
		 textFieldSearchp=new TextField();
		 textFieldSearchv=new TextField();
		 textFieldSearchc=new TextField();
		 textFieldId=new TextField(l.getid()+"");
		 textFieldId.setDisable(true);
		 textFieldqt=new TextField();
		 textFieldProduit=new TextField();
		 textFieldIdv=new TextField(v.getid()+"");
		 textFieldIdv.setDisable(true);
		 textFieldate=new TextField();
		 textFieldcli=new TextField();
		 
		 textFieldId.getStyleClass().add("text-field ");
		 
		    //title.bind(titleTextField.textProperty());
        
		 
		 //init colum
		 idCol.setCellValueFactory(
				    new PropertyValueFactory<LigneCommande,String>("id"));
				desCol.setCellValueFactory(
				    new PropertyValueFactory<LigneCommande, String>("qt"));
				prixColA.setCellValueFactory(
				    new PropertyValueFactory<LigneCommande,String>("subTotal"));
				prixColV.setCellValueFactory(
					    new PropertyValueFactory<LigneCommande,String>("p"));
				
				idColp.setCellValueFactory(
						    new PropertyValueFactory<Produit,String>("id"));
						desColp.setCellValueFactory(
						    new PropertyValueFactory<Produit, String>("designiation"));
						prixColAp.setCellValueFactory(
						    new PropertyValueFactory<Produit,String>("Prix_A"));
						prixColVp.setCellValueFactory(
							    new PropertyValueFactory<Produit,String>("Prix_V"));
						categorieColVp.setCellValueFactory(
							    new PropertyValueFactory<Produit,String>("catt"));
						 idColv.setCellValueFactory(
								    new PropertyValueFactory<Vente,String>("id"));
								totalCol.setCellValueFactory(
								    new PropertyValueFactory<Vente, String>("total"));
								dateCol.setCellValueFactory(
								    new PropertyValueFactory<Vente,String>("date"));
								cliCol.setCellValueFactory(
									    new PropertyValueFactory<Vente,String>("c"));
								
								 idColc.setCellValueFactory(
										    new PropertyValueFactory<Client,String>("id"));
										nomCol.setCellValueFactory(
										    new PropertyValueFactory<Client, String>("nom"));
										prenomCol.setCellValueFactory(
										    new PropertyValueFactory<Client,String>("prenom"));
										telCol.setCellValueFactory(
											    new PropertyValueFactory<Client,String>("tel"));
										emailCol.setCellValueFactory(
											    new PropertyValueFactory<Client,String>("email"));
										adresseCol.setCellValueFactory(
											    new PropertyValueFactory<Client,String>("adresse"));

	}
	
	public static void main(String[] args) {
		System.out.println("hello");
		launch(args);
	}

}
