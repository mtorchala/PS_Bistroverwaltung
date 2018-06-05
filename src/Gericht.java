
public class Gericht {

	private String name;
	private double preis;
	private int kategorieid;
	
	public Gericht(String name, double preis, int kategorieid) {
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
}
