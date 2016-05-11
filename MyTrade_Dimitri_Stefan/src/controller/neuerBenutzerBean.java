package controller;

import javax.faces.bean.ManagedBean;

import model.Benutzer;

@ManagedBean(name="neuerBenutzerBean")
public class neuerBenutzerBean {
	private Benutzer benutzer;
	public neuerBenutzerBean(){
		setBenutzer(new Benutzer());
	}
	public Benutzer getBenutzer() {
		return benutzer;
	}
	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
}
