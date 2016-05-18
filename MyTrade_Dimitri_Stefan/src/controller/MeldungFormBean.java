package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "meldungFormBean")
@SessionScoped
public class MeldungFormBean {

	private String meldung1 = "Die Aktie wurde erfolgreich gespeichert: ";
	private String meldung2 = "Die Dividende wurde erfolgreich ausgeschüttet.";
	private String meldung3 = "Der Benutzer wurde erfolgreich angelegt: ";
	private String meldung4 = "Der Auftrag wurde erfolgreich ausgeführt: ";
	private String meldung5 = "Der Auftrag wurde erfolgreich erfasst:";
	private String meldung6 = "Der Auftrag wurde erfolgreich storniert: ";

	private String errorMeldung1 = "Sie haben versucht auf eine Haendler-Seite zuzugreifen."
			+ "Sie sind aber Admin und haben keinen zugriff auf diese Seite."
			+ "Wir haben Sie auf die Admin-Seite weitergeleitet.";

	private String errorMeldung2 = "Sie haben versucht auf eine Admin-Seite zuzugreifen."
			+ "Sie sind aber Haendler und haben keinen Zugriff auf diese Seite."
			+ "Wir haben Sie auf Ihr Portfolio weitergeleitet.";
	private String aktuelleMeldung = "";

	public String getMeldung1() {
		return meldung1;
	}

	public String getMeldung2() {
		return meldung2;
	}

	public String getMeldung3() {
		return meldung3;
	}

	public String getMeldung4() {
		return meldung4;
	}

	public String getMeldung5() {
		return meldung5;
	}

	public String getMeldung6() {
		return meldung6;
	}

	public void setAktuelleMeldung(String meldung) {
		this.aktuelleMeldung = meldung;
	}

	public String getAktuelleMeldung() {
		return aktuelleMeldung;
	}

	public String getErrorMeldung1() {
		return errorMeldung1;
	}

	public String getErrorMeldung2() {
		return errorMeldung2;
	}

	public void putMeldungToSession(MeldungFormBean m) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null) {
			return;
		}
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.getSessionMap().put("meldungFormBean", m);
	}
}
