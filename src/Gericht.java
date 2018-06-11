
public class Gericht {

	private String name;
	private double preis;
	private int kategorieid;
	private int gerichtId;
	
	public Gericht(int gerichtId, String name, double preis, int kategorieid) {
		this.gerichtId = gerichtId;
		this.name = name;
		this.preis = preis;
		this.kategorieid = kategorieid;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getKategorieid() {
		return kategorieid;
	}
	public void setKategorieid(int kategorieid) {
		this.kategorieid = kategorieid;
	}
	public int getGerichtId() {
		return gerichtId;
	}
	public void setGerichtId(int gerichtId) {
		this.gerichtId = gerichtId;
	}
	
	
}
