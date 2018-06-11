/*package tests;
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
	
	@Given("an order which contains $dishes dishes")
	public void setDishes(int dishes){
		bestellung = new Bestellung(new Date());
		try {
			gerichte = new DatenbankAnbindung().selectAlleGerichte();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@When("the user adds 1 dish to the order")
	public void addDish(){
		bestellung.fügeGerichtHinzu(gerichte.get(0));
	}
	
	@Then("the order should contain $dishes dishes")
	public void checkDish(int dishes){
		Assert.assertEquals(bestellung.getBestelltegerichte().get(0).getAnzahl(),1);
	}
	
}
*/