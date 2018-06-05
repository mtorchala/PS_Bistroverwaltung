
public class BestellungGericht {
	
	private Gericht gericht;
	private int anzahl;

	
	public BestellungGericht(Gericht gericht, int anzahl) {
		this.gericht = gericht;
		this.anzahl = anzahl;
	}
	
	
	public Gericht getGericht() {
		return gericht;
	}
	public void setGericht(Gericht gericht) {
		this.gericht = gericht;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	
}
