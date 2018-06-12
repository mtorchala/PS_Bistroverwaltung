package tests;

import java.sql.SQLException;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import javafx.collections.ObservableList;
import programm.BasisControl;
import programm.Bestellung;
import programm.DatenbankAnbindung;
import programm.Gericht;

public class BerechnePreisSteps extends Steps {
	
	Bestellung bestellung;
	ObservableList<Gericht> gerichte;
	double gesamtpreis = 0.0;
	
	
	@Given("eine Bestellung bestehend aus $anzahl1 $gericht1 und $anzahl2 $gericht2")
	public void setBestellung(int anzahl1, String gericht1, int anzahl2, String gericht2){
		bestellung = new Bestellung(new Date());
		try {
			gerichte = new DatenbankAnbindung().selectAlleGerichte();
			for(Gericht g : gerichte){
				if(g.getName().equals(gericht1)){
					for(int i = 0; i < anzahl1; i++){
						bestellung.fügeGerichtHinzu(g);
					}
				}
				else if(g.getName().equals(gericht2)){
					for(int i = 0; i < anzahl2; i++){
						bestellung.fügeGerichtHinzu(g);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("der Preis für die komplette Bestellung berechnet wird")
	public void berechnePreis(){
		gesamtpreis = bestellung.berechnePreis();
	}
	
	@Then("sollte der Preis $preis betragen")
	public void vergleichePreis(double preis){		
		Assert.assertEquals(gesamtpreis,preis,0.01);
	}
}