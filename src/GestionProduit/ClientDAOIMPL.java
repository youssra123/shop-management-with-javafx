package GestionProduit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOIMPL extends AbstractDAO implements ClientDAO {

	

	@Override
	public Client find(long id) {
		String  sql="select * from client where id='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				return new Client(res.getLong("id"), res.getString("nom"), res.getString("prenom"), res.getString("tel"),res.getString("email"),res.getString("adress"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean create(Client client) {
		boolean b = false;
		String sql = "insert into client(id,nom,prenom,tel,email,adress) values (   '" +client.getId()+"'   ,   '" + client.getNom() + "','"
				+ client.getPrenom() + "', '" + client.getTel() + "', '" + client.getEmail() + "', '" + client.getAdresse() + "')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}

	@Override
	public List<Client> findAll() {
		List<Client> list=new ArrayList<Client>();
		String sql="select * from client";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				list.add(new Client(res.getLong("id"), res.getString("nom"), res.getString("prenom"), res.getString("tel"),res.getString("email"),res.getString("adress")));
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
	public List<Client> findAll(String key) {
		List<Client> list=findAll();
		List<Client> list1=new ArrayList<Client>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getNom().contains(key) || list.get(i).getPrenom().contains(key) ) {
				list1.add(new Client(list.get(i).getId(), list.get(i).getNom(), list.get(i).getPrenom(), list.get(i).getTel(),list.get(i).getEmail(),list.get(i).getAdresse() ));
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
	public void delete(Client client) {
		try {
			
			getStatement().execute("delete from client where id='" +client.getId( )+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Client p) {
		String sql = "update client set nom='" + p.getNom()+ "',prenom='" + p.getPrenom()+ "',tel='" + p.getTel()
				+"',email='" + p.getEmail()+"',adress='" + p.getAdresse()+ "'  where id='" + p.getId() + "'  ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public long getid() {

		String  sql="select MAX(id) from client ";
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
