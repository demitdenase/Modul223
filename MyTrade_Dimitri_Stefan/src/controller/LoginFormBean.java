/**
 * @author : Jason Angst
 * @date   : 03.07.2015
 * @version: 1.0
 * 
 * **/
package controller;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import db.BenutzerDAO;
import model.Benutzer;




@ManagedBean
@SessionScoped
public class LoginFormBean {

	private String username;
	private String password;
	private BenutzerDAO jdbc;
	FacesContext context;
	

	public LoginFormBean() throws SQLException {

		jdbc = new BenutzerDAO();
	}

	/**
	 * Ruft die Login Methode im DAO auf. Und leitet den User entsprechend weiter.
	 * @return String um auf die vorherige Seite zu kommen(mehrere M�glichkeiten).
	 */
	public String anmelden() {
		Benutzer benutzer = null;
		if (jdbc.login(username, password)) {
			System.out.println("hat geklappt");
				benutzer = new Benutzer();
				benutzer = jdbc.getUserByLogin(username);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ExternalContext externalContext = facesContext.getExternalContext();
				externalContext.getSessionMap().put("user", benutzer);
//				admin
				if(1 == benutzer.getFk_typID()){
					return "/private/admin/Admin?faces-redirect=true";
				}
				return "/private/haendler/Portfolio?faces-redirect=true";
		} else {
			System.out.println("User oder PW falsch");
			return "/public/login?faces-redirect=true";
		}
		
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

