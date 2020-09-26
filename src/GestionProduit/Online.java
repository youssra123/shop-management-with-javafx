package GestionProduit;

import java.io.Serializable;

public class Online implements Serializable,ModePaiment{
private long id;
private long num_carte;
private long code;
private double montant;
private String etat;


public Online(long num_carte, double montant,String etat) {

	this.num_carte = num_carte;
	this.montant = montant;
	this.etat=etat;
	//setEtat(etat);
}
public Online(long id, long num_carte, long code, double montant) {
	super();
	this.id = id;
	this.num_carte = num_carte;
	this.code = code;
	this.montant = montant;
}
public Online( long num_carte, long code, double montant) {
	
	this.num_carte = num_carte;
	this.code = code;
	this.montant = montant;
}
public Online() {
	
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getNum_carte() {
	return num_carte;
}
public void setNum_carte(long num_carte) {
	this.num_carte = num_carte;
}
public long getCode() {
	return code;
}
public void setCode(long code) {
	this.code = code;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}
@Override
public String toString() {
	return "Online [id=" + id + ", num_carte=" + num_carte + ", code=" + code + ", montant=" + montant + "]";
}

}
