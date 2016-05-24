
package controller;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import db.BenutzerDAO;
import model.Benutzer;




@ManagedBean(name="loginFormBean")
@SessionScoped
public class LoginFormBean {
	private Benutzer benutzer;
	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	private String benutzername;
	private String passwortHash;
	private BenutzerDAO jdbc;
	FacesContext context;
	

	public LoginFormBean() throws SQLException {

		jdbc = new BenutzerDAO();
	}

	/**
	 * Ruft die Login Methode im DAO auf. Und leitet den User entsprechend weiter.
	 * @return String um auf die vorherige Seite zu kommen(mehrere M�glichkeiten).
	 */
	public String validate() {
		if (jdbc.login(benutzername, passwortHash)) {
			System.out.println("hat geklappt");
				benutzer = new Benutzer();
				benutzer = jdbc.getUserByLogin(benutzername);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ExternalContext externalContext = facesContext.getExternalContext();
				externalContext.getSessionMap().put("benutzer", benutzer);
//				admin
				if(1 == benutzer.getRolle()){
					return "/private/admin/Admin?faces-redirect=true";
				}
				return "/private/haendler/Haendler?faces-redirect=true";
		} else {
			System.out.println("User oder PW falsch");
			return "/public/login?faces-redirect=true";
		}
		
	
	}
	public String logout(){
		Benutzer benutzer = null;
		benutzer = jdbc.getUserByLogin(benutzername);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.getSessionMap().put("benutzer", null);
		return "/public/login?faces-redirect=true";
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswortHash() {
		return passwortHash;
	}

	public void setPasswortHash(String passwortHash) {
		this.passwortHash = passwortHash;
	}

	public BenutzerDAO getJdbc() {
		return jdbc;
	}

	public void setJdbc(BenutzerDAO jdbc) {
		this.jdbc = jdbc;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}



}

