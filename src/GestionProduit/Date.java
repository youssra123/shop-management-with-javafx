package GestionProduit;

public class Date {
private int jour;
private int annee;
private int mois;
@Override
public String toString() {
	return "Date [jour=" + jour + ", annee=" + annee + ", mois=" + mois + "]";
}
public int getJour() {
	return jour;
}
public void setJour(int jour) {
	this.jour = jour;
}
public int getAnnee() {
	return annee;
}
public void setAnnee(int annee) {
	this.annee = annee;
}
public int getMois() {
	return mois;
}
public void setMois(int mois) {
	this.mois = mois;
}
public Date(int jour, int annee, int mois) {
	super();
	this.jour = jour;
	this.annee = annee;
	this.mois = mois;
}
}
