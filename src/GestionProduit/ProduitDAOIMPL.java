package GestionProduit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOIMPL extends AbstractDAO implements ProduitDAO  {
private CategorieDAO ca=new CategorieDAOIMPL();
	

	@Override
	public Produit find(long id) {
		String  sql="select * from produit where id='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				return new Produit(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV"),ca.find(res.getInt("id_categorie")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean create(Produit produit) {
		boolean b = false;
		String sql = "insert into produit(id,des,prixA,prixV,id_categorie) values (   '" +produit.getId()+"'   ,   '" + produit.getDesigniation() + "','"
				+ produit.getPrix_A() + "', '" + produit.getPrix_V() + "', '" + produit.getCa().getId() + "')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}

	@Override
	public List<Produit> findAll() {
		List<Produit> list=new ArrayList<Produit>();
		String sql="select * from produit";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				list.add(new Produit(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV"),ca.find(res.getLong("id_categorie"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	/*public List<Produit> findAll(String key) {
		List<Produit> list=new ArrayList<Produit>();
		String sql="select * from produit where des like '"+key+"'";
		try {
			statement = connection.createStatement();
			ResultSet res=statement.executeQuery(sql);
			while(res.next()) {
				list.add(new Produit(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}	*/
	public List<Produit> findAll(String key) {
		List<Produit> list=findAll();
		List<Produit> list1=new ArrayList<Produit>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getDesigniation().contains(key)) {
				list1.add(new Produit(list.get(i).getId(), list.get(i).getDesigniation(), list.get(i).getPrix_A(), list.get(i).getPrix_V(),list.get(i).getCa() ));
			}
		}
		/*String sql="select * from produit where des like '"+key+"'";
		try {
			statement = connection.createStatement();
			ResultSet res=statement.executeQuery(sql);
			while(res.next()) {
				list.add(new Produit(res.getLong("id"), res.getString("des"), res.getDouble("prixA"), res.getDouble("prixV") ));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return list1;
		
	}

	@Override
	public void delete(Produit produit) {
		try {
			
			getStatement().execute("delete from produit where id='" +produit.getId( )+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Produit p) {
		String sql = "update produit set des='" + p.getDesigniation()+ "',prixA='" + p.getPrix_A()+ "',prixV='" + p.getPrix_V()+ "',id_categorie='" + p.getCa().getId()
				+ "'  where id='" + p.getId() + "'  ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public long getid() {
		String  sql="select MAX(id) from produit";
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
