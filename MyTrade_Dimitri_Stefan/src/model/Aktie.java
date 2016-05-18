package model;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Aktie {
	private int id;
	private String name;
	private double	nominalwert;
	private double dividende;
	private String kuerzel;
	public int getId() {
		return id;
	}
	public Aktie getAktieFromSession() {
		Aktie a;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		a = (Aktie) externalContext.getSessionMap().get("Aktie");
		return a;
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
	public String getKuerzel() {
		return kuerzel;
	}
	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

}
