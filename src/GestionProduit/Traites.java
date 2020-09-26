package GestionProduit;

import java.util.ArrayList;
import java.util.List;

public class Traites implements ModePaiment {
	private long id;
	private double mantant;
	private String date_prevue; 
	private String date_effective; 
	private Cheque c;
	private String etat;
	
	public Traites( double mantant, String date_prevue, String date_effective, Cheque c) {
		
		this.mantant = mantant;
		this.date_prevue = date_prevue;
		this.date_effective = date_effective;
		this.c = c;
		//this.etat=etat;
	}
	public Traites( double mantant, String date_prevue, String date_effective) {
		
		this.mantant = mantant;
		this.date_prevue = date_prevue;
		this.date_effective = date_effective;
	
		//this.etat=etat;
	}
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Traites() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public double getMontant() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setMontant(double s) {
		// TODO Auto-generated method stub
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getMantant() {
		return mantant;
	}
	public void setMantant(double mantant) {
		this.mantant = mantant;
	}
	public String getDate_prevue() {
		return date_prevue;
	}
	public void setDate_prevue(String date_prevue) {
		this.date_prevue = date_prevue;
	}
	public String getDate_effective() {
		return date_effective;
	}
	public void setDate_effective(String date_effective) {
		this.date_effective = date_effective;
	}
	public Cheque getC() {
		return c;
	}
	public void setC(Cheque c) {
		this.c = c;
	}

	
}
