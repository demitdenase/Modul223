package model;

public class Verkauf {
	private int verkaufsid;
	private Aktie aktie;
	private Benutzer benutzer;
	private double preis;
	public int getVerkaufsid() {
		return verkaufsid;
	}
	public void setVerkaufsid(int verkaufsid) {
		this.verkaufsid = verkaufsid;
	}
	public Aktie getAktie() {
		return aktie;
	}
	public void setAktie(Aktie aktie) {
		this.aktie = aktie;
	}
	public Benutzer getBenutzer() {
		return benutzer;
	}
	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}

}
