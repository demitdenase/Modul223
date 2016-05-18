package model;

import java.util.ArrayList;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Benutzer {
	private int benutzerid;
	private String name;
	private String passwortHash;
	private int rolle; //1 == admin 2 == Händler
	private ArrayList<Aktie> aktienListe = new ArrayList<Aktie>();
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
	public ArrayList<Aktie> getAktienListe() {
		return aktienListe;
	}
	public void setAktienListe(ArrayList<Aktie> aktienListe) {
		this.aktienListe = aktienListe;
	}
	private double kontostand;
	public Benutzer getUserObjectFromSession() {
			Benutzer benutzer;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			benutzer = (Benutzer) externalContext.getSessionMap().get("Benutzer");
			return benutzer;
		
	}


}
