package GestionProduit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaimentDAOIMP extends AbstractDAO implements PaimentDAO {
private int a=0;
private long g;
	@Override
	public Paiment find(long id) {
		String  sql="select * from paiment where id='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				return new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
		
		return null;
	}
	public Espece findespece(long id) {
		String  sql="select * from espece where id_paiment='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				
				return new Espece(res.getDouble("montant"),res.getString("etat"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
		
		return null;
	}

	public Cheque findcheque(long id) {
		String  sql="select * from cheque where id_paiment='"+id+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {
				
				return new Cheque(res.getLong("id"), res.getString("proprietaire"), res.getString("date"), res.getString("banque"), res.getDouble("montant"),res.getString("etat"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public boolean create(Paiment client) {
		boolean b = false;
		String sql = "insert into paiment(id,id_vente,date,type) values (    '" + client.getId() + "','" +client.getId_vente()+"', '" + client.getDate() +  "', '" + client.getType1()+ "')";
		
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	public boolean createEs(Paiment client) {
		boolean b = false;
		String sql = "insert into espece(montant,id_paiment,id_traite,etat) values (    '" + client.getType().getMontant() + "','" +client.getId()+"',0,'payer')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	public boolean createC(Paiment client) {
		boolean b = false;
		String sql = "insert into cheque(proprietaire,montant,date,banque,num,id_paiment,id_traite,etat) values (    '" + client.getC().getProprietaire()+ "','" +client.getC().getMontant()+"', '" + client.getC().getDate()+ "','" +client.getC().getBanque()+"','" +client.getC().getNum()+"','" +client.getId()+"',0,'payer')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	public boolean createO(Paiment client) {
		boolean b = false;
		
		String sql = "insert into online(num_carte,montant,etat,id_paiment) values (    '" + client.getOn().getNum_carte()+ "', '" + client.getOn().getMontant()+ "','payer', '" + client.getId()+ "')";
		System.out.println(sql);
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	public boolean createCheque(Paiment client) {
		boolean b = false;
	
		String sql = "insert into cheque(proprietaire,montant,date,banque,num,id_paiment,id_traite,etat) values ( '" + client.getT().getC().getProprietaire()+ "','" + client.getT().getC().getMontant()+"','','" + client.getT().getC().getBanque()+"','" + client.getT().getC().getNum()+"','"+client.getId()+"','"+ g+"','no payer')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	public boolean createT(Paiment client) {
		boolean b = false;
		g=getidT();
		String sql = "insert into traite(mantant,date_prevue,date_effective,id_paiment) values (    '" + client.getT().getMantant()+ "','" +client.getT().getDate_prevue()+"', '" + client.getT().getDate_effective()+ "','" +client.getId()+"')";
		try {
			
			 b=getStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createCheque(client);
		return b;
		
	}
	@Override
	public List<Paiment> findAll() {
		List<Paiment> list=new ArrayList<Paiment>();
	
		String sql="select * from paiment";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				if(res.getString("type").equals("Cheque")) {
					String  sql1="select * from cheque where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
					Cheque c=new Cheque(res1.getLong("num"), res1.getString("proprietaire"), res1.getString("date"), res1.getString("banque"), res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				if(res.getString("type").equals("Espece")) {
					String  sql1="select * from espece where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
					Espece c=new Espece(res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				if(res.getString("type").equals("Traite")) {
					String  sql1="select * from cheque where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
						Cheque c=new Cheque(res1.getLong("num"), res1.getString("proprietaire"), res1.getString("date"), res1.getString("banque"), res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				if(res.getString("type").equals("Online")) {
					String  sql1="select * from online where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
						Online c=new Online(res1.getLong("num_carte"), res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				a++;
				//list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<Paiment> findAllid(long id) {
		List<Paiment> list=new ArrayList<Paiment>();
	
		String sql="select * from paiment where id_vente="+id+"";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				if(res.getString("type").equals("Cheque")) {
					String  sql1="select * from cheque where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
					Cheque c=new Cheque(res1.getLong("num"), res1.getString("proprietaire"), res1.getString("date"), res1.getString("banque"), res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				if(res.getString("type").equals("Espece")) {
					String  sql1="select * from espece where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
					Espece c=new Espece(res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				if(res.getString("type").equals("Traite")) {
					String  sql1="select * from cheque where id_paiment='"+res.getLong("id")+ "'";	
					ResultSet res1=getStatement().executeQuery(sql1);
					while(res1.next()) {
						Cheque c=new Cheque(res1.getLong("num"), res1.getString("proprietaire"), res1.getString("date"), res1.getString("banque"), res1.getDouble("montant"),res1.getString("etat"));
					//System.out.println(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
					list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type"),c));
				}}
				a++;
				//list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public double calcul() {
		List<Paiment> list=new ArrayList<Paiment>();
		double s=0;
		String sql="select * from paiment";
		try {
		
			ResultSet res=getStatement().executeQuery(sql);
			while(res.next()) {
				s=s+res.getDouble("montant");
				list.add(new Paiment(res.getLong("id"), res.getLong("id_vente"), res.getString("date"), res.getString("type")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
		
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
	public List<Paiment> findAll(String key) {
		List<Paiment> list=findAll();
		List<Paiment> list1=new ArrayList<Paiment>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getDate().contains(key)) {
		list1.add(new Paiment(list.get(i).getId(),list.get(i).getId_vente(), list.get(i).getDate(), list.get(i).getType1()));
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
	
	public void delete(Paiment p) {
		try {
			
			getStatement().execute("delete from paiment where id='" +p.getId()+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updatees(Paiment p) {
		update(p);
		System.out.println(p.getEs());
		String sql = "update espece set montant='" + p.getEs().getMontant()+ "',etat='" + p.getEs().getEtat()
		+ "'  where id_paiment=" +p.getId() +" ";
try {

	getStatement() .execute(sql);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
	public void updatec(Paiment p) {
		update(p);
		String sql = "update cheque set montant='" + p.getC().getMontant()+ "',etat='" + p.getC().getEtat()+ "',num='" + p.getC().getNum()
		+ "'  where id_paiment='" +p.getId()+ "'  ";
try {

	getStatement() .execute(sql);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
	public void update(Paiment p) {
		System.out.println(p);
		String sql = "update paiment set date='" + p.getDate()+ "', type='" + p.getType1()
				+ "'  where id='" +p.getId() + "'  ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public long getid() {
		String  sql="select MAX(id) from paiment ";
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
	public long getidT() {
		String  sql="select MAX(id) from traite ";
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
