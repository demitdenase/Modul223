package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.connectionPooling.*;

import javax.naming.NoPermissionException;

import model.Aktie;

public class AktieDAO extends AbstractDAO {
	Connection c;

	public AktieDAO() throws SQLException {
		try {
			c = ConnectionPoolingImplementation.getInstance().getConnection();
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Aktie> getAktien() {
		List<Aktie> aktienListe = new ArrayList<Aktie>();
		try {

			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_aktie`");
			while (rs.next()) {
				Aktie aktie = new Aktie();
				aktie.setId(rs.getInt(1));
				aktie.setName(rs.getString(2));
				aktie.setNominalwert(rs.getDouble(3));
				aktie.setDividende(rs.getDouble(4));
				aktienListe.add(aktie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aktienListe;
	}

	public Aktie findAktie(int aktienId) throws SQLException {
		Statement stm = c.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM `mytrade`.`tbl_aktie` WHERE `aktien_pk`= " + aktienId);
		if (rs.next()) {
			Aktie aktie = new Aktie();
			aktie.setId(rs.getInt(1));
			aktie.setName(rs.getString(2));
			aktie.setNominalwert(rs.getDouble(3));
			aktie.setDividende(rs.getDouble(4));
			return aktie;
		} else {
			return null;
		}

	}

	public Aktie insertAktie(Aktie aktie) throws SQLException {
		Statement stm = c.createStatement();
		int numero;
		numero = stm.executeUpdate("INSERT INTO `mytrade`.`tbl_aktie`(`name`,`nominalwert`,`dividende`)VALUES ("
				+ aktie.getName() + "', "+ aktie.getNominalwert() + ", "+ aktie.getDividende() + ")", Statement.RETURN_GENERATED_KEYS);
		c.commit();
		aktie.setId(numero);
		return aktie;
	}

	public Aktie updateAktie(Aktie aktie) throws SQLException {
		Statement stm = c.createStatement();
		String sql = "UPDATE `mytrade`.`tbl_benutzer` SET " + "`name` = '"+ aktie.getName() + "'" + ", `nominalwert` = " + aktie.getNominalwert()
				+ ", `dividende` = " + aktie.getDividende()+ " WHERE `aktien_pk` = " + aktie.getId();
		stm.executeUpdate(sql);
		c.commit();
		return aktie;
	}
}
