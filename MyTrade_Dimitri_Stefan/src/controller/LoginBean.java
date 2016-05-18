package controller;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import db.*;
import model.*;

@ManagedBean(name = "loginBean")
public class LoginBean {
	private String benutzername;
	private String passwortHash;
	private Benutzer activeBenutzer;

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

	public String validate() {

		try {
			BenutzerDAO benuzerDAO = new BenutzerDAO();
			activeBenutzer = benuzerDAO.loginPersonExists(benutzername, passwortHash);
			if (activeBenutzer == null) {
				System.out.println("Benutzer existiert nicht");
				return "Fehler";

			} else {
				return "seite2";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fehler";
		}
	}

}
