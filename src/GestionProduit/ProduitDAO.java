package GestionProduit;
import java.util.List;
import GestionProduit.DAO;
public interface ProduitDAO extends DAO<Produit>{
     public Produit find(long id);
     public boolean create(Produit produit);
     public void delete(Produit produit);
     public void update(Produit produit);
     public List<Produit> findAll();
     public List<Produit> findAll(String key);
     public long getid();
     
}
