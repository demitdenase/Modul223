/**
 * @author : Stefan Ciric
 * @date   : 18.05.2016
 * @version: 1.0
 * 
 * **/
package controller;


import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import db.AktieDAO;
import db.VerkaufDAO;
import model.Aktie;


@ManagedBean
@SessionScoped
public class AuftragErfassenBean {
		
	AktieDAO       aktieDao;
	VerkaufDAO     verkaufDao;
	Aktie     aktie;
	private String displayname;
	private int    aktieID;
	private double preis;
	private int    anzahl;
	private long   maxAnzahl;
	
	public AuftragErfassenBean() {
		anzahl = 1; //Standartwert
	}
	
	/**
	 * Angeklickte Aktie wird in Session gespeichert.
	 * @param aktie
	 * @return String um auf die n�chste Seite zu kommen. (Auftragerfassen.xhml)
	 */
	public String verkaufen(Aktie aktie){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.getSessionMap().put("Aktie", aktie);
		displayCurrentAktie();
		return "/private/haendler/Auftragerfassen?faces-redirect=true";
	}
	
	/**
	 * Die Aktie wird angezeigt auf dem Auftragerfassen.xhtml
	 */
	public void displayCurrentAktie(){
		Aktie aktie;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		aktie = (Aktie) externalContext.getSessionMap().get("Aktie");
		displayname = "" + aktie.getName() + " / " + aktie.getKuerzel() + "";
		System.out.println(displayname);
	}
	
	/**
	 * insert Auftrag in DB
	 * @return String um auf die n�chste Seite zu kommen(Portfolio.xhtml).
	 * @throws SQLException 
	 */
	public String insertAuftrag() throws SQLException{
		String auftragIds = "";
		verkaufDao = new VerkaufDAO();
		aktie = aktie.getAktieFromSession();
		verkaufDao.insertAuftrag(preis, aktie.getKuerzel());
		
		
		MeldungFormBean m = new MeldungFormBean();
		m.setAktuelleMeldung(m.getMeldung5() + auftragIds);
		m.putMeldungToSession(m);
	return "/private/haendler/Portfolio?faces-redirect=true";
	}
	
	/**
	 * Zur�ckButton
	 * @return String um auf die vorherige Seite zu kommen(Portfolio.xhtml).
	 */
	public String back(){
		return "/private/haendler/Portfolio?faces-redirect=true";	
	}
	
	/**
	 * MaxAnzahl f�r die Validierung.
	 * @return maximale Anzahl an Aktien
	 */
	public long getMaxAnzahl() {
		try {
			aktieDao = new AktieDAO();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			aktie = (Aktie) externalContext.getSessionMap().get("Aktie");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return maxAnzahl = aktieDao.getAnzahlAktieBySymbol(aktie.getKuerzel());
	}
	public void setMaxAnzahl(long maxAnzahl) {
		this.maxAnzahl = maxAnzahl;
	}
	public AktieDAO getAktieDao() {
		return aktieDao;
	}
	public void setAktieDao(AktieDAO aktieDao) {
		this.aktieDao = aktieDao;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public int getAktieID() {
		return aktieID;
	}
	public void setAktieID(int aktieID) {
		this.aktieID = aktieID;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public VerkaufDAO getVerkaufDao() {
		return verkaufDao;
	}

	public void setVerkaufDao(VerkaufDAO verkaufDao) {
		this.verkaufDao = verkaufDao;
	}

	public Aktie getAktie() {
		return aktie;
	}

	public void setAktie(Aktie aktie) {
		this.aktie = aktie;
	}

}

