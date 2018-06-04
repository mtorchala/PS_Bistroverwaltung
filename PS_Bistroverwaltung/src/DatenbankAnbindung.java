import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatenbankAnbindung {
	
	String dburl = "jdbc:sqlite:bistroverwaltung.db";

	public void createAllTables() {
      
		String sql = "CREATE TABLE IF NOT EXISTS gericht (\n"
                + "	gericht_id integer PRIMARY KEY,\n"
                + "	name text,\n"
                + "	preis real,\n"
                + "	kategorie_id integer"
                + ");";
		
		String sql1 = "CREATE TABLE IF NOT EXISTS kategorie (\n"
                + "	kategorie_id integer PRIMARY KEY,\n"
                + "	name text"
                + ");";
		
		String sql2 = "CREATE TABLE IF NOT EXISTS bestellung (\n"
                + "	bestellung_id integer PRIMARY KEY,\n"
                + "	datum date"
                + ");";
		
		String sql3 = "CREATE TABLE IF NOT EXISTS gericht_bestellung (\n"
                + "	gericht_id integer,\n"
                + "	bestellung_id integer,\n"
                + "	anzahl integer"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(dburl);
                Statement stmt = conn.createStatement()) {
          
            stmt.execute(sql);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void insertIntoGericht(String name, double preis, int kategorieid) throws SQLException{
		String sql = "INSERT INTO gericht(name,preis,kategorie_id) VALUES(?,?,?)";
		 
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setDouble(2, preis);
        pstmt.setDouble(3, kategorieid);
        pstmt.executeUpdate();
	}
	
	public void insertIntoBestellung(Date datum) throws SQLException{
		String sql = "INSERT INTO bestellung(datum) VALUES(?)";
		 
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1,datum);
        pstmt.executeUpdate();
   
	}
	
	public void insertIntoKategorie(int kategorieid,String name) throws SQLException{
		String sql = "INSERT INTO kategorie(kategorie_id,name) VALUES(?,?)";
		 
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, kategorieid);
        pstmt.setString(2, name);
        pstmt.executeUpdate();
   
	}
	
	public void insertIntoGerichtBestellung(int bestellungid, int gerichtid, int anzahl) throws SQLException{
		String sql = "INSERT INTO gericht_bestellung(bestellung_id,gericht_id,anzahl) VALUES(?,?,?)";
		 
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, bestellungid);
        pstmt.setInt(2, gerichtid);
        pstmt.setInt(3, anzahl);
        pstmt.executeUpdate();
   
	}
	
	public void selectAlleGerichteBestellungen() throws SQLException{
	       
		String sql = "SELECT * FROM gericht_bestellung";
		Connection conn = connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
            
	    while (rs.next()) {
	    	
	    System.out.println(rs.getInt("bestellung_id") +  "\t" + 
	                       rs.getDouble("gericht_id") + "\t" +
	                       rs.getInt("anzahl"));
	    }
       
    }
	
	public void selectAlleKategorien() throws SQLException{
	       
		String sql = "SELECT * FROM kategorie";
		Connection conn = connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
            
	    while (rs.next()) {
	    	System.out.println(rs.getString("name"));
	    }
       
    }
	
	public ObservableList<Gericht> selectAlleGerichte() throws SQLException{
       
		ObservableList<Gericht> gerichtListe = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM gericht";
		Connection conn = connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
            
	    while (rs.next()) {
	    	
	    	gerichtListe.add(new Gericht(rs.getString("name"),
	                       rs.getDouble("preis"),
	                       rs.getInt("kategorie_id")));
	    }
       
	    return gerichtListe;
    }
	
	public void selectAlleBestellungen() throws SQLException{
	       
		String sql = "SELECT * FROM bestellung";
		Connection conn = connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
            
	    while (rs.next()) {
	    	System.out.println(rs.getString("datum"));
	    }
       
    }
	
	
	
	private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
	
}

