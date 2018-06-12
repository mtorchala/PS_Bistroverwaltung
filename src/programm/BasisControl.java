package programm;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;


public class BasisControl implements Initializable{
	private BasisModel basisModel;
	
	@FXML
	private Button bttnAdd;
	
	@FXML
	private TableView<Gericht> tvGerichte;
	
	@FXML
	private TableView<Gericht> tvGerichteEdit;
	
	@FXML
	private TableView tvBestellung;
	
	@FXML
	private ComboBox<Bestellung> cbxBestellung;
	
	@FXML
	private Label lblRechnung;
	
	@FXML
	private TextField txtEditName;
	@FXML
	private TextField  txtEditPreis;
	
	private Bestellung bestellung;
	
	DecimalFormat df = new DecimalFormat("###,##0.00");
	ObservableList<Bestellung> bestellungen;
	
	public BasisControl(){
		
		basisModel = new BasisModel();
		bestellung = new Bestellung(new Date());
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BestellungenAufbereiten();
		gerichteEinlesen();
		bestellungEinlesen();
		cbxBestellung.getSelectionModel().select(bestellungen.size()-1);
	}
	
	 @FXML
	 private void handleAction() {
		 
		 Gericht gericht = (Gericht) tvGerichteEdit.getSelectionModel().getSelectedItem();
			
			if(gericht != null){
				txtEditName.setText(gericht.getName());
				txtEditPreis.setText(df.format(gericht.getPreis()));
			}
	    }
	 
	 @FXML
	 private void updateGericht() {
		 Gericht gericht = (Gericht) tvGerichteEdit.getSelectionModel().getSelectedItem();
		 
		 if(gericht != null) {
		 
			 if(!txtEditName.getText().isEmpty() && !txtEditPreis.getText().isEmpty()) {
				 try {
					basisModel.updateGericht(gericht.getGerichtId(), txtEditName.getText(), Double.parseDouble(txtEditPreis.getText().replaceAll(",", ".")), gericht.getKategorieid());
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtEditName.clear();
				txtEditPreis.clear();
				gerichteEinlesen();
				
				//ToDo Preis on the fly berechen
				tvBestellung.refresh();
				lblRechnung.setText("Kosten: " + df.format(bestellung.berechnePreis())+ " €");
			 }else {
				 Alert alert = new Alert(Alert.AlertType.ERROR);
				    alert.setTitle("Fehler");
				    alert.setHeaderText("Leerer Name oder Preis");
				    alert.setContentText("Der Name und der Preis darf nicht leer sein.");
				    alert.showAndWait();			 
			 }
		 }
	 }
	 
	 @FXML
	 private void insertGericht() {
		 if(!txtEditName.getText().isEmpty() && !txtEditPreis.getText().isEmpty()) {
			 try {
				 basisModel.insertIntoGericht(txtEditName.getText(), Double.parseDouble(txtEditPreis.getText().replaceAll(",", ".")), 1);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtEditName.clear();
			txtEditPreis.clear();
			gerichteEinlesen();
		 } else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("Fehler");
		    alert.setHeaderText("Leerer Name oder Preis");
		    alert.setContentText("Der Name und der Preis darf nicht leer sein.");
		    alert.showAndWait();		 
		 }
	 }
	 
	 @FXML
	 private void deleteGericht() {
		 Gericht gericht = (Gericht) tvGerichteEdit.getSelectionModel().getSelectedItem();
		 
		 if(gericht != null) {
			try {
				basisModel.deleteGericht(gericht.getGerichtId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtEditName.clear();
			txtEditPreis.clear();
			gerichteEinlesen();
		 }
		 
		 
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
			lblRechnung.setText("Kosten: " + df.format(bestellung.berechnePreis())+ " €");
		}
		
		
	}
	
	
	
	private void gerichteEinlesen(){
		tvGerichte.getColumns().clear();
		tvGerichteEdit.getColumns().clear();
		
		ObservableList<Gericht> ausgeleseneGerichte = null;
		try {
			ausgeleseneGerichte = basisModel.selectAlleGerichte();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<Gericht> ausgeleseneGerichte2 = null;
		try {
			ausgeleseneGerichte2 = basisModel.selectAlleGerichte();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TableColumn<Gericht, String> colName = new TableColumn<Gericht, String>("Name");
		colName.setMinWidth(175);
		colName.setCellValueFactory(new PropertyValueFactory<Gericht, String>("name"));
		
		TableColumn<Gericht, String> colName2 = new TableColumn<Gericht, String>("Name");
		colName2.setMinWidth(175);
		colName2.setCellValueFactory(new PropertyValueFactory<Gericht, String>("name"));
        
		TableColumn<Gericht, Double> colPreis = new TableColumn<Gericht, Double>("Preis");
		colPreis.setMinWidth(75);        
		colPreis.setCellValueFactory(new PropertyValueFactory<Gericht, Double>("preis"));
		
		TableColumn<Gericht, Double> colPreis2 = new TableColumn<Gericht, Double>("Preis");
		colPreis2.setMinWidth(75);        
		colPreis2.setCellValueFactory(new PropertyValueFactory<Gericht, Double>("preis"));
		
        tvGerichte.getColumns().addAll(colName,colPreis); 
        tvGerichteEdit.getColumns().addAll(colName2,colPreis2);
        
        tvGerichte.setItems(ausgeleseneGerichte.sorted());
        tvGerichteEdit.setItems(ausgeleseneGerichte2.sorted());
	}
	
	private void bestellungEinlesen() {
		bestellungen = null;
		
		try {
			bestellungen = basisModel.selectAlleBestellungen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bestellungen.add(new Bestellung(0,new Date()));
		
		if(bestellungen != null) {
			
			cbxBestellung.setItems(bestellungen);
			
		}
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
        
        Callback<ListView<Bestellung>, ListCell<Bestellung>> factory = lv -> new ListCell<Bestellung>() {

            @Override
            protected void updateItem(Bestellung item, boolean empty) {
                super.updateItem(item, empty);
                DateFormat formatter = new SimpleDateFormat();
                setText(empty ? "" : item.getBestellungId()+" | " + formatter.format(item.getDatum())+ " Uhr");
            }

        };

        cbxBestellung.setCellFactory(factory);
        cbxBestellung.setButtonCell(factory.call(null));
        
	}
	
	@FXML
	public void bestellungVerwerfen(){
		
		if(bestellung.getBestellungId() > 0) {
			try {
				basisModel.deleteBestellung(bestellung.getBestellungId());
				bestellungEinlesen();
				cbxBestellung.getSelectionModel().select(bestellungen.size()-1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		bestellung = null;
		bestellung = new Bestellung(new Date());
		tvBestellung.getColumns().clear();
		BestellungenAufbereiten();
		tvBestellung.refresh();
		lblRechnung.setText("Kosten: " + df.format(bestellung.berechnePreis())+ " €");
	}
	
	@FXML
	public void gerichtAusBestellungEntfernen(){
		int index = tvBestellung.getSelectionModel().getSelectedIndex();
		if (index != -1){
			bestellung.entferneGericht(index);
			tvBestellung.setItems(bestellung.getBestelltegerichte());
			tvBestellung.refresh();
			lblRechnung.setText("Kosten: " + df.format(bestellung.berechnePreis())+ " €");
		}
	}
	
	@FXML
	public void bestellungSpeichern(){
		try {
			if(bestellung.getBestelltegerichte().size() > 0){
				basisModel.bestellungSpeichern(bestellung);
				bestellungEinlesen();
				cbxBestellung.getSelectionModel().select(bestellungen.size()-2);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void bestellungLaden(){
		try {
			Bestellung b = cbxBestellung.getSelectionModel().getSelectedItem();
			
			if(b != null){
				if(b.getBestellungId() > 0) {
					ObservableList<BestellungGericht> bestellungGericht = basisModel.bestellungLaden(b.getBestellungId());
					bestellung = bestellungen.get(cbxBestellung.getSelectionModel().getSelectedIndex());
					bestellung.setBestellteGerichte(bestellungGericht);
					tvBestellung.setItems(bestellungGericht);
				} else {
				bestellung = null;
				bestellung = new Bestellung(new Date());
				tvBestellung.getColumns().clear();
				BestellungenAufbereiten();
			}
				
				
		}
		tvBestellung.refresh();
		
		lblRechnung.setText("Kosten: " + df.format(bestellung.berechnePreis())+ " €");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
