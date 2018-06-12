package tests;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import programm.BasisControl;

public class NumberFormatExceptionSteps extends Steps {
	
	String preis;
	BasisControl basisControl = new BasisControl();
	private boolean thrownException = false;
	
	@Given("eine Eingabe für einen Preis $preis")
	public void setPreis(String preis){
		this.preis = preis;

	}
	
	@When("die ungültige Eingabe in eine Zahl umgewandelt wird")
	public void convertPreis(){
		try {
			basisControl.stringToDouble(preis);      
		} catch (Exception e) {
			
		    thrownException = true;
		}	
	}
	
	@Then("soll eine Fehlermeldung aufgerufen werden")
	public void checkError(){		
		Assert.assertEquals(thrownException,true);
	}
}