package GestionProduit;

import java.util.ArrayList;
import java.util.List;

public class Vente {
private long id;
private double total;
private String date;
//private List<LigneCommande> lp=new ArrayList<LigneCommande>();
private Client c;
@Override
public String toString() {
	return "Vente [id=" + id + ", total=" + total + ", date=" + date + ", c=" + c + "]";
}
public Vente(long id, double total, String date, Client c) {

	this.id = id;
	this.total = total;
	this.date = date;
	this.c = c;
}
public Vente(long id, String date, Client c) {

	this.id = id;
	this.date = date;
	this.c = c;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

public Client getC() {
	return c;
}
public void setC(Client c) {
	this.c = c;
}

}
