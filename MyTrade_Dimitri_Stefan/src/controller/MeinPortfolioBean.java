package controller;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.AktieDAO;
import model.Aktie;
import model.AktieBenutzer;
import model.Benutzer;


@ManagedBean
@SessionScoped
public class MeinPortfolioBean {
	Benutzer benutzer;
	AktieDAO aktieDAO;
	ArrayList<AktieBenutzer> list;
	Double konto;
	
	public Benutzer getBenutzer() {
		return benutzer;
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
		aktieDAO =    new AktieDAO();
		benutzer =    new Benutzer().getUserObjectFromSession();
		list = new ArrayList<AktieBenutzer>();
//		holt Aktien aus DB
		list = aktieDAO.getAllAktienforUser(benutzer);
		return list;
	}
	public void setList(ArrayList<AktieBenutzer> list) {
		this.list = list;
	}

}