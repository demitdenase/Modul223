package controller;

import javax.faces.bean.ManagedBean;

import model.Benutzer;

@ManagedBean(name="neuerBenutzerBean")
public class NeuerBenutzerBean {
	private Benutzer benutzer;
	public NeuerBenutzerBean(){
		setBenutzer(new Benutzer());
	}
	public Benutzer getBenutzer() {
		return benutzer;
	}
	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
}
