package GestionProduit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
	DataConnection dataConnection;
	Statement statement=null;
	Connection connection=null;
	//=DataConnection.getDataConnection();
public AbstractDAO() {
	dataConnection=DataConnection.getDataConnection();

}
public Connection getConnection() {
	connection= dataConnection.getConnection();
	return connection;

}
public Statement getStatement() {
	try {
		statement= getConnection().createStatement();
		return statement;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return null;

}
}
