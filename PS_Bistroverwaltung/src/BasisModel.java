import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BasisModel {

	public void createAllTables() {
		
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.createAllTables();
		
	}

	public void insertIntoGericht(String name, double preis, int kategorieid) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.insertIntoGericht(name, preis, kategorieid);
		
	}
	
	public void insertIntoKategorie(String name,int kategorieid) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.insertIntoKategorie(kategorieid,name);
		
	}
	
	public int insertIntoBestellung(Date datum) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		return dba.insertIntoBestellung(datum);
		
	}
	
	public int selectGerichtID(Gericht gericht) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		return dba.selectGerichtID(gericht);
		
	}
	
	public void insertIntoGerichtBestellung(int bestellungid, int gerichtid, int anzahl) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.insertIntoGerichtBestellung(bestellungid, gerichtid, anzahl);
		
	}

	public ObservableList<Gericht> selectAlleGerichte() throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		return dba.selectAlleGerichte();
		
	}

	public void bestellungSpeichern(Bestellung bestellung) throws SQLException, InterruptedException {
		
		int bestellungid = insertIntoBestellung(bestellung.getDatum());
		System.out.println(bestellungid);
		
		if(bestellungid != -1){
			for (BestellungGericht bg : bestellung.getBestelltegerichte()){
				int gerichtid = selectGerichtID(bg.getGericht());
				insertIntoGerichtBestellung(bestellungid,gerichtid,bg.getAnzahl());
				Thread.sleep(100);
			}
		}
		
	}
	
	
}
