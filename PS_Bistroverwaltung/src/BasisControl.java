import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BasisControl implements Initializable{
	private BasisModel basisModel;
	
	@FXML
	private Button bttnAdd;
	
	@FXML
	private TableView<Gericht> tvGerichte;
	
	@FXML
	private TableView tvBestellung;
	
	@FXML
	private Label lblRechnung;
	
	private Bestellung bestellung;
	
	public BasisControl(){
		
		basisModel = new BasisModel();
		bestellung = new Bestellung(new Date());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BestellungenAufbereiten();
		gerichteEinlesen();
	}
	
	
	
	
	@FXML
	private void gerichtZurBestellungHinzufügen(){
		
		Gericht gericht = (Gericht) tvGerichte.getSelectionModel().getSelectedItem();
		
		if(gericht == null){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Fehler");
		    alert.setHeaderText("Fehler bei der Auswahl des Gerichts!");
		    alert.setContentText("Es wurde kein Gericht ausgewählt!");
		    alert.showAndWait();
		}
		else{	
			bestellung.fügeGerichtHinzu(gericht);
			tvBestellung.setItems(bestellung.getBestelltegerichte());
			tvBestellung.refresh();
			lblRechnung.setText("Kosten: " + bestellung.berechnePreis()+ " €");
		}
		
		
	}
	
	
	
	private void gerichteEinlesen(){
		tvGerichte.getColumns().clear();
		
		ObservableList<Gericht> ausgeleseneGerichte = null;
		try {
			ausgeleseneGerichte = basisModel.selectAlleGerichte();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TableColumn<Gericht, String> colName = new TableColumn<Gericht, String>("Name");
		colName.setMinWidth(175);
		colName.setCellValueFactory(new PropertyValueFactory<Gericht, String>("name"));
        
		TableColumn<Gericht, Double> colPreis = new TableColumn<Gericht, Double>("Preis");
		colPreis.setMinWidth(75);        
		colPreis.setCellValueFactory(new PropertyValueFactory<Gericht, Double>("preis"));
        
        
        tvGerichte.getColumns().addAll(colName,colPreis);
        
        
       
        tvGerichte.setItems(ausgeleseneGerichte);
	}
	
	private void BestellungenAufbereiten(){
		tvBestellung.getColumns().clear();
		TableColumn<BestellungGericht, String> colName = new TableColumn<BestellungGericht, String>("Name");
		colName.setMinWidth(175);
		
		TableColumn<BestellungGericht, String> colPreis = new TableColumn<BestellungGericht, String>("Preis");
		colPreis.setMinWidth(93);        
			
		TableColumn<BestellungGericht, Double> colAnzahl = new TableColumn<BestellungGericht, Double>("Anzahl");
		colAnzahl.setMinWidth(93);        
		colAnzahl.setCellValueFactory(new PropertyValueFactory<BestellungGericht, Double>("anzahl"));
		
		colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BestellungGericht, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<BestellungGericht, String> bestellunggericht) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(bestellunggericht.getValue().getGericht().getName());
			}
		});
		
		colPreis.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BestellungGericht, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<BestellungGericht, String> bestellunggericht) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(bestellunggericht.getValue().getGericht().getPreis()+"");
			}
		});
        
        tvBestellung.getColumns().addAll(colName,colPreis,colAnzahl);
        tvBestellung.setItems(bestellung.getBestelltegerichte());
	}
	
	@FXML
	public void bestellungVerwerfen(){
		bestellung = null;
		bestellung = new Bestellung(new Date());
		tvBestellung.getColumns().clear();
		BestellungenAufbereiten();
		tvBestellung.refresh();
		lblRechnung.setText("Kosten: " + bestellung.berechnePreis()+ " €");
	}
	
	@FXML
	public void gerichtAusBestellungEntfernen(){
		int index = tvBestellung.getSelectionModel().getSelectedIndex();
		if (index != -1){
			bestellung.entferneGericht(index);
			tvBestellung.setItems(bestellung.getBestelltegerichte());
			tvBestellung.refresh();
			lblRechnung.setText("Kosten: " + bestellung.berechnePreis()+ " €");
		}
	}
	
	@FXML
	public void bestellungSpeichern(){
		try {
			if(bestellung.getBestelltegerichte().size() > 0){
				basisModel.bestellungSpeichern(bestellung);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
