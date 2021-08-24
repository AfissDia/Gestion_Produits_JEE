
package test;

import java.util.List;

import metier.entities.Produit;
import dao.ProduitDaoImpl;

public class Test {
	
	public static void main(String[] args) {
		ProduitDaoImpl pdao= new ProduitDaoImpl();
		
		Produit prod= pdao.save(new Produit("tangual",2800));
		System.out.println(prod);
		List<Produit> prods =pdao.ProduitsParMC("bn");
		for (Produit p : prods)
		System.out.println(p);
}
}