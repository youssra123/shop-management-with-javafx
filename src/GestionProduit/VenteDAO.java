package GestionProduit;

import java.util.List;

public interface VenteDAO extends DAO<Vente>{
    public Vente find(long id);
    public boolean create(Vente c);
    public void delete(Vente c);
    public void update(Vente c);
    public List<Vente> findAll();
    public List<Vente> findAll(String key);
    public List<Vente> findcv(long id);
  
    
}