package model;

public class Benutzer {
	private int benutzerid;
	private String name;
	private String passwortHash;
	private int rolle; 
	private ArrayList = new ArrayList<Aktie>();
	
	public int getBenutzerid() {
		return benutzerid;
	}
	public void setBenutzerid(int benutzerid) {
		this.benutzerid = benutzerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswortHash() {
		return passwortHash;
	}
	public void setPasswortHash(String passwortHash) {
		this.passwortHash = passwortHash;
	}
	public int getRolle() {
		return rolle;
	}
	public void setRolle(int rolle) {
		this.rolle = rolle;
	}
	public double getKontostand() {
		return kontostand;
	}
	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}
	private double kontostand;

}
