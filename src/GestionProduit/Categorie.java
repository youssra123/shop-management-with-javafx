package GestionProduit;

import java.util.List;

public class Categorie {
	private long id;
	private String nom;
	
	public Categorie(long id, String nom) {
		this.id = id;
		this.nom = nom;
		
	}

	public Categorie( String nom) {
		
		this.nom = nom;
		
	}
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + "]";
	}

}
