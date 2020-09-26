package GestionProduit;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		
//			DataConnection dataConnection=ne
			ProduitDAO produitDao=new ProduitDAOIMPL();
			System.out.println(produitDao.find(1));
			
}
}
