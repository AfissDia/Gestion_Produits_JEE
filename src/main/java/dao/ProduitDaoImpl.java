package dao;

//public class ProduitTdsiImpl {
//package dao;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import metier.SingletonConnection;
	import metier.entities.Produit;
	
public class ProduitDaoImpl implements IProduitDao {
	@Override
	public Produit save(Produit p) {
	Connection conn=SingletonConnection.getConnection();
	try {
	PreparedStatement ps= conn.prepareStatement("INSERT INTO PRODUIT (NOM_PRODUIT,PRIX) VALUES(?,?)");
		ps.setString(1, p.getNomProduit());
		ps.setDouble(2, p.getPrix());
		ps.executeUpdate();
		
	PreparedStatement ps2= conn.prepareStatement
	("SELECT MAX(ID_PRODUIT) as MAX_ID FROM PRODUIT");
	ResultSet rs =ps2.executeQuery();
	if (rs.next()) {
	p.setIdProduit(rs.getLong("MAX_ID"));
	}
	ps.close();
	ps2.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return p;
}
	
@Override
public List<Produit> ProduitsParMC(String mc) {
List<Produit> prods= new ArrayList<Produit>();
Connection conn=SingletonConnection.getConnection();
try {
	PreparedStatement ps= conn.prepareStatement("select * from PRODUIT where NOM_PRODUIT LIKE ?");
	ps.setString(1, "%"+mc+"%");
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	Produit p = new Produit();
	p.setIdProduit(rs.getLong("ID_PRODUIT"));
	p.setNomProduit(rs.getString("NOM_PRODUIT"));
	p.setPrix(rs.getDouble("PRIX"));
	prods.add(p);
	}
} catch (SQLException e) {
	e.printStackTrace();
}
	return prods;
}
@Override
public Produit getProduit(Long id) {
	Connection conn=SingletonConnection.getConnection();
	Produit p = new Produit();
	try {
		PreparedStatement ps= conn.prepareStatement("select * from PRODUIT  where ID_PRODUIT = ?");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			p.setIdProduit(rs.getLong("ID_PRODUIT"));
			p.setNomProduit(rs.getString("NOM_PRODUIT"));
			p.setPrix(rs.getDouble("PRIX"));
		}
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return p;
	
}
@Override
public Produit updateProduit(Produit p) {
	Connection conn=SingletonConnection.getConnection();
	try {
		PreparedStatement ps= conn.prepareStatement("UPDATE PRODUIT SET NOM_PRODUIT=?,PRIX=? WHERE ID_PRODUIT=?");
		ps.setString(1, p.getNomProduit());
		ps.setDouble(2, p.getPrix());
		ps.setLong(3, p.getIdProduit());
		ps.executeUpdate();
		ps.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return p;
}
@Override
public void deleteProduit(Long id) {
//TODO Auto-generated method stub
	Connection conn=SingletonConnection.getConnection();
	try {
		PreparedStatement ps= conn.prepareStatement("DELETE FROM PRODUIT WHERE ID_PRODUIT = ?");
		ps.setLong(1, id);
		ps.executeUpdate();
		ps.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
}
}