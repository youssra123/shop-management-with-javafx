package GestionProduit;

public class LigneDeCommande {
private int qt;
private double subTotal;
private Produit p;
public int getQt() {
	return qt;
}
@Override
public String toString() {
	return "LigneDeCommande [qt=" + qt + ", subTotal=" + subTotal + ", p=" + p + "]";
}
public LigneDeCommande(int qt, double subTotal, Produit p) {
	super();
	this.qt = qt;
	this.subTotal = subTotal;
	this.p = p;
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
public void setP(Produit p) {
	this.p = p;
}
}
