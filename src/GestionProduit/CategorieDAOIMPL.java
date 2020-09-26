package GestionProduit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOIMPL extends AbstractDAO implements CategorieDAO {

	

	@Override
	public Categorie find(long id) {
		String  sql="select * from categorie where id="+id;
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				return new Categorie(res.getLong("id"), res.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean create(Categorie client) {
		boolean b = false;
		String sql = "insert into categorie(id,nom) values (   '" +client.getId()+"'   ,   '" + client.getNom() + "')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}

	@Override
	public List<Categorie> findAll() {
		List<Categorie> list=new ArrayList<Categorie>();
		String sql="select * from categorie";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				list.add(new Categorie(res.getLong("id"), res.getString("nom")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	/*public List<Client> findAll(String key) {
		List<Client> list=new ArrayList<Client>();
		String sql="select * from Client where des like '"+key+"'";
		try {
			statement = connection.createStatement();
			ResultSet res=statement.executeQuery(sql);
			while(res.next()) {
				list.add(new Client(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}	*/
	public List<Categorie> findAll(String key) {
		List<Categorie> list=findAll();
		List<Categorie> list1=new ArrayList<Categorie>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getNom().contains(key) ) {
				list1.add(new Categorie(list.get(i).getId(), list.get(i).getNom() ));
			}
		}
		/*String sql="select * from Client where des like '"+key+"'";
		try {
			statement = connection.createStatement();
			ResultSet res=statement.executeQuery(sql);
			while(res.next()) {
				list.add(new Client(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return list1;
		
	}
	public Categorie findAllcat(String key) {
		List<Categorie> list=findAll();
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getNom().equals(key) ) {
				System.out.println(list.get(i));
			return new Categorie(list.get(i).getId(), list.get(i).getNom());
			}
		}
		/*String sql="select * from Client where des like '"+key+"'";
		try {
			statement = connection.createStatement();
			ResultSet res=statement.executeQuery(sql);
			while(res.next()) {
				list.add(new Client(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return null;
		
	}

	@Override
	public void delete(Categorie client) {
		try {
			
			getStatement().execute("delete from categorie where id='" +client.getId( )+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Categorie p) {
		String sql = "update categorie set nom='" + p.getNom()+ "'  where id='" + p.getId() + "'  ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public long getid() {

		String  sql="select MAX(id) from categorie ";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				return res.getLong("MAX(id)")+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;

	}
	

}
