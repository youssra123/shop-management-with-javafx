package GestionProduit;

import java.util.List;

public interface DAO<T> {
	 public T find(long id);
    public boolean create(T t);
    public void delete(T t);
    public void update(T t);
    public List<T> findAll();
    public List<T> findAll(String key);
    public long getid();
}
