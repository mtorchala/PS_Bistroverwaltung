package programm;
import java.util.Date;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bestellung {
	private int bestellungId;
	private Date datum;
	private ObservableList<BestellungGericht> bestellteGerichte;

	
	

	public Bestellung(int bestellungId, Date datum) {
		this.bestellungId = bestellungId;
		this.datum = datum;
		bestellteGerichte = FXCollections.observableArrayList();
	}

	public Bestellung(Date datum) {
		this.datum = datum;
		bestellteGerichte = FXCollections.observableArrayList();
	}

	public int getBestellungId() {
		return bestellungId;
	}
	
	public void setBestellungId(int bestellungId) {
		this.bestellungId = bestellungId;
	}
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public void setBestellteGerichte(ObservableList<BestellungGericht> bestellteGerichte) {
		this.bestellteGerichte = bestellteGerichte;
	}



	public ObservableList<BestellungGericht> getBestelltegerichte() {
		return bestellteGerichte;
	}



	public void fügeGerichtHinzu(Gericht gericht) {
		int index = getIndexFromGericht(gericht);
		if(index != -1){
			int neueAnzahl = bestellteGerichte.get(index).getAnzahl();
			neueAnzahl++;
			bestellteGerichte.get(index).setAnzahl(neueAnzahl);
		}
		else{
			bestellteGerichte.add(new BestellungGericht(gericht,1));		
		}
	}
	
	public void entferneGericht(int index){
		if(bestellteGerichte.get(index).getAnzahl() >= 2){
			int neueAnzahl = bestellteGerichte.get(index).getAnzahl();
			neueAnzahl--;
			bestellteGerichte.get(index).setAnzahl(neueAnzahl);
		}
		else{
			bestellteGerichte.remove(index);
		}
	}
	
	public int getIndexFromGericht(Gericht gericht){
		int index = -1;
		
		for(BestellungGericht bg : bestellteGerichte){
			index++;
			if(bg.getGericht().equals(gericht)){
				return index;
			}
		}
		return -1;
		
	}
	
	public double berechnePreis(){
		double preis = 0.0;
		for(BestellungGericht bg : bestellteGerichte){
			preis+= bg.getGericht().getPreis()*bg.getAnzahl();
		}
		double preisgerundet = Math.round(preis*100);
		return preisgerundet/100;
	}
	
}
