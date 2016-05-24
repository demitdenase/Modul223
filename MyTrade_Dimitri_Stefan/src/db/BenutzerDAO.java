package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.naming.NoPermissionException;

import controller.DividendenRechner;
import db.connectionPooling.ConnectionPoolingImplementation;
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

	public Benutzer loginPersonExists(String username, String passwortHash) throws SQLException {
		Statement stm = c.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_benutzer` WHERE `benutzername`= '" + username
				+ "' AND `passwort_hash`= '" + passwortHash + "'");
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

	public synchronized boolean login(String user, String password) {

		try {
			PreparedStatement preparedStatement = c.prepareStatement(
					"SELECT `tbl_benutzer`.`benutzer_pk`,`tbl_benutzer`.`benutzername`,`tbl_benutzer`.`passwort_hash`,    `tbl_benutzer`.`rolle`, `tbl_benutzer`.`kontostand` FROM `tbl_benutzer` "
							+ "WHERE benutzername = ? AND passwort_hash = ?"); // SHA1(?)
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();

			int count = 0;

			while (rs.next()) {
				Benutzer benutzer = new Benutzer();
				benutzer.setBenutzerid(rs.getInt(1));
				benutzer.setName(rs.getString(2));
				benutzer.setPasswortHash(rs.getString(3));
				benutzer.setRolle(rs.getInt(4));
				benutzer.setKontostand(rs.getDouble(5));				
				int benutzerID = rs.getInt(1);
				count++;

				if (count > 1) {
					System.out.println("Es gibt mehr als einen Benutzer: " + user);
					return false;
				} else if (count == 1) {

					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getSessionMap().put("id", "" + benutzerID);
					context.getExternalContext().getSessionMap().put("user", benutzer);
					System.out.println(benutzerID);
					return true;
				}

			}

			rs.close();
			preparedStatement.close();
			// ConnectionPoolingImplementation.putConnection(c);

		} catch (SQLException e) {
			System.out.println("Es trat ein Fehler mit SQL auf");
			e.printStackTrace();
			// ConnectionPoolingImplementation.putConnection(c);
		}
		return false;
	}

	public synchronized Benutzer getUserByLogin(String login) {

		try {

			System.out.println("Connection: " + c);
			PreparedStatement preparedStatement = c.prepareStatement(
					"SELECT `tbl_benutzer`.`benutzer_pk`, `tbl_benutzer`.`benutzername`,`tbl_benutzer`.`passwort_hash`,`tbl_benutzer`.`rolle`,`tbl_benutzer`.`kontostand` FROM `mytrade`.`tbl_benutzer` WHERE `tbl_benutzer`.`benutzername` = ?");
			preparedStatement.setString(1, login);

			ResultSet rs = preparedStatement.executeQuery();

			Benutzer benutzer = new Benutzer();
			System.out.println("testgetuser");
			if (rs.next()) {
				System.out.println(rs.getString("benutzername"));
				benutzer.setBenutzerid(rs.getInt("benutzer_pk"));
				benutzer.setName(rs.getString("benutzername"));
				benutzer.setRolle(rs.getInt("rolle"));
				benutzer.setKontostand(rs.getInt("kontostand"));
			}
			// ConnectionPoolingImplemenation.putConnection(c);
			System.out.println(benutzer.getName());
			return benutzer;

		} catch (SQLException sqle) {
			System.out.println("Es trat ein Fehler im SQL auf.");
			sqle.printStackTrace();
			// ConnectionPoolingImplemenation.putConnection(c);
		}
		return null;
	}

	public void dividendeAnBenutzer() {
		try {
			PreparedStatement preparedStatement = c.prepareStatement("SELECT kuerzel, dividende FROM tbl_aktien");
			ResultSet rs = preparedStatement.executeQuery();

			AktieDAO aktieDao = new AktieDAO();
			int neueDividende;
			while (rs.next()) {
				neueDividende = DividendenRechner.neueDividende(rs.getInt("dividende"),
						DividendenRechner.MITTLERE_STREUUNG, 20, 666);
				aktieDao.updateLetzteDividende(rs.getString("kuerzel"), neueDividende);

				preparedStatement = c.prepareStatement(
						"UPDATE tbl_benutzer " + "INNER JOIN aktie ON benutzer.benutzerId=aktie.fk_benutzerId "
								+ "SET kontostand=kontostand+? " + "WHERE aktie.kuerzel=?");
				preparedStatement.setDouble(1, neueDividende);
				preparedStatement.setString(2, rs.getString("kuerzel"));
				preparedStatement.executeUpdate();

				preparedStatement.close();

			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

}
