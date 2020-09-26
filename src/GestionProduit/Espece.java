package GestionProduit;

public class Espece implements ModePaiment{
private long id;
private double montant;
public String etat;
private long idp;
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}
public Espece(double montant,String etat) {
	// TODO Auto-generated constructor stub
	this.montant = montant;
	this.etat=etat;
}
public Espece(double montant,String etat,long idp) {
	// TODO Auto-generated constructor stub
	this.montant = montant;
	this.etat=etat;
	this.idp=idp;
}
public long getIdp() {
	return idp;
}
public void setIdp(long idp) {
	this.idp = idp;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
@Override
public double getMontant() {
	return montant;
}
@Override
public void setMontant(double montant) {
	this.montant = montant;
	this.etat=etat;
}
public Espece() {

}
@Override
public String toString() {
	return "Espece [id=" + id + ", montant=" + montant +  ", etat=" + etat + "]";
}



}
