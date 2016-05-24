/**
 * @author dennis.gehrig
 * @date   29.05.2015
 */
package controller;


import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.BenutzerDAO;
import model.Benutzer;

@ManagedBean
@SessionScoped
public class BenutzerFormBean {
	private int rolle;
	private String name;
	private String vorname;
	private String login;
	private String passwort;
	
	/**
	 * Map f�r das Dropdown auf Benutzererfassen.xhtml
	 */
	private static final Map<String,Object> ROLLENLISTE;
	static{
		ROLLENLISTE = new LinkedHashMap<String, Object>();
		ROLLENLISTE.put("Administrator", 1);
		ROLLENLISTE.put("Aktienhändler", 2);
	}
	
	public Map<String,Object> getRollenListe() {
		return ROLLENLISTE;
	}
	
	/**
	 * Speichert den Benutzer im UserModel und dann in der DB.
	 * @return String um auf die nächste Seite zu kommen(Admin.xhtml)
	 */
	public String saveBenutzer(){
		Benutzer benutzer = new Benutzer();
		BenutzerDAO benutzerDao;
		try {
			//User in Datenbank schreiben
			benutzerDao = new BenutzerDAO();
			benutzer.setName(login);
			benutzer.setPasswortHash(passwort);
			benutzer.setRolle(rolle);
			benutzer.setKontostand(10000);
			benutzerDao.insertBenutzer(benutzer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MeldungFormBean m = new MeldungFormBean();
		
		
		
		//Meldung Ausgeben
		m.setAktuelleMeldung(m.getMeldung3() + vorname);
		m.putMeldungToSession(m);
		
		return "/private/admin/Admin?faces-redirect=true";
	}
	
	/**
	 * Weiter Button Methode
	 * @return String um auf die n�chste Seite zu kommen(Benutzerbestaetigung.xhtml).
	 */
	public String next(){
		return "/private/admin/Benutzerbestaetigung?faces-redirect=true";
	}
	
	/**
	 * Zurück Button Methode
	 * @return String um auf die vorherige Seite zu kommen(Admin.xhtml).
	 */
	public String back(){
		System.out.println("debug test");
		return "/private/admin/Admin?faces-redirect=true";
		
	}
	
	/**
	 * Zurück Button2 Methode
	 * @return String um auf die vorherige Seite zu kommen(Benutzererfassen.xhtml).
	 */
	public String back2(){
		return "/private/admin/Benutzererfassen?faces-redirect=true";
	}
	
	//Getters and Setters
	public int getRolle() {
		return rolle;
	}
	public void setRolle(int rolle) {
		this.rolle = rolle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
}


