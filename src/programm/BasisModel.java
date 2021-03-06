package programm;
import java.sql.SQLException;
import java.util.Date;
import javafx.collections.ObservableList;

public class BasisModel {
	
	private static BasisModel instance;
	
	public BasisModel(){
		
		// Initate DB with demo values
		createAllTables();
		try {
			insertIntoKategorie("Hauptgericht", 1);
			
		} catch (SQLException e) {

			//e.printStackTrace();
		}
	}
	
	public static BasisModel getInstance(){
		if(instance == null){
			instance = new BasisModel();
		}
		return instance;
	}

	public void createAllTables() {
		
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.createAllTables();
		
	}

	public void insertIntoGericht(String name, double preis, int kategorieid) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.insertIntoGericht(name, preis, kategorieid);
		
	}
	
	public void updateGericht(int gerichtId, String name, double preis, int kategorieid) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.updateGericht(gerichtId, name, preis, kategorieid);
		
	}
	
	public void deleteGericht(int gerichtId) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.deleteGericht(gerichtId);
		
	}
	
	public void deleteBestellung(int bestellungId) throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		dba.deleteBestellung(bestellungId);
		
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

	public ObservableList<Bestellung> selectAlleBestellungen() throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		return dba.selectAlleBestellungen();
		
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
	
public ObservableList<BestellungGericht> bestellungLaden(int bestellungid) throws SQLException, InterruptedException {
		
		DatenbankAnbindung dba = new DatenbankAnbindung();
		return dba.selectBestellungenGerichte(bestellungid);
		
	}
	
	
}
