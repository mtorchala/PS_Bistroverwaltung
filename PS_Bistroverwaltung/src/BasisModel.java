import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
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

	public ObservableList<Gericht> selectAlleGerichte() throws SQLException {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		return dba.selectAlleGerichte();
		
	}
	
	
}
