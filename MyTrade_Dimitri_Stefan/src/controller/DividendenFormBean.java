package controller;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.BenutzerDAO;

@ManagedBean
@SessionScoped
public class DividendenFormBean {

	/**
	 * Schüttet die Dividenden aus.
	 * @return String die Seite zu reloaden(Admin.xhtml).
	 * @throws SQLException 
	 */
	public String ausschuetten() throws SQLException {
		BenutzerDAO benutzerDao = new BenutzerDAO();
//		Dividenden aussch�tten
		benutzerDao.dividendeAnBenutzer();
		
		MeldungFormBean m = new MeldungFormBean();
		m.setAktuelleMeldung(m.getMeldung2());
		m.putMeldungToSession(m);
		
		return "/private/admin/Admin?faces-redirect=true";
	}

}


