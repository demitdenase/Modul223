package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.connectionPooling.*;

import javax.naming.NoPermissionException;

import model.Verkauf;

public class VerkaufDAO extends AbstractDAO {
	Connection c;
	BenutzerDAO benutzerDAO = new BenutzerDAO();

	public VerkaufDAO() throws SQLException {
		try {
			c = ConnectionPoolingImplementation.getInstance().getConnection();
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Verkauf> getVerkaufsliste() {
		List<Verkauf> verkaufsListe = new ArrayList<Verkauf>();
		try {

			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_verkauf`");
			while (rs.next()) {
				Verkauf verkauf = new Verkauf();
				verkauf.setVerkaufsid(rs.getInt(1));
				// verkauf.setAktie(rs.(2));
				verkauf.setBenutzer(benutzerDAO.findPerson(rs.getInt(3)));
				verkauf.setPreis(rs.getDouble(4));
				verkaufsListe.add(verkauf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return verkaufsListe;
	}

	public Verkauf findVerkauf(int verkaufsId) throws SQLException {
		Statement stm = c.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM tbl_verkauf WHERE Person_ID= " + verkaufsId);
		if (rs.next()) {
			Verkauf verkauf = new Verkauf();
			verkauf.setVerkaufsid(rs.getInt(1));
			// verkauf.setAktie(rs.(2));
			verkauf.setBenutzer(benutzerDAO.findPerson(rs.getInt(3)));
			verkauf.setPreis(rs.getDouble(4));
			return verkauf;
		} else {
			return null;
		}

	}

	public Verkauf insertVerkauf(Verkauf verkauf) throws SQLException {
		Statement stm = c.createStatement();
		int numero;
		numero = stm.executeUpdate("INSERT INTO `mytrade`.`tbl_verkauf`(`aktien_fk`,`benutzer_fk`,`preis`)VALUES ("
				+ verkauf.getAktie().getId() + ", " + verkauf.getBenutzer().getBenutzerid() + ", " + verkauf.getPreis()
				+ ")", Statement.RETURN_GENERATED_KEYS);
		c.commit();
		verkauf.setVerkaufsid(numero);
		;
		return verkauf;
	}

	public Verkauf updatePerson(Verkauf verkauf) throws SQLException {
		Statement stm = c.createStatement();
		String sql = "UPDATE `mytrade`.`tbl_verkauf` SET " + ", `verkauf_pk` = " + verkauf.getVerkaufsid()
				+ "`benutzer_fk` = " + verkauf.getBenutzer().getBenutzerid() + ", `aktien_fk` = "
				+ verkauf.getAktie().getId() + ", `preis` = " + verkauf.getPreis() + " WHERE `verkauf_pk` = "
				+ verkauf.getVerkaufsid();
		stm.executeUpdate(sql);
		c.commit();
		return verkauf;
	}
}
