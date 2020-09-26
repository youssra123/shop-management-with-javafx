package GestionProduit;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Emitter extends Thread{
	Socket s;

	public Emitter(Socket s) {
		this.s=s;
	}
	
	public void run() {
		PrintStream ps;
		try {
			ps = new PrintStream(s.getOutputStream());
			while(true) {
				ps.println( (new Scanner(System.in)).nextLine() );
				ps.flush();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
