package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.connectionPooling.*;

import javax.naming.NoPermissionException;

import model.Benutzer;

public class BenutzerDAO extends AbstractDAO {
	Connection c;

	public BenutzerDAO() throws SQLException {
		try {
			c = ConnectionPoolingImplementation.getInstance().getConnection();
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Benutzer> getBenutzer() {
		List<Benutzer> benutzerListe = new ArrayList<Benutzer>();
		try {

			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_benutzer`");
			while (rs.next()) {
				Benutzer benutzer = new Benutzer();
				benutzer.setBenutzerid(rs.getInt(1));
				benutzer.setName(rs.getString(2));
				benutzer.setPasswortHash(rs.getString(3));
				benutzer.setRolle(rs.getInt(4));
				benutzer.setKontostand(rs.getDouble(5));
				benutzerListe.add(benutzer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return benutzerListe;
	}

	public Benutzer findPerson(int benutzerId) throws SQLException {
		Statement stm = c.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_benutzer` WHERE benutzer_pk= " + benutzerId);
		if (rs.next()) {
			Benutzer benutzer = new Benutzer();
			benutzer.setBenutzerid(rs.getInt(1));
			benutzer.setName(rs.getString(2));
			benutzer.setPasswortHash(rs.getString(3));
			benutzer.setRolle(rs.getInt(4));
			benutzer.setKontostand(rs.getDouble(5));
			return benutzer;
		} else {
			return null;
		}

	}
	public Benutzer loginPersonExists(String username,String passwortHash) throws SQLException {
		Statement stm = c.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_benutzer` WHERE `benutzername`= '" + username +"' AND `passwort_hash`= '" + passwortHash +"'");
		if (rs.next()) {
			Benutzer benutzer = new Benutzer();
			benutzer.setBenutzerid(rs.getInt(1));
			benutzer.setName(rs.getString(2));
			benutzer.setPasswortHash(rs.getString(3));
			benutzer.setRolle(rs.getInt(4));
			benutzer.setKontostand(rs.getDouble(5));
			return benutzer;
		} else {
			return null;
		}

	}

	public Benutzer insertBenutzer(Benutzer benutzer) throws SQLException {
		Statement stm = c.createStatement();
		int numero;
		numero = stm.executeUpdate(
				"INSERT INTO `mytrade`.`tbl_benutzer`(`benutzername`,`passwort_hash`,`rolle`,`kontostand`)VALUES ("
						+ benutzer.getName() + "', '" + benutzer.getPasswortHash() + "', " + benutzer.getRolle() + ", "
						+ benutzer.getKontostand() + ")",
				Statement.RETURN_GENERATED_KEYS);
		c.commit();
		benutzer.setBenutzerid(numero);
		return benutzer;
	}

	public Benutzer updatePerson(Benutzer benutzer) throws SQLException {
		Statement stm = c.createStatement();
		String sql = "UPDATE `mytrade`.`tbl_benutzer` SET " + "`benutzername` = " + benutzer.getName()
				+ ", `passwort_hash` = '" + benutzer.getPasswortHash() + "'" + ", `rolle` = " + benutzer.getRolle()
				+ ", `kontostand` = " + benutzer.getKontostand() + " WHERE `benutzer_pk` = " + benutzer.getBenutzerid();
		stm.executeUpdate(sql);
		c.commit();
		return benutzer;
	}
}
