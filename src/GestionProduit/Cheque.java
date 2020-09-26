package GestionProduit;

public class Cheque implements ModePaiment {
	private long id;
	private String proprietaire;
	private String date;
	private String banque;
	private long num;
	public double montant;
	public String etat;
	private long idp;
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Cheque() {
		
	}
	public Cheque(long num, String proprietaire, String date, String banque,double montant) {
		
		//this.id = id;
		this.proprietaire = proprietaire;
		this.date = date;
		this.banque = banque;
		this.num = num;
		this.montant = montant;
	}
	public Cheque(long num, String proprietaire, String date, String banque,double montant,String etat) {
	
		//this.id = id;
		this.proprietaire = proprietaire;
		this.date = date;
		this.banque = banque;
		this.num = num;
		this.montant = montant;
		this.etat=etat;
	}
	public Cheque(long num,double montant,String etat) {
		
		this.date = date;	
		this.num = num;
		this.montant = montant;
		this.etat=etat;

	}
	public long getIdp() {
		return idp;
	}
	public void setIdp(long idp) {
		this.idp = idp;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Cheque [num=" + num + ", proprietaire=" + proprietaire + ", date=" + date + ", banque=" + banque
				+ ", num=" + num + ", montant=" + montant + "]";
	}

	
}
