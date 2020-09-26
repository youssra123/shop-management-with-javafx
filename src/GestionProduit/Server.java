package GestionProduit;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import com.sun.xml.internal.ws.handler.ServerSOAPHandlerTube;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Server extends AbstractDAO{
	ServerSocket server=null;
	ObjectInputStream ois;
	InputStream in;
	OutputStream out;
	private final int port=1234;
    public Server() throws Exception {
    	try {
			server=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	System.out.println("je sui la 2");
		Socket soc=server.accept();
		System.out.println("7");
		in=soc.getInputStream();
		out=soc.getOutputStream();
		System.out.println("8");
		ois=new ObjectInputStream(in);
		System.out.println("9");
		Online on=(Online)ois.readObject();
		System.out.println("10");
		System.out.println(on.toString());
		if (Comparer(find(on.getNum_carte(), on.getCode()),on)==1) {
			out.write(1);
			out.flush();
		}else {
			out.write(0);
			out.flush();
		}
		soc.close();
		server.close();
		
    }
    public void accepterConnexion() throws Exception {
    
    }

    public static void main(String[] args) throws Exception {
    	System.out.println("demarrer le serveur");
    	//launch(args);
		Server s=new Server();
		
	}

	public Online find(long num,long code) {
		String  sql="select * from banque where num='"+num+ "' and code='"+code+ "'";
		try {
			
			ResultSet res=getStatement().executeQuery(sql);
			if(res.next()) {

				return new Online(res.getLong("num"), res.getLong("code"), res.getDouble("mantant"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	private void update(Online o) {
		String sql = "update banque set mantant='" + o.getMontant()+ "' where code='" + o.getCode() + "' and num='" + o.getNum_carte() + "' ";
		try {
		
			getStatement() .execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private int Comparer(Online o1,Online o2) {
	if(o1.getMontant()>=o2.getMontant()) {
		o1.setMontant(o1.getMontant()-o2.getMontant());
		update(o1);
		return 1;
	}
	return 0;

}

}
