package GestionProduit;

import java.util.List;

public interface PaimentDAO extends DAO<Paiment>{
    public Paiment find(long id);
    public boolean create(Paiment c);
    public void delete(Paiment c);
    public void update(Paiment c);
    public List<Paiment> findAll();
    public List<Paiment> findAll(String key);
    
}
