package GestionProduit;

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;

public class Paiment {
	long id;
	private long id_vente;
	private String date;
	private ModePaiment type;
	private Cheque c;
	private Espece es;
	private Online on;
	private Traites t;
	private double Montant;
	private String type1;
	private String etat;
	private long numc;
	private long code;
	private long numcarte;
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}

	public Paiment(long id,long id_vente, String date, String type1,Cheque c) {
		this.id=id;
		this.id_vente = id_vente;
		this.type1=type1;
		this.date = date;
		this.c=c;
		this.es=null;
		this.t=null;
		this.Montant=c.getMontant();
		this.etat=c.getEtat();
		this.numc=c.getNum();
		System.out.println("Montant="+Montant +",etat="+etat+",numc="+numc);
		}
	public Paiment(long id,long id_vente, String date, String type1,Espece es) {
		this.id=id;
		this.id_vente = id_vente;
		this.type1=type1;
		this.date = date;
		this.c=null;
		this.t=null;
		this.es=es;
		this.Montant=this.es.getMontant();
		this.etat=this.es.getEtat();
		}
	public Paiment(long id,long id_vente, String date, String type1,Online on) {
		this.id=id;
		this.id_vente = id_vente;
		this.type1=type1;
		this.date = date;
		setMontant(on.getMontant());
		this.c=null;
		this.t=null;
		this.es=null;
		this.on=on;
		this.Montant=this.on.getMontant();
		this.numcarte=on.getNum_carte();
		this.etat=this.on.getEtat();
		
		}
	public Paiment(long id,long id_vente, String date, String type1,Traites t) {
		this.id=id;
		this.id_vente = id_vente;
		this.type1=type1;
		this.date = date;
		this.c=null;
		this.es=null;
		this.t=t;
		this.Montant=this.t.getMontant();
		
		}
	public Paiment(long id,long id_vente, String date, String type1) {
		this.id=id;
		this.id_vente = id_vente;
		this.type1=type1;
		this.date = date;
		if(type1.equals("Cheque")) {
			this.c=new Cheque();
		
		}
		if(type1.equals("Traite")) {
			this.t=new Traites();

			
		}
		if(type1.equals("Espece")) {
			this.es=new Espece(); 
		}
		if(type1.equals("Online")) {
			this.on=new Online(); 
		}
	
	}
	public double getMontant() {
		return Montant;
	}
	public void setMontant(double montant) {
		Montant = montant;
	}

	public long getNumc() {
		return numc;
	}
	public void setNumc(long numc) {
		this.numc = numc;
	}
	public Cheque getC() {
		return c;
	}
	public void setC(Cheque c) {
		this.c = c;
	}
	public Espece getEs() {
		return es;
	}
	public void setEs(Espece es) {
		this.es = es;
	}
	public Online getOn() {
		return on;
	}
	public void setOn(Online on) {
		this.on = on;
	}
	public Traites getT() {
		return t;
	}
	public void setT(Traites t) {
		this.t = t;
	}
	public Paiment( long id, String date, String type1) {
		this.id = id;
		this.date = date;
		this.type1 = type1;
	
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_vente() {
		return id_vente;
	}
	public void setId_vente(long id_vente) {
		this.id_vente = id_vente;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ModePaiment getType() {
		return type;
	}
	public void setType(ModePaiment type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Paiment [id=" + id + ", id_vente=" + id_vente + ", date="
				+ date + ", type1=" + type1 +", type=" + type +", Montant=" + Montant +", online=" + on + "]";
	}
	





}
