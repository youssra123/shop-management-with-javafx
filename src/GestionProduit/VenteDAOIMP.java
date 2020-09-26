package GestionProduit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenteDAOIMP extends AbstractDAO implements VenteDAO {

	private ClientDAOIMPL pimpl=new ClientDAOIMPL();
	@Override
	public Vente find(long id) {
		String  sql="select * from vente where id='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {

				return new Vente(res.getLong("id"), res.getLong("total"), res.getString("date"),pimpl.find(res.getLong("id_cli")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean create(Vente client) {
		boolean b = false;
		String sql = "insert into vente(id,total,date,id_cli) values (   '" +client.getId()+"'   ,   '" + client.getTotal() + "','"
				+ client.getDate()+ "' , '" + client.getC().getId() + "')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}

	@Override
	public List<Vente> findAll() {
		List<Vente> list=new ArrayList<Vente>();
		String sql="select * from vente";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				list.add(new Vente(res.getLong("id"), res.getLong("total"), res.getString("date"),pimpl.find(res.getLong("id_cli"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	@Override
	public List<Vente> findcv(long id){
		List<Vente> list=new ArrayList<Vente>();
		String sql="select * from vente where id_cli="+id+"";
		String sql1="select * from paiment where id_vente=0";
		try {
			ResultSet res=getStatement().executeQuery(sql);
			//ResultSet res1=getStatement().executeQuery(sql1);
			while(res.next()) {
		//		while(res1.next()) {
					//if() {
						list.add(new Vente(res.getLong("id"), res.getLong("total"), res.getString("date"),pimpl.find(res.getLong("id_cli"))));
				//	}
				
			//}
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
	public List<Vente> findAll(String key) {
		List<Vente> list=findAll();
		List<Vente> list1=new ArrayList<Vente>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getDate().contains(key)  ) {
				list1.add(new Vente(list.get(i).getId(), list.get(i).getTotal(),list.get(i).getDate(),list.get(i).getC()));
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
	public void delete(Vente client) {
		try {
			
			getStatement().execute("delete from vente where id='" +client.getId( )+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Vente p) {
		String sql = "update vente set total='" + p.getTotal()+ "',date='" + p.getDate()+ "',id_cli='" + p.getC().getId()+"'  where id='" + p.getId() + "'  ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public long getid() {

		String  sql="select MAX(id) from vente ";
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

