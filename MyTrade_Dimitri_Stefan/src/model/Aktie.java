package model;

public class Aktie {
	private int id;
	private String name;
	private double	nominalwert;
	private double dividende;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNominalwert() {
		return nominalwert;
	}
	public void setNominalwert(double nominalwert) {
		this.nominalwert = nominalwert;
	}
	public double getDividende() {
		return dividende;
	}
	public void setDividende(double dividende) {
		this.dividende = dividende;
	}

}
