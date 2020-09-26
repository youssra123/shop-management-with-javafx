package GestionProduit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeDAOIMP extends AbstractDAO implements LigneCommandeDAO {
private ProduitDAOIMPL pimpl=new ProduitDAOIMPL();
private VenteDAOIMP pimpv=new VenteDAOIMP();
private double t=0;

	@Override
	public LigneCommande find(long id) {
		String  sql="select * from ligneCommande where id='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				return new LigneCommande(res.getLong("id"), res.getInt("qt"), pimpl.find(res.getInt("id_produit")), pimpv.find(res.getInt("id_v")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}
	@Override
	public List<LigneCommande> findcl(long id) {
		List<LigneCommande> list=new ArrayList<LigneCommande>();
		String sql="select * from ligneCommande where id_v='"+id+ "'";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				t=t+pimpl.find(res.getInt("id_produit")).getPrix_A();
				list.add(new LigneCommande(res.getLong("id"), res.getInt("qt"), pimpl.find(res.getInt("id_produit")), pimpv.find(res.getInt("id_v"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	@Override
	public boolean create(LigneCommande client) {
		boolean b = false;
		String sql = "insert into ligneCommande(id,qt,subTotal,id_produit,id_v) values (   '" +client.getId()+"'   ,   '" + client.getQt() + "','"
				+ client.getSubTotal() + "', '" + client.getP().getId()+ "', '" + client.getV().getId() + "')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}

	@Override
	public List<LigneCommande> findAll() {
		List<LigneCommande> list=new ArrayList<LigneCommande>();
		String sql="select * from ligneCommande";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
			
				list.add(new LigneCommande(res.getLong("id"), res.getInt("qt"), pimpl.find(res.getInt("id_produit")), pimpv.find(res.getInt("id_v"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	@Override
	public List<LigneCommande> findAllc(long idv) {
		List<LigneCommande> list=new ArrayList<LigneCommande>();
		String sql="select * from ligneCommande where id_v="+idv+"" ;
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				list.add(new LigneCommande(res.getLong("id"), res.getInt("qt"), pimpl.find(res.getInt("id_produit")), pimpv.find(res.getInt("id_v"))));
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
	public List<LigneCommande> findAll(String key) {
		List<LigneCommande> list=findAll();
		List<LigneCommande> list1=new ArrayList<LigneCommande>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getP().getDesigniation().contains(key)  ) {
				list1.add(new LigneCommande(list.get(i).getId(), list.get(i).getQt(), list.get(i).getP(),list.get(i).getV()));
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

	@Override
	public void delete(LigneCommande client) {
		try {
			
			getStatement().execute("delete from ligneCommande where id='" +client.getId()+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(LigneCommande p) {
		String sql = "update ligneCommande set qt='" + p.getQt()+ "',subTotal='" + p.getSubTotal()+ "',id_produit='" + p.getP().getId()
				+ "'  where id='" + p.getId() + "'  ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*@Override
	public List<LigneCommande> verif(List<LigneCommande>lc,LigneCommande ligne) {
		for(int i=0;i<lc.size();i++) {
			if(lc.get(i).getId()!=ligne.getId() && lc.get(i).getP().getId()==ligne.getP().getId()) {
				lc.get(i).setQt(lc.get(i).getQt()+ligne.getQt());
				lc.get(i).setSubTotal(lc.get(i).getSubTotal()+ligne.getSubTotal());
				return lc;}
			if(lc.get(i).getId()==ligne.getId() && (lc.get(i).getQt()!=ligne.getQt()|| lc.get(i).getP().getId()!=ligne.getP().getId())) {
				lc.get(i).setP(ligne.getP());
				lc.get(i).setQt(ligne.getQt());
				lc.get(i).setSubTotal(ligne.getQt()*ligne.getP().getPrix_V());
				return lc;}
}return lc;}*/
	
	@Override
	public long getid() {
		String  sql="select MAX(id) from ligneCommande ";
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

