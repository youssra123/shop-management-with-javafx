package GestionProduit;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Receiver extends Thread{
	Socket s;

	public Receiver(Socket s) {
		this.s=s;
	}
	
	public void run() {
		try {
			Scanner sc=new Scanner(s.getInputStream());
			
			while(true) {
			System.out.println(sc.nextLine());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
