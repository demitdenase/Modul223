package controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="loginBean")
public class LoginBean {
	private String benutzername;
	private String passwortHash;
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

}
