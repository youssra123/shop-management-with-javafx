package GestionProduit;

import java.util.List;

public interface CategorieDAO extends DAO<Categorie>{
    public Categorie find(long id);
    public boolean create(Categorie c);
    public void delete(Categorie c);
    public void update(Categorie c);
    public List<Categorie> findAll();
    public List<Categorie> findAll(String key);
    public long getid();
    public Categorie findAllcat(String key);
}
