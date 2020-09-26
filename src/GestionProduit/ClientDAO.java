package GestionProduit;

import java.util.List;

public interface ClientDAO extends DAO<Client>{
    public Client find(long id);
    public boolean create(Client c);
    public void delete(Client c);
    public void update(Client c);
    public List<Client> findAll();
    public List<Client> findAll(String key);
    public long getid();
    
}