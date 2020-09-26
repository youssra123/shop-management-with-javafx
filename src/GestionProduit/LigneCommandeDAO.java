package GestionProduit;

import java.util.List;

public interface LigneCommandeDAO extends DAO<LigneCommande>{
    public LigneCommande find(long id);
    public boolean create(LigneCommande c);
    public void delete(LigneCommande c);
    public void update(LigneCommande c);
    public List<LigneCommande> findAll();
    public List<LigneCommande> findAll(String key);
   // public List<LigneCommande> verif(List<LigneCommande>lc,LigneCommande ligne);
    public List<LigneCommande> findAllc(long idv);
    public List<LigneCommande> findcl(long idv);
}