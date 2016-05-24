package controller;


import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.*;
import model.*;


@ManagedBean
@SessionScoped
public class OffeneAuftraegeFormBean {
	private List<Verkauf> auftraege;
	private double kontostand;
	private VerkaufDAO verkaufDAO;

	
	/**
	 * Entweder kaufen oder stornieren, je nach dem, ob dem angemeldeten User die geklickte Aktie geh�rt oder nicht. (stornieren oder kaufen)
	 * @param verkauf
	 * @return String um die Seite zu reloaden(Auftraege.xhtml).
	 * @throws SQLException 
	 */
	public String doAktion(Verkauf verkauf) throws SQLException{
		verkaufDAO = new VerkaufDAO();
		MeldungFormBean m = new MeldungFormBean();
		//stornieren
		//if (verkauf.isUser()) {

			m.setAktuelleMeldung(m.getMeldung6() + verkauf.getVerkaufsid());
			System.out.println("storno");
			System.out.println(verkauf.getVerkaufsid());
			verkaufDAO.deleteAuftragById(verkauf.getVerkaufsid());
		//} 
		//kaufen
		//else {

			m.setAktuelleMeldung(m.getMeldung4() + verkauf.getVerkaufsid());
			System.out.println("kaufen");
			verkaufDAO.doKaufen(verkauf);
			verkaufDAO.deleteAuftragById(verkauf.getVerkaufsid());
			
		//}
		m.putMeldungToSession(m);
		//System.out.println(verkauf.isUser());
		return "/private/haendler/Auftraege?faces-redirect=true";
	}
	
	public double getKontostand() {
		Benutzer benutzer = new Benutzer().getUserObjectFromSession();
		return this.kontostand = benutzer.getKontostand();
	}
	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}
	public List<Verkauf> getAuftraege() throws SQLException {
		return auftraege = new VerkaufDAO().getVerkaufsliste();
	}
	public void setAuftraege(List<Verkauf> auftraege) {
		this.auftraege = auftraege;
	}
}


