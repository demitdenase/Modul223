package model;

public class AktieBenutzer {
	private Aktie aktie;
	private Benutzer benutzer;
	private int menge;
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
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	

}
