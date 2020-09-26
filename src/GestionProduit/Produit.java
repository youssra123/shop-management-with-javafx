package GestionProduit;


public class Produit {
	private long id;
	private String designiation;
	private double prix_A;
	private double prix_V;
	private Categorie ca;
	private String catt;


	public String getCatt() {
		return catt;
	}
	public void setCatt(String cat) {
		this.catt = catt;
	}
	public Categorie getCa() {
		return ca;
	}
	public void setCa(Categorie ca) {
		this.ca = ca;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesigniation() {
		return designiation;
	}
	public void setDesigniation(String designiation) {
		this.designiation = designiation;
	}
	public double getPrix_A() {
		return prix_A;
	}
	public void setPrix_A(double prix_A) {
		this.prix_A = prix_A;
	}
	public double getPrix_V() {
		return prix_V;
	}
	public void setPrix_V(double prix_V) {
		this.prix_V = prix_V;
	}
	public Produit(long id, String designiation, double prix_A, double prix_V,Categorie ca) {

		this.id = id;
		this.designiation = designiation;
		this.prix_A = prix_A;
		this.prix_V = prix_V;
		this.ca=ca;
		this.catt=ca.getNom();
	}
	@Override
	public String toString() {
		return  designiation ;
	}

}