package GestionProduit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Page1 extends Application{
	private Button categorie;
	private Button client;
	private Button produit;
	private Button vente;
	private Button payement;
	private Label nom;
	private HBox h,h1,h2,h3;
	private Scene s;
	private GridPane root;
	private GridPane rootfotter;
	private Stage window;
	private Label l1;
	private Label l2;
	private Label l3;
	private Label l4;
	private Label l5;
	private Label l6;
	private GridPane gp;
	private BorderPane rootp;
	public void initelement() {
		l1=new Label("  ");
		l2=new Label("  ");
		l3=new Label("  ");
		l4=new Label("  ");
		l5=new Label("Realiser par Fadlaoui Youssra GLSID2 2019-2020");
		l6=new Label("Gestion de magasin");
		categorie=new Button("Gestion categorie");
		l5.getStyleClass().add("custom-lable1");
		l6.getStyleClass().add("custom-lable1");
		categorie.getStyleClass().add("custom-button1");
		client=new Button("Gestion client");
		client.getStyleClass().add("custom-button1");
		produit=new Button("Gestion produit");
		produit.getStyleClass().add("custom-button1");
		vente=new Button("Gestion vente");
		vente.getStyleClass().add("custom-button1");
		payement=new Button("Gestion payement");
		payement.getStyleClass().add("custom-button1");
		nom=new Label("Gestion de magasin        ");
		nom.getStyleClass().add("custom-lable");
		root=new GridPane();
		
		h=new HBox();
		h2=new HBox();
		h3=new HBox();
		rootp=new BorderPane();
		root.getStyleClass().add("back");
	
		//root.add(nom, 0, 0);
		root.add(categorie, 0, 0);
		root.add(l1, 1, 0);
		root.add(client, 2, 0);
		root.add(l2, 3, 0);
		root.add(produit, 4, 0);
		root.add(l3, 5, 0);
		root.add(vente, 6, 0);
		root.add(l4, 7, 0);
		root.add(payement,8,0 );
		root.setAlignment(Pos.CENTER);
		l6.setAlignment(Pos.CENTER);
		l5.setAlignment(Pos.CENTER);
		h2.getChildren().add(l6);
		h3.getChildren().add(l5);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);

	rootp.setTop(h2);
	rootp.setCenter(root);
	rootp.setBottom(h3);
	rootp.getStyleClass().add("back");
		s=new Scene(rootp);
	
		s.getStylesheets().add("mycss.css");
		

	}

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			primaryStage.setHeight(1000);
			primaryStage.setWidth(1900);
			initelement();
			window=primaryStage;
			categorie.setOnAction(e->{
				 try {
					(new CategorieIHM()).start(window);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			produit.setOnAction(e->{
				 try {
					(new ProduitIHM()).start(window);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			vente.setOnAction(e->{
				 try {
					(new VenteIHM()).start(window);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			payement.setOnAction(e->{
				 try {
					(new PaimentIHM()).start(window);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			client.setOnAction(e->{
				 try {
					(new ClientIHM()).start(window);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			primaryStage.setScene(s);
			primaryStage.show();
			
		}
		public static void main(String[] args) {
			launch(args);
		}

	}
