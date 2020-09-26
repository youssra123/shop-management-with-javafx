package GestionProduit;

public class LigneCommande {
private	long id;
private	int qt;
private	double subTotal;
private	Produit p;
private Vente v;
private double prix;
public String nomp;
public LigneCommande(long id, int qt, Produit p,Vente v) {
	this.id = id;
	this.qt = qt;
	this.p = p;
	this.v=v;
	this.subTotal=qt*p.getPrix_V();
	this.prix=p.getPrix_V();
	this.nomp=p.getDesigniation();
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}
public LigneCommande(long id, int qt, Produit p) {
	this.id = id;
	this.qt = qt;
	this.p = p;
	this.subTotal=qt*p.getPrix_V();
	this.nomp=p.getDesigniation();
}
public Vente getV() {
	return v;
}
public void setV(Vente v) {
	this.v = v;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public int getQt() {
	return qt;
}
public void setQt(int qt) {
	this.qt = qt;
}
public double getSubTotal() {
	return subTotal;
}
public void setSubTotal(double subTotal) {
	this.subTotal = subTotal;
}
public Produit getP() {
	return p;
}
public String getPD() {
	return p.getDesigniation();
}
public void setP(Produit p) {
	this.p = p;
}
@Override
public String toString() {
	return "LigneCommande [id=" + id + ", qt=" + qt + ", subTotal=" + subTotal + ", p=" + p + ", v=" + v + "]";
}

	

}
