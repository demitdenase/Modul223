package controller;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import db.AktieDAO;
import model.Aktie;
import model.AktieBenutzer;
import model.Benutzer;


@ManagedBean
@ViewScoped
public class MeinPortfolioBean {
	Benutzer benutzer;
	AktieDAO aktieDAO;
	private int menge;
	ArrayList<AktieBenutzer> list;
	ArrayList<Aktie> aktienListe;
	public ArrayList<Aktie> getAktienListe() {
		return benutzer.getAktienListe();
	}

	public void setAktienListe(ArrayList<Aktie> aktienListe) {
		this.aktienListe = aktienListe;
	}
	Double konto;
	@PostConstruct
	public void init() throws SQLException {
		benutzer = benutzer.getUserObjectFromSession();
		aktienListe = benutzer.getAktienListe();
	}
	
	public Benutzer getBenutzer() {
		return benutzer.getUserObjectFromSession();
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public AktieDAO getAktieDAO() {
		return aktieDAO;
	}

	public void setAktieDAO(AktieDAO aktieDAO) {
		this.aktieDAO = aktieDAO;
	}

	public Double getKonto() {
		return konto;
	}

	public void setKonto(Double konto) {
		this.konto = konto;
	}

	/**
	 * Holt den Kontostand des Users aus der Session
	 * @return den Kontostand des angemeldeten Users
	 */
	public Double getKontostand(){
		benutzer = new Benutzer().getUserObjectFromSession();
		konto = benutzer.getKontostand();
	
	
		return konto;
	}
	
	/**
	 * Holt die Aktien des angemeldeten Users aus der DB
	 * @return Liste aller Aktien des angemeldeten Users
	 * @throws SQLException 
	 */
	public ArrayList<AktieBenutzer> getList() throws SQLException {
		
		return list;
	}
	public void setList(ArrayList<AktieBenutzer> list) {
		this.list = list;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

}