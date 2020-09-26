package GestionProduit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

public class PaimentIHM extends Application{
	Socket soc=null;
	InputStream in;
	OutputStream out;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	BufferedReader br;
	PrintWriter pw;
	InputStreamReader isr;
	private Label titre;
	private Label labelId;
	private Label labelDate;
	private Label labelMontantes;
	private Label labelMontanton;
	private Label labelMontantc;
	private Label labelMontantt;
	private Label labledatep;
	private Label labledateef;
	private Label propretairet;
	private Label banquet;
	private Label numchequet;
	
	private Label labelType;
	private Label labelTotal;
	private Label labelT;
	private Label labelR;
	private Label lb;
	private Label NumV;
	private TextField textFieldId;
	private TextField textFieldDate;
	private TextField textFieldMontanton;
	private TextField textFieldMontantes;
	private TextField textFieldMontantc;
	
	private TextField TlabelMontantt;
	private TextField Tlabledatep;
	private TextField Tlabledateef;
	private TextField Tpropretairet;
	private TextField Tbanquet;
	private TextField Tnumchequet;
	
	private Label proprietaire;
	private TextField Tproprietaire;
	private Label date;
	private TextField Tdate;
	private Label numcheque;
	private TextField Tnumcheque;
	private Label banque;
	private TextField Tbanque;
	private Label numcarte;
	private TextField Tnumcarte;
	private Label codecarte;
	private TextField Tcodecarte;
	private Label numt;
	private TextField Tnumt;
	private Label vide;
	private Label payeravance;
	private Label payerimmediatement;
	private RadioButton checkBoxcheque = new RadioButton("Cheque");
	private RadioButton checkBoxespece = new RadioButton("Espece");
	//private TextField Tcodecarte;
	 private VenteDAO v=new VenteDAOIMP();
	//private TextField textFieldType;
	private TextField textFieldSearch;
	private BorderPane root;
	private BorderPane root2;
	private ComboBox<String> comboBox;
	private ComboBox<String> comboBoxbank;
	private ComboBox<String> comboBoxbank1;
	private ComboBox<String> comboBoxpayeravence;
	private HBox header;
	private HBox total;
	private double tot=5680;
	private long idv;
	private VBox right;
	private VBox left;
	private GridPane grid;
	private GridPane grid1;
	private GridPane grid2;
	private GridPane grid3;
	private Button ajouter;
	private Button afficher;
	private Button modifier;
	private Button supprimer;
	private TableView<Paiment>  table ;
	private TableColumn<Paiment,String> idCol ;
    private TableColumn<Paiment,String>  desCol ;
    private TableColumn<Paiment,String>  prixColA ;
    private TableColumn<Paiment,String>  prixColV ;
    private TableColumn<Paiment,String>  prixColVe ;
    private TableColumn<LigneCommande,String> idColl ;
    private TableColumn<LigneCommande,String>  desColl ;
    private TableColumn<LigneCommande,String>  prixColAl ;
    private TableColumn<LigneCommande,String>  prixColVl;
    private TableColumn<LigneCommande,String>  prixColVlv;
    private PaimentDAOIMP p=new PaimentDAOIMP();
    private ClientDAOIMPL c=new ClientDAOIMPL();
    private LigneCommandeDAOIMP l=new LigneCommandeDAOIMP();
    private TableView<Vente>  tablev ;
    private TableView<LigneCommande>  tablel ;
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
    private TextField textFieldSearchc;
	private TextField textFieldSearchv;
	private TextField textFieldSearchl;
	  private long idc;
	  private long idp;
	private List<Client> listc=c.findAll();
	private List<LigneCommande> listl=l.findAll();
	private List<Vente> listv=v.findAll();
	private List<Paiment> listp=p.findAll();
	private Label totallc;
	private Label totalpayer;
	private Label reste;
	private double toto;
	private Label ncmodif;
	private Label montmodif;
	private Label etamodif;
	private TextField Tncmodif;
	private TextField Tmontmodif;
	private TextField Tetamodif;
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1900);
		primaryStage.setTitle("Gestion des paiments");
		Stage stageTwo =new Stage();
		//Stage stageSupprimer=new Stage();
		initPanes();
		initElements();
		Scene scene=new Scene(root, 800,600);
		Scene scene2=new Scene(root2, 0,300);
		stageTwo.setScene(scene2);
		menu m1=new menu();
		header.getChildren().add(m1.hboxreturn(primaryStage));
		
	//left.getChildren().addAll(textFieldSearchl, tablel);
		//grid1.add(labelTotal,0,0);
		grid1.add(textFieldSearchc,0,0);
		grid1.add(tablec,0,1);

		//left.getChildren().addAll(textFieldSearchc, tablec);
		left.getChildren().add(grid1);
		
		grid3.add(totalpayer,0,2);
		grid3.add(reste,1,2);
		right.getChildren().add(textFieldSearch);
		right.getChildren().add(table);
		right.getChildren().add(grid3);
		right.getChildren().add(textFieldSearchl);
		right.getChildren().add(tablel);
		right.getChildren().add(totallc);
		//right.getChildren().add(grid3);
		left.getChildren().add(textFieldSearchv);
		left.getChildren().add(tablev);
		grid.add(NumV, 0, 0);
		grid.add(labelId, 0, 1);
		grid.add(textFieldId, 1, 1);
		grid.add(labelDate, 0, 2);
		grid.add(textFieldDate, 1, 2);	
		grid.add(labelType, 0, 4);
		grid.add(comboBox, 1, 4);
	//	grid.add(labelT, 0, 10);
	//	grid.add(labelR, 0, 11);
		grid.add(ajouter, 0, 70);
		grid.add(modifier, 1, 70);
		grid.add(supprimer, 2, 70);
		
		root.setTop(header);
		root.setLeft(left);
		root.setRight(right);
		//root.setCenter(grid2);
		root.setCenter(grid);
		root.setBottom(m1.hboxreturn1());
		
		textFieldSearch.setOnKeyPressed((e)->{
			
			String key=textFieldSearch.getText();
			
				 List<Paiment> list=p.findAll(key);
			        final ObservableList<Paiment> data =FXCollections.observableArrayList( list );
			        table.setItems(data);
			  
	        
		});
textFieldSearchv.setOnKeyPressed((e)->{
			
			String key=textFieldSearchv.getText();
			
				 List<Vente> list=v.findAll(key);
			        final ObservableList<Vente> data =FXCollections.observableArrayList( list );
			        tablev.setItems(data);
			  
	        
		});
textFieldSearchl.setOnKeyPressed((e)->{
	
	String key=textFieldSearchl.getText();
	
		 List<LigneCommande> listl=l.findAll(key);
	        final ObservableList<LigneCommande> data =FXCollections.observableArrayList( listl );
	        tablel.setItems(data);
	
    
});
textFieldSearchc.setOnKeyPressed((e)->{
	
	String key=textFieldSearchc.getText();
	
		 List<Client> list=c.findAll(key);
	        final ObservableList<Client> data =FXCollections.observableArrayList( list );
	        tablec.setItems(data);
	  
    
});
checkBoxcheque.setOnKeyPressed((e)->{
	 labelMontantes.setVisible(false);
	numcheque.setVisible(true);
	Tnumcheque.setVisible(true);
	proprietaire.setVisible(true);
	Tproprietaire.setVisible(true);
	textFieldMontantc.setVisible(true);
	labelMontantc.setVisible(true);
	banque.setVisible(true);
	comboBoxbank.setVisible(true);
	date.setVisible(true);
	Tdate.setVisible(true);
	grid.add(labelMontantc, 0, 9);
	grid.add(textFieldMontantc, 1, 9);
	grid.add(numcheque, 0, 10);
	grid.add(Tnumcheque, 1, 10);
	grid.add(proprietaire, 0, 11);
	grid.add(Tproprietaire, 1, 11);
	grid.add(date, 0, 12);
	grid.add(Tdate, 1, 12);
	grid.add(banque, 0, 13);
	grid.add(comboBoxbank, 1, 13);
	
});
checkBoxespece.setOnKeyPressed((e)->{
	 labelMontantc.setVisible(false);
	 labelMontantes.setVisible(true);
		textFieldMontantes.setVisible(true);
		grid.add(labelMontantes, 0, 9);
		grid.add(textFieldMontantes, 1, 9);
});
comboBoxpayeravence.setOnKeyPressed((e)->{
	String key=comboBoxpayeravence.getValue();

	if(key.equals("yes")) {
		 checkBoxcheque.setVisible(true);
		 checkBoxespece.setVisible(true);
		grid.add(checkBoxespece, 0, 8);
		grid.add(checkBoxcheque, 1, 8);
	}
if(key.equals("no")) {
		
	}
});
comboBox.setOnKeyPressed((e)->{
	visible();
	String key=comboBox.getValue();
	

	if(key.equals("Cheque")) {
	
		 System.out.println("Cheque");
		 
		numcheque.setVisible(true);
		Tnumcheque.setVisible(true);
		proprietaire.setVisible(true);
		Tproprietaire.setVisible(true);
		textFieldMontantc.setVisible(true);
		labelMontantc.setVisible(true);
		banque.setVisible(true);
		comboBoxbank.setVisible(true);
		date.setVisible(true);
		Tdate.setVisible(true);
		grid.add(labelMontantc, 0, 5);
		grid.add(textFieldMontantc, 1, 5);
		grid.add(numcheque, 0, 6);
		grid.add(Tnumcheque, 1, 6);
		grid.add(proprietaire, 0, 7);
		grid.add(Tproprietaire, 1, 7);
		grid.add(date, 0, 8);
		grid.add(Tdate, 1, 8);
		grid.add(banque, 0, 9);
		grid.add(comboBoxbank, 1, 9);
	}
	if(key.equals("Espece")) {
		 System.out.println("Espece");
		 labelMontantes.setVisible(true);
			textFieldMontantes.setVisible(true);
			grid.add(labelMontantes, 0, 5);
			grid.add(textFieldMontantes, 1, 5);
		
	}
	if(key.equals("Online")) {
		 System.out.println("Online");
		 
		labelMontanton.setVisible(true);
		textFieldMontanton.setVisible(true);
		numcarte.setVisible(true);
		Tnumcarte.setVisible(true);
		Tcodecarte.setVisible(true);
		codecarte.setVisible(true);
		grid.add(labelMontanton, 0, 5);
		grid.add(textFieldMontanton, 1, 5);
		grid.add(numcarte, 0, 6);
		grid.add(Tnumcarte, 1, 6);
		grid.add(codecarte, 0, 7);
		grid.add(Tcodecarte, 1, 7);
		

	
	}	
	if(key.equals("Traite")) {
		 System.out.println("Traite");
		
		 numt.setVisible(true);
		 Tnumt.setVisible(true);
		 
		payerimmediatement.setVisible(true);
		payeravance.setVisible(true);
		comboBoxpayeravence.setVisible(true);
		labelMontantt.setVisible(true);
		TlabelMontantt.setVisible(true);
		labledatep.setVisible(true);
		Tlabledatep.setVisible(true);
		labledateef.setVisible(true);
		Tlabledateef.setVisible(true);
		propretairet.setVisible(true);
		Tpropretairet.setVisible(true);
		banquet.setVisible(true);
		comboBoxbank1.setVisible(true);
		numchequet.setVisible(true);
		Tnumchequet.setVisible(true);
		grid.add(payeravance, 0, 7);
		grid.add(comboBoxpayeravence, 1, 7);
		grid.add(payerimmediatement, 0, 15);
		grid.add(numt, 0, 16);
		grid.add(Tnumt, 1, 16);
		grid.add(labelMontantt, 0, 17);
		grid.add(TlabelMontantt, 1, 17);
		grid.add(labledatep, 0, 18);
		grid.add(Tlabledatep, 1, 18);
		grid.add(labledateef, 0, 19);
		grid.add(Tlabledateef, 1, 19);
		grid.add(propretairet, 0, 20);
		grid.add(Tpropretairet, 1, 20);
		grid.add(banquet, 0, 21);
		grid.add(comboBoxbank1, 1, 21);
		grid.add(numchequet, 0, 22);
		grid.add(Tnumchequet, 1, 22);
		//grid.add(afficher, 2, 16);
	

	
	}
	
});
tablec.setOnMouseClicked(e->{
	Client p1=tablec.getSelectionModel().getSelectedItem();
	textFieldSearchc.setText(p1.getNom());
	idc=p1.getId();
	listv=v.findcv(idc);
	refresh1();
});
		table.setOnMouseClicked(e->{
			Paiment p1=table.getSelectionModel().getSelectedItem();
			PaimentDAOIMP p4=new PaimentDAOIMP();
			if (p1.getType1().equals("Traite")|| p1.getType1().equals("Cheque")) {
				ncmodif.setVisible(true);

				 Tncmodif.setVisible(true);
				 grid.add(ncmodif, 0, 68);
				 grid.add(Tncmodif, 1, 68);
				Tncmodif.setText(p1.getC().getNum()+"");
				montmodif.setVisible(true);
				 Tmontmodif.setVisible(true);
				 Tetamodif.setVisible(true);
				etamodif.setVisible(true);
				 grid.add(montmodif, 0, 67);
				 grid.add(Tmontmodif, 1, 67);
				 grid.add(etamodif, 0, 69);
				 grid.add(Tetamodif, 1, 69);
				 comboBox.setValue(p1.getType1()+"");
				Tmontmodif.setText(p1.getC().getMontant()+"");
				Tetamodif.setText(p1.getC().getEtat()+"");
				textFieldId.setText(p1.getId()+"");
				textFieldId.setDisable(true);
				textFieldDate.setText(p1.getDate());
			}
			if (p1.getType1().equals("Espece")) {
				montmodif.setVisible(true);
				 Tmontmodif.setVisible(true);
				 Tetamodif.setVisible(true);
				etamodif.setVisible(true);
				 grid.add(montmodif, 0, 67);
				 grid.add(Tmontmodif, 1, 67);
				 grid.add(etamodif, 0, 69);
				 grid.add(Tetamodif, 1, 69);
				 comboBox.setValue(p1.getType1()+"");
				Tmontmodif.setText(p1.getEs().getMontant()+"");
				Tetamodif.setText(p1.getEs().getEtat()+"");
				textFieldId.setText(p1.getId()+"");
				textFieldId.setDisable(true);
				textFieldDate.setText(p1.getDate());
			}
			
			
			//textFieldMontant.setText(p1.getMontant()+"");
			idp=p1.getId();
			
			
			

		});
		tablev.setOnMouseClicked(e->{
			Vente p1=tablev.getSelectionModel().getSelectedItem();
			NumV.setText("Num Vente:"+p1.getId()+"du "+p1.getDate());
			NumV.setVisible(true);
			idv=p1.getId();
			listl=l.findcl(idv);
			listp=p.findAllid(idv);

			refresh();
			refreshl();
			 totallc.setText("Total:"+calcultlc());
			 reste.setText("Reste a payer:"+(calcultlc()-totalapayer()));
			 totalpayer.setText("Total payer:"+totalapayer());
		});
		modifier.setOnAction(e->{
						String date=textFieldDate.getText();
				int id=Integer.parseInt(textFieldId.getText());
				//double montant=Double.parseDouble(textFieldMontant.getText());
				String type=comboBox.getValue();
				System.out.println("type="+type);
				Paiment prod=new Paiment(id, date,type);
				//p.update(prod);
				if(type.equals("Espece")){
					double m=Double.parseDouble(Tmontmodif.getText());
					String etat=Tetamodif.getText();
					Espece es=new Espece(m,etat,id);
					System.out.println("espece="+es);
					prod.setEs(es);
					p.updatees(prod);
					}
				if(type.equals("Traite") || type.equals("Cheque") ){
					long um=Long.parseLong(Tncmodif.getText());
					double m=Double.parseDouble(Tmontmodif.getText());
					String etat=Tetamodif.getText();
				Cheque e1=new Cheque(um, m,etat);
				prod.setC(e1);
				p.updatec(prod);

					
				}
			
				
				ncmodif.setVisible(false);
				montmodif.setVisible(false);
				etamodif.setVisible(false);
				 Tncmodif.setVisible(false);
				 Tetamodif.setVisible(false);
				 Tmontmodif.setVisible(false);
				textFieldId.setText(p.getid()+"");
				textFieldId.setDisable(true);
				textFieldDate.setText("");
				//textFieldMontant.setText("");
				comboBox.setValue("");
				refresh();				
			
		});
		ajouter.setOnAction(e->{
			 if(comboBox.getValue()==null) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait veillez choisi un type de paiement");
                 return;
             }
       
        	 if(textFieldDate.getText().isEmpty()) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter la date exmple:yyyy/mm/dd");
                 return;
             }
         
             if(!isValiddate(textFieldDate.getText())) {
                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " la date n'est pas valid exmple:yyyy/mm/dd");
                 return;
             }
          
            
			String date=textFieldDate.getText();
			int id=Integer.parseInt(textFieldId.getText());
			
			String type=comboBox.getValue();
			System.out.println("type="+type);
			Paiment p1=new Paiment(id,idv, date,type);
			PaimentDAOIMP pd=new PaimentDAOIMP();
			PaimentDAO pdao=new PaimentDAOIMP();
			pdao.create(p1);
			
			
			if(type.equals("Espece")){
				 if(textFieldMontantes.getText().isEmpty()) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter le montant");
	                 return;
	             }
	         
	             if(!isValiddouble(textFieldMontantes.getText())) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " le montant n'est pas valid ");
	                 return;
	             }
				double montant=Double.parseDouble(textFieldMontantes.getText());
				ModePaiment e1=new Espece(montant,"payer");
				p1.setType(e1);
				p1.getType().setMontant(montant);
				p.createEs(p1);
				textFieldMontantes.setText("");
			}
			if(type.equals("Cheque")){
				 if(Tnumcheque.getText().isEmpty()) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter le num cheque");
	                 return;
	             }
	         
	             if(!isValidnum(Tnumcheque.getText())) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " le num cheque n'est pas valid ");
	                 return;
	             }
	        	 if(textFieldMontantc.getText().isEmpty()) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter le montant");
	                 return;
	             }
	         
	             if(!isValiddouble(textFieldMontantc.getText())) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " le montant n'est pas valid ");
	                 return;
	             }
	        	 if(Tproprietaire.getText().isEmpty()) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter le proprietaire");
	                 return;
	             }
	         
	             if(!isValidstring(Tproprietaire.getText())) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " le proprietaire n'est pas valid ");
	                 return;
	             }
	        	 if(Tdate.getText().isEmpty()) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", "s'il vous plait enter la date exmple:yyyy/mm/dd");
	                 return;
	             }
	         
	             if(!isValiddate(Tdate.getText())) {
	                 showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Form Error!", " la date n'est pas valid exmple:yyyy/mm/dd");
	                 return;
	             }
				long numcheque=	Long.parseLong(Tnumcheque.getText());
				double montantc=Double.parseDouble(textFieldMontantc.getText());
				String proprietaire1=Tproprietaire.getText();
				String DateC=Tdate.getText();
				String Banque=comboBoxbank.getValue();
				String etat="payer";
				Cheque e1=new Cheque(numcheque, proprietaire1, DateC, Banque, montantc,etat);
				e1.setEtat(etat);
				p1.setType(e1);
				p1.setC(e1);
				p1.getType().setMontant(montantc);
				p.createC(p1);
				textFieldMontantc.setText("");
				Tproprietaire.setText("");
				Tdate.setText("");
				Tnumcheque.setText("");
				comboBoxbank.setValue("");
				
			}
			if(type.equals("Traite")){
				int numt=Integer.parseInt(Tnumt.getText());
				String datep=Tlabledatep.getText();
				String dateef=Tlabledateef.getText();
				double montantt=Double.parseDouble(TlabelMontantt.getText());
				String pro=Tpropretairet.getText();
				long numChe=Long.parseLong(Tnumchequet.getText());
				String banquet=comboBoxbank1.getValue();
				String datec=Tdate.getText();
				String etatt="no payer";
				double m=montantt/numt;
				for (int i = 0; i < numt; i++) {
					Cheque e1=new Cheque(numChe, pro, datec, banquet, m,etatt);
					Traites t=new Traites(m, datep, dateef, e1);
					t.setC(e1);
					p1.setT(t);
					p.createT(p1);
				}
				
				
			}
			
			if(type.equals("Online")){
				long num=Integer.parseInt(Tnumcarte.getText());
				long code=Integer.parseInt(Tcodecarte.getText());
				double montanton=Double.parseDouble(textFieldMontanton.getText());
				ModePaiment e1=new Online(num, code, montanton);
				//p1.setType(e1);
			
				try {
					soc=new Socket("localhost", 1234);
					in=soc.getInputStream();
					System.out.println("1");
					out=soc.getOutputStream();
					System.out.println("2");
					//ois=new ObjectInputStream(in);
					System.out.println("3");
					oos=new ObjectOutputStream(out);
					System.out.println("4");
					System.out.println("5");
					oos.writeObject(e1);
					int nb=in.read();
					if(nb==1) {System.out.println("operation bien effectuer");
					System.out.println("e="+e1);
						p1.setType(e1);
						p1.setOn((Online)e1);
						System.out.println("p1="+p1);
						p.createO(p1);
						
						
					}
					if(nb==0) {
						System.out.println("operation n'est pas effectuer");
					}
					soc.close();
					/*
					//ihm.envoyer().show();
				//	System.out.println("l'object est :"+serihm.hboxen());
					System.out.println("1");
					in=new ObjectInputStream(soc.getInputStream());
					System.out.println("2");
					out=new ObjectOutputStream(soc.getOutputStream());
					System.out.println("3");
					out.writeObject(e1);
					System.out.println("4");
					out.flush();
					System.out.println("5");*/
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				
				
			/*	p1.getType().setCode(code);
				p1.getType().setNum_carte(num);
				p1.getType().setMontant(montanton);
				p.createOn(p1);*/
				
			}
			
			
			 showAlert(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow(), "Registration Successful!", "le paiement est bien Ajouter ");

			
			textFieldId.setText(p.getid()+"");
			textFieldId.setDisable(true);
			textFieldDate.setText("");
			
			//textFieldMontant.setText("");
			comboBox.setValue("");

		     refresh();
		
		});
		
		afficher.setOnAction(e->{
			Tpropretairet.setVisible(false);
			Tlabledateef.setVisible(false);
			Tlabledatep.setVisible(true);
			TlabelMontantt.setVisible(false);
			comboBoxbank.setVisible(false);
			Tnumchequet.setVisible(false);
			propretairet.setVisible(false);
			labledateef.setVisible(false);
			labledatep.setVisible(false);
			labelMontantt.setVisible(false);
			banquet.setVisible(false);
			numchequet.setVisible(false);
			
			Tpropretairet.setVisible(true);
			Tlabledateef.setVisible(true);
			Tlabledatep.setVisible(true);
			TlabelMontantt.setVisible(true);
			comboBoxbank1.setVisible(true);
			Tnumchequet.setVisible(true);
			propretairet.setVisible(true);
			labledateef.setVisible(true);
			labledatep.setVisible(true);
			labelMontantt.setVisible(true);
			banquet.setVisible(true);
			numchequet.setVisible(true);
			//String date=textFieldDate.getText();
			int id=Integer.parseInt(Tnumt.getText());
			System.out.println("id="+id);
			int t=0,k=1;
			for (int i = 0; i < id; i++) {
				t=t+i;
				System.out.println(i);
				grid.add(new Label("traite num"+i), 0, 17+t);
				grid.add(labelMontantt, 0, 18+t);
				grid.add(TlabelMontantt, 1, 18+t);
				grid.add(labledatep, 0, 19+t);
				grid.add(Tlabledatep, 1, 19+t);
				grid.add(labledateef, 0, 20+t);
				grid.add(Tlabledateef, 1, 20+t);
				grid.add(propretairet, 0, 21+t);
				grid.add(Tpropretairet, 1, 21+t);
				grid.add(banquet, 0, 22+t);
				grid.add(comboBoxbank1, 1, 22+t);
				grid.add(numchequet, 0, 23+t);
				grid.add(Tnumchequet, 1, 23+t);
				k=1+i;
				t=t+6*k;
			}

		
		
		});
		supprimer.setOnAction(e->{
			 Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Supprision Confirmation");
		      alert.setHeaderText("Vous etre sur que vous vollez supprimer le produit: "+idp+"?");
		     
		 
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK){
		  		if(textFieldId.getText() !=" "  &&  textFieldDate.getText() !=" " && comboBox.getValue() !=" " ) {
		  			String date=textFieldDate.getText();
					//int id=Integer.parseInt(textFieldId.getText());
				//	double montant=Double.parseDouble(textFieldMontant.getText());
					String type=comboBox.getValue();
					Paiment prod=new Paiment(idp, date,type);
					
					
					p.delete(prod);
					textFieldId.setText(p.getid()+"");
					textFieldId.setDisable(true);
					textFieldDate.setText("");
					//textFieldMontant.setText("");
					comboBox.setValue("");
				
					
					
					
				}
		    	} 
	
		});
		
		scene.getStylesheets().add("mycss.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	 public static boolean isValiddouble(String email)
	    {
	        String emailRegex = "[0-9]*.?[0-9]*";

	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
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
public void visible() {
	Tpropretairet.setVisible(false);
	Tlabledateef.setVisible(false);
	Tlabledatep.setVisible(false);
	TlabelMontantt.setVisible(false);
	//Tbanquet.setVisible(false);
	Tnumchequet.setVisible(false);
	propretairet.setVisible(false);
	labledateef.setVisible(false);
	labledatep.setVisible(false);
	labelMontantt.setVisible(false);
	banquet.setVisible(false);
	numchequet.setVisible(false);
	comboBoxpayeravence.setVisible(false);
	 checkBoxcheque.setVisible(false);
	 checkBoxespece.setVisible(false);
	NumV.setVisible(false);
	payeravance.setVisible(false);
	payerimmediatement.setVisible(false);
	numcheque.setVisible(false);
	proprietaire.setVisible(false);
	date.setVisible(false);
	banque.setVisible(false);
	numcarte.setVisible(false);
	codecarte.setVisible(false);
	comboBoxbank.setVisible(false);
	 Tproprietaire.setVisible(false);
	 Tdate.setVisible(false);
	 Tnumcheque.setVisible(false);
	 Tcodecarte.setVisible(false);
	 Tnumcarte.setVisible(false);
	 labelMontanton.setVisible(false);
		textFieldMontanton.setVisible(false);
		labelMontantes.setVisible(false);
		textFieldMontantes.setVisible(false);
		labelMontantc.setVisible(false);
		textFieldMontantc.setVisible(false);
		 checkBoxcheque.setVisible(false);
		 checkBoxespece.setVisible(false);
		payeravance.setVisible(false);
}
	public void refresh() {
	
        final ObservableList<Paiment> data =FXCollections.observableArrayList( listp );
        table.setItems(data);
		
	}
	public void refresh1() {
	
        final ObservableList<Vente> data =FXCollections.observableArrayList(listv);
        tablev.setItems(data);
     
		
	}
	public void refreshc() {
	
        final ObservableList<Client> data =FXCollections.observableArrayList(listc);
        tablec.setItems(data);
     
		
	}
	public void refreshl() {
		
        final ObservableList<LigneCommande> data =FXCollections.observableArrayList(listl);
        tablel.setItems(data);
     
		
	}
	private double calcultlc() {
		toto=0;
		for (int i = 0; i < listl.size(); i++) {
			toto=toto+listl.get(i).getSubTotal();
		}
		return toto;

	}
	private double totalapayer() {
		double payer=0;
		for (int i = 0; i < listp.size(); i++) {
			if(listp.get(i).getType1().equals("Cheque")) {
				if(listp.get(i).getC().getEtat().equals("payer")) {
					payer=payer+listp.get(i).getC().getMontant();
					
				}
			}
			if(listp.get(i).getType1().equals("Traite")) {
				if(listp.get(i).getC().getEtat().equals("payer")) {
					payer=payer+listp.get(i).getC().getMontant();
					
				}
			}
			if(listp.get(i).getType1().equals("Espece")) {
				if(listp.get(i).getEs().getEtat().equals("payer")) {
					payer=payer+listp.get(i).getEs().getMontant();
					
				}
			}
		
			
		}
return payer;
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
		// left.getStyleClass().add("class-left");
		 left.setSpacing(30);
		 left.setPadding(new Insets(20));
		 
		 grid=new GridPane();
		 grid1=new GridPane();
		 grid3=new GridPane();
		// grid.getStyleClass().add("custom-pane-center");
		 grid.setPadding(new Insets(10));

		
	}
	public void initElements() {
		//initialiser labels
		titre=new Label("Gestion des paiments d'une vente");
		totallc=new Label("Total:"+calcultlc());
		reste=new Label("Reste a payer:"+(calcultlc()-totalapayer()));
		totalpayer=new Label("Total payer:"+totalapayer());
		labelId=new Label("N");
		labelDate=new Label("Date");
		labelMontanton=new Label("Montant:");
		labelMontantes=new Label("Montant:");
		labelMontantc=new Label("Montant:");
		labelType=new Label("Type");
		labelMontantt =new Label("Montant:");
		labledatep=new Label("Date prevue");
		labledateef=new Label("Date effective");
		propretairet =new Label("proprietaire");
		banquet=new Label("banque:");
		numchequet=new Label("num cheque:");

		propretairet.setVisible(false);
		labledateef.setVisible(false);
		labledatep.setVisible(false);
		labelMontantt.setVisible(false);
		banquet.setVisible(false);
		numchequet.setVisible(false);
		NumV=new Label("Num Vente:");
		NumV.setVisible(false);
		payeravance=new Label("Payer en avance:");
		payeravance.setVisible(false);
		payerimmediatement=new Label("Payer le reste:");
		payerimmediatement.setVisible(false);
		numcheque=new Label("Num cheque:");
		numcheque.setVisible(false);
		proprietaire=new Label("Proprietaire:");
		proprietaire.setVisible(false);
		date=new Label("Date cheque:");
		date.setVisible(false);
		banque=new Label("Banque:");
		banque.setVisible(false);
		numcarte=new Label("Num Carte:");
		numcarte.setVisible(false);
		codecarte=new Label("Code Carte:");
		codecarte.setVisible(false);
		numt=new Label("Nombre de traite:");
		numt.setVisible(false);
		ncmodif=new Label("num cheque:");
		montmodif=new Label("Montant:");
		etamodif=new Label("Etat:");
		ncmodif.setVisible(false);
		montmodif.setVisible(false);
		etamodif.setVisible(false);
		//labelTotal=new Label("Total paye:"+p.calcul());
		labelT=new Label("Total:"+tot);
		//labelR=new Label("Reste:"+(tot-p.calcul()));
		labelId.getStyleClass().add("custom-label");
		labelDate.getStyleClass().add("custom-label");
		//labelMontant.getStyleClass().add("custom-label");
		labelType.getStyleClass().add("custom-label");
		 textFieldSearchv=new TextField();
		 textFieldSearchc=new TextField();
		 textFieldSearchl=new TextField();
		lb=new Label();
		
		table=new TableView<Paiment>();
		idCol = new TableColumn<Paiment,String>("Date");
        desCol = new TableColumn<Paiment,String>("Montant");
        prixColA = new TableColumn<Paiment,String>("Type");
        prixColV = new TableColumn<Paiment,String>("Num cheque");
        prixColVe = new TableColumn<Paiment,String>("Etat");
        table.getColumns().addAll(idCol, desCol, prixColA,prixColV,prixColVe);
        
        tablel=new TableView<LigneCommande>();
        idColl = new TableColumn<LigneCommande,String>("id");
        desColl = new TableColumn<LigneCommande,String>("qt");
        prixColAl = new TableColumn<LigneCommande,String>("SubTotal");
        prixColVl = new TableColumn<LigneCommande,String>("Designiation");
        prixColVlv = new TableColumn<LigneCommande,String>("prix vente");
        
        tablel.getColumns().addAll(idColl,prixColVl,prixColVlv, desColl, prixColAl);
        
        tablec=new TableView<Client>();
     		idColc = new TableColumn<Client,String>("id");
     		nomCol = new TableColumn<Client,String>("nom");
             prenomCol = new TableColumn<Client,String>("prenom");
             telCol = new TableColumn<Client,String>("tel");
             emailCol = new TableColumn<Client,String>("email");
             adresseCol = new TableColumn<Client,String>("adress");
             
             tablec.getColumns().addAll(idColc, nomCol, prenomCol,telCol,emailCol,adresseCol);
             
        tablev=new TableView<Vente>();
		idColv = new TableColumn<Vente,String>("id");
        totalCol = new TableColumn<Vente,String>("total");
        dateCol = new TableColumn<Vente,String>("date");
        cliCol = new TableColumn<Vente,String>("c");
        
        tablev.getColumns().addAll(idColv, totalCol, dateCol,cliCol);
        
        refresh1();
		refresh();
		refreshc();
		refreshl();
		//initialiser buttons
		ajouter=new Button("ajouter");
	    ajouter.getStyleClass().add("custom-button");
	    afficher=new Button("afficher");
	    afficher.getStyleClass().add("custom-button");
		modifier=new Button("Enregister");
		modifier.getStyleClass().add("custom-button");
		supprimer=new Button("Annuler");
		supprimer.getStyleClass().add("custom-button");
		
		//initialiser textfields
		 textFieldSearch=new TextField();
		 textFieldId=new TextField(p.getid()+"");
		 textFieldId.setDisable(true);
		 textFieldDate=new TextField();
		//textFieldMontant=new TextField();
		 textFieldMontanton=new TextField();
		 textFieldMontantc=new TextField();
		 textFieldMontantes=new TextField();
		 TlabelMontantt=new TextField();
		 Tlabledatep=new TextField();
		 Tlabledateef=new TextField();
		 Tpropretairet=new TextField();
		 Tncmodif=new TextField();
		 Tetamodif=new TextField();
		 Tmontmodif=new TextField();
		 Tncmodif.setVisible(false);
		 Tetamodif.setVisible(false);
		 Tmontmodif.setVisible(false);
		// Tbanquet=new TextField();
		 Tnumchequet=new TextField();
		 
		 Tnumchequet.setVisible(false);

		 Tpropretairet.setVisible(false);
		 Tlabledateef.setVisible(false);
		 Tlabledatep.setVisible(false);
		 TlabelMontantt.setVisible(false);
		 
		// Tbanque=new TextField();
		 //Tbanque.setVisible(false);
		 Tproprietaire=new TextField();
		 Tproprietaire.setVisible(false);
		 Tdate=new TextField();
		 Tdate.setVisible(false);
		 Tnumcheque=new TextField();
		 Tnumcheque.setVisible(false);
		 Tcodecarte=new TextField();
		 Tcodecarte.setVisible(false);
		 Tnumcarte=new TextField();
		 Tnumcarte.setVisible(false);
		 Tnumt=new TextField();
		 Tnumt.setVisible(false);
		// textFieldType=new TextField();
		 textFieldId.getStyleClass().add("text-field ");
		 comboBox = new ComboBox(); 
		 comboBox.getItems().setAll("Espece", "Cheque", "Traite", "Online");
		 comboBoxpayeravence = new ComboBox(); 
		 comboBoxpayeravence.getItems().setAll("yes", "no");
		    //title.bind(titleTextField.textProperty());
		 comboBoxbank = new ComboBox(); 
		 comboBoxbank.getItems().setAll("attijariwafabank", "banquePopulaire","BMCE","CIH");
		 comboBoxbank1 = new ComboBox(); 
		 comboBoxbank1.getItems().setAll("attijariwafabank", "banquePopulaire","BMCE","CIH");
		 comboBoxbank1.setVisible(false);
		 //init colum
		 idCol.setCellValueFactory(
				    new PropertyValueFactory<Paiment,String>("date"));
				desCol.setCellValueFactory(
				    new PropertyValueFactory<Paiment, String>("Montant"));
				prixColA.setCellValueFactory(
				    new PropertyValueFactory<Paiment,String>("type1"));
				prixColV.setCellValueFactory(
					    new PropertyValueFactory<Paiment,String>("numc"));
				prixColVe.setCellValueFactory(
					    new PropertyValueFactory<Paiment,String>("etat"));
				idColv.setCellValueFactory(
					    new PropertyValueFactory<Vente,String>("id"));
					totalCol.setCellValueFactory(
					    new PropertyValueFactory<Vente, String>("total"));
					dateCol.setCellValueFactory(
					    new PropertyValueFactory<Vente,String>("date"));
					cliCol.setCellValueFactory(
						    new PropertyValueFactory<Vente,String>("c"));
					
					 idColl.setCellValueFactory(
							    new PropertyValueFactory<LigneCommande,String>("id"));
					 prixColVl.setCellValueFactory(
							    new PropertyValueFactory<LigneCommande,String>("p"));
					 prixColVlv.setCellValueFactory(
							    new PropertyValueFactory<LigneCommande,String>("prix"));
							desColl.setCellValueFactory(
							    new PropertyValueFactory<LigneCommande, String>("qt"));
							prixColAl.setCellValueFactory(
							    new PropertyValueFactory<LigneCommande,String>("subTotal"));
							
							
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
		launch(args);
	}

}

