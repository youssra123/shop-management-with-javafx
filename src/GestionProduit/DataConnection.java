package GestionProduit;
import java.sql.*;
public class DataConnection {
	private static DataConnection connectionsingle=null;
	private Connection connection;
	//Statement statement;
       private DataConnection() {
    	   String url = "jdbc:mysql://localhost:3306/java";
   		  try {
			  connection = DriverManager.getConnection(url, "root", "");
			  System.out.println("connection réussie....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		  
   		  
       }
       
       public Connection getConnection() {
    	   return connection;
       }
       
       public   static DataConnection getDataConnection() {
    	   if(connectionsingle == null) {
    		   connectionsingle=new DataConnection();
    	   }
		return connectionsingle;
       }
       
       
     
       
}
