package GestionProduit;

import java.util.Optional;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class menu extends Application{
private Button categorie;
private Button client;
private Button produit;
private Button vente;
private Button payement;
private Label nom;
private HBox h,h1;
private Scene s;
private GridPane root;
private GridPane rootfotter;
private Stage window;
private Label l1;
private Label l2;
private Label l3;
private Label l4;
private Label l5;
public void initelement() {
	l1=new Label("  ");
	l2=new Label("  ");
	l3=new Label("  ");
	l4=new Label("  ");
	l5=new Label("Realiser par Fadlaoui Youssra GLSID2 2019-2020");
	categorie=new Button("Gestion categorie");
	l5.getStyleClass().add("custom-lable");
	categorie.getStyleClass().add("custom-buttonn");
	client=new Button("Gestion client");
	client.getStyleClass().add("custom-buttonn");
	produit=new Button("Gestion produit");
	produit.getStyleClass().add("custom-buttonn");
	vente=new Button("Gestion vente");
	vente.getStyleClass().add("custom-buttonn");
	payement=new Button("Gestion payement");
	payement.getStyleClass().add("custom-buttonn");
	nom=new Label("Gestion de magasin        ");
	nom.getStyleClass().add("custom-lable");
	root=new GridPane();
	rootfotter=new GridPane();
	h=new HBox();
	h1=new HBox();
	h.getStyleClass().add("header");
	h1.getStyleClass().add("header");
	root.add(nom, 0, 0);
	root.add(categorie, 1, 0);
	root.add(l1, 2, 0);
	root.add(client, 3, 0);
	root.add(l2, 4, 0);
	root.add(produit, 5, 0);
	root.add(l3, 6, 0);
	root.add(vente, 7, 0);
	root.add(l4, 8, 0);
	root.add(payement,9,0 );
	rootfotter.add(l5,0,0 );
	this.h.getChildren().add(root);
	this.h1.getChildren().add(rootfotter);
	this.h1.setAlignment(Pos.CENTER);
	s=new Scene(h);
	s.getStylesheets().add("mycss.css");
	

}
public HBox hboxreturn1() {
	initelement();
	
	return h1;

}
public HBox hboxreturn(Stage windows) {
	initelement();
	categorie.setOnAction(e->{
		 try {
			(new CategorieIHM()).start(windows);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	});
	produit.setOnAction(e->{
		 try {
			(new ProduitIHM()).start(windows);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	});
	vente.setOnAction(e->{
		 try {
			(new VenteIHM()).start(windows);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	});
	payement.setOnAction(e->{
		 try {
			(new PaimentIHM()).start(windows);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	});
	client.setOnAction(e->{
		 try {
			(new ClientIHM()).start(windows);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
	return h;

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
