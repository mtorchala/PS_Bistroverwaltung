import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BasisControl implements Initializable{
	private BasisModel basisModel;
	
	@FXML
	private Button bttnAdd;
	
	@FXML
	private TableView<Gericht> tvGerichte;
	
	@FXML
	private TableView tvBestellung;
	
	public BasisControl(){
		
		basisModel = new BasisModel();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gerichteEinlesen();
	}
	
	
	
	
	@FXML
	private void gerichtZurBestellungHinzufügen(){
		tvGerichte.setDisable(true);
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
	
}
