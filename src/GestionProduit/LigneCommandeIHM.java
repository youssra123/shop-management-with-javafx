package GestionProduit;

import java.util.List;
import java.util.Optional;

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

public class LigneCommandeIHM extends Application{
	ProduitDAOIMPL imp=new ProduitDAOIMPL();
	private Label titre;
	private Label labelId;
	private Label labelqt;
	private Label labelProduit;
	private Label labelsubTotal;
	private Label lb;
	private TextField textFieldId;
	private TextField textFieldqt;
	private TextField textFieldsubTotal;
	private TextField textFieldProduit;
	private TextField textFieldSearch;
	private TextField textFieldSearchp;
	private BorderPane root;
	private BorderPane root2;

	private HBox header;
	private VBox right;
	private VBox left;
	private GridPane grid;
	private Button ajouter;
	private Button modifier;
	private Button supprimer;
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
    private LigneCommandeDAO p=new LigneCommandeDAOIMP();
    private ProduitDAO pr=new ProduitDAOIMPL();
    private long idl;
    private long idp;


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gestion des ligne de Commande");
		Stage stageTwo =new Stage();
		//Stage stageSupprimer=new Stage();
		initPanes();
		initElements();
		Scene scene=new Scene(root, 900,600);
		Scene scene2=new Scene(root2, 500,300);
		stageTwo.setScene(scene2);

		header.getChildren().add(titre);
		left.getChildren().addAll(ajouter, modifier,supprimer);
		right.getChildren().add(textFieldSearch);
		right.getChildren().add(table);
		grid.add(labelId, 0, 0);
		grid.add(textFieldId, 1, 0);
		grid.add(labelqt, 0, 1);
		grid.add(textFieldqt, 1, 1);	
		grid.add(labelProduit, 0, 2);
		grid.add(textFieldProduit, 1, 2);
		grid.add(textFieldSearchp, 0, 3);
		grid.add(tablep, 0, 4);
		root.setTop(header);
		root.setLeft(left);
		root.setRight(right);
		root.setCenter(grid);
		
		textFieldSearch.setOnKeyPressed((e)->{
			
			String key=textFieldSearch.getText();
			
				 List<LigneCommande> list=p.findAll(key);
			        final ObservableList<LigneCommande> data =FXCollections.observableArrayList( list );
			        table.setItems(data);
			  
	        
		});
		
		textFieldSearchp.setOnKeyPressed((e)->{
			
			String key=textFieldSearchp.getText();
			
				 List<Produit> list=pr.findAll(key);
			        final ObservableList<Produit> data =FXCollections.observableArrayList( list );
			        tablep.setItems(data);
			  
	        
		});
		
		table.setOnMouseClicked(e->{
			LigneCommande p1=table.getSelectionModel().getSelectedItem();
			textFieldId.setText(p1.getId()+"");
			textFieldId.setDisable(true);
			textFieldqt.setText(p1.getQt()+"");
			textFieldProduit.setText(p1.getP().getDesigniation()+"");
			idl=p1.getP().getId();
			
			
	
		});
		
		tablep.setOnMouseClicked(e->{
			Produit p1=tablep.getSelectionModel().getSelectedItem();
			textFieldProduit.setText(p1.getDesigniation());
			idp=p1.getId();
		});
		modifier.setOnAction(e->{
			if(textFieldId.getText() !=" "  &&  textFieldqt.getText() !=" " && textFieldProduit.getText() !=" ") {
				int id=Integer.parseInt(textFieldId.getText());
				int qt=Integer.parseInt(textFieldqt.getText());
				Produit produit=imp.find(idl);
				
			
				LigneCommande prod=new LigneCommande(id, qt, produit);
				p.update(prod);
				textFieldId.setText(p.getid()+1+"");
				textFieldId.setDisable(true);
				textFieldqt.setText("");
				textFieldProduit.setText("");
				refresh();
				
				
				
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
					int qt=Integer.parseInt(textFieldqt.getText());
					Produit produit=imp.find(idl);
					
					LigneCommande prod=new LigneCommande(id, qt, produit);
					p.delete(prod);
					textFieldId.setText(p.getid()+1+"");
					textFieldId.setDisable(true);
					textFieldqt.setText("");
					textFieldProduit.setText("");
					
					refresh();
					
					
					
				}
		    	} 
	
		});
			
		ajouter.setOnAction(e->{
		    int id1=Integer.parseInt(textFieldId.getText());
				int qt=Integer.parseInt(textFieldqt.getText());
				Produit produit=imp.find(idp);
				LigneCommande p=new LigneCommande(id1, qt, produit);
				System.out.println(produit.toString());
				LigneCommandeDAO pdao=new LigneCommandeDAOIMP();
				pdao.create(p);
				textFieldId.setText(p.getId()+1+"");
				textFieldId.setDisable(true);
				textFieldqt.setText("");
				textFieldProduit.setText("");
				
				refresh();
		
		});
		
		
		scene.getStylesheets().add("mycss.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public void refresh() {
		List<LigneCommande> list=p.findAll();
        final ObservableList<LigneCommande> data =FXCollections.observableArrayList( list );
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
		labelqt=new Label("Qt");
		labelProduit=new Label("Produit");
		labelId.getStyleClass().add("custom-label");
		labelqt.getStyleClass().add("custom-label");
		labelProduit.getStyleClass().add("custom-label");
		lb=new Label();
		tablep=new TableView<Produit>();
		idColp = new TableColumn<Produit,String>("id");
        desColp = new TableColumn<Produit,String>("designiation");
        prixColAp = new TableColumn<Produit,String>("Prix_A");
        prixColVp = new TableColumn<Produit,String>("Prix_V");
        
        tablep.getColumns().addAll(idColp, desColp, prixColAp,prixColVp);
        
		table=new TableView<LigneCommande>();
		idCol = new TableColumn<LigneCommande,String>("id");
        desCol = new TableColumn<LigneCommande,String>("qt");
        prixColA = new TableColumn<LigneCommande,String>("SubTotal");
        prixColV = new TableColumn<LigneCommande,String>("id_produit");
        
        table.getColumns().addAll(idCol, desCol, prixColA,prixColV);
        
        
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
		 textFieldSearchp=new TextField();
		 textFieldId=new TextField(p.getid()+"");
		 textFieldId.setDisable(true);
		 textFieldqt=new TextField();
		 textFieldProduit=new TextField();

		 
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
					    new PropertyValueFactory<LigneCommande,String>("Produit"));
				 idColp.setCellValueFactory(
						    new PropertyValueFactory<Produit,String>("id"));
						desColp.setCellValueFactory(
						    new PropertyValueFactory<Produit, String>("designiation"));
						prixColAp.setCellValueFactory(
						    new PropertyValueFactory<Produit,String>("Prix_A"));
						prixColVp.setCellValueFactory(
							    new PropertyValueFactory<Produit,String>("Prix_V"));

	}
	
	public static void main(String[] args) {
		System.out.println("hello");
		launch(args);
	}

}
