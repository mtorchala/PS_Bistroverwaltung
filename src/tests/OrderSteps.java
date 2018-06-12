package tests;
import java.sql.SQLException;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import javafx.collections.ObservableList;
import programm.Bestellung;
import programm.DatenbankAnbindung;
import programm.Gericht;

public class OrderSteps extends Steps{
	
	Bestellung bestellung;
	
	ObservableList<Gericht> gerichte;
	
	@Given("eine Bestellung bestehend aus $anzahlGerichte identischen Gerichten")
	public void setDishes(int anzahlGerichte){
		bestellung = new Bestellung(new Date());
		try {
			gerichte = new DatenbankAnbindung().selectAlleGerichte();
			for(int i = 0; i < anzahlGerichte; i++){
				bestellung.fügeGerichtHinzu(gerichte.get(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@When("der Nutzer 1 identisches Gericht zu der Bestellung hinzufügt")
	public void addDish(){	
		bestellung.fügeGerichtHinzu(gerichte.get(0));
	}
	
	@Then("soll die Bestellung aus $anzahlGerichte identischen Gericht/en bestehen")
	public void checkDish(int anzahlGerichte){
		Assert.assertEquals(bestellung.getBestelltegerichte().get(0).getAnzahl(),anzahlGerichte);
	}
	
}
