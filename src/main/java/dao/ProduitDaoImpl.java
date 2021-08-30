package dao;

import java.util.List;

import javax.persistence.EntityManager;

//public class ProduitTdsiImpl {
//package dao;
	
import javax.persistence.EntityTransaction;


	import metier.entities.Produit;
import util.JpaUtil;
	
	public class ProduitDaoImpl implements IProduitDao {
		private EntityManager entityManager=JpaUtil.getEntityManager("Gestion_produits");
	@Override
	public Produit save(Produit p) {
	//EntityTransaction tx = entityManager.getTransaction();
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(p);
	tx.commit();
	return p;
}
	
@Override

public List<Produit> ProduitsParMC(String mc) {
	List<Produit> prods = entityManager.createQuery("select p from Produit p where p.nomProduit like :mc").setParameter("mc", "%"+mc+"%")
			.getResultList();
	return prods;
}
@Override
public Produit getProduit(Long id) {
	return entityManager.find(Produit.class, id);
//	Connection conn=SingletonConnection.getConnection();
//	Produit p = new Produit();
//	try {
//		PreparedStatement ps= conn.prepareStatement("select * from PRODUIT  where ID_PRODUIT = ?");
//		ps.setLong(1, id);
//		ResultSet rs = ps.executeQuery();
//		if (rs.next()) {
//			p.setIdProduit(rs.getLong("ID_PRODUIT"));
//			p.setNomProduit(rs.getString("NOM_PRODUIT"));
//			p.setPrix(rs.getDouble("PRIX"));
//		}
//	} catch (SQLException e) {
//	e.printStackTrace();
//	}
//	return p;
	
}
@Override
public Produit updateProduit(Produit p) {
	
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(p);
	tx.commit();
	return p;
	
//	Connection conn=SingletonConnection.getConnection();
//	try {
//		PreparedStatement ps= conn.prepareStatement("UPDATE PRODUIT SET NOM_PRODUIT=?,PRIX=? WHERE ID_PRODUIT=?");
//		ps.setString(1, p.getNomProduit());
//		ps.setDouble(2, p.getPrix());
//		ps.setLong(3, p.getIdProduit());
//		ps.executeUpdate();
//		ps.close();
//	} catch (SQLException e) {
//	e.printStackTrace();
//	}
//	return p;
	
}
@Override
public void deleteProduit(Long id) {
	
	Produit produit = entityManager.find(Produit.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(produit);
	entityManager.getTransaction().commit();
	
////TODO Auto-generated method stub
//	Connection conn=SingletonConnection.getConnection();
//	try {
//		PreparedStatement ps= conn.prepareStatement("DELETE FROM PRODUIT WHERE ID_PRODUIT = ?");
//		ps.setLong(1, id);
//		ps.executeUpdate();
//		ps.close();
//	} catch (SQLException e) {
//	e.printStackTrace();
//	}
}
}