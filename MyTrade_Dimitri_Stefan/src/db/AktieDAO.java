package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NoPermissionException;

import db.connectionPooling.ConnectionPooling;
import db.connectionPooling.ConnectionPoolingImplementation;
import model.Aktie;
import model.Benutzer;

public class AktieDAO extends AbstractDAO {
	Connection c;
	ArrayList<Aktie> portFolioList;

	public AktieDAO() throws SQLException {
		try {
			c = ConnectionPoolingImplementation.getInstance().getConnection();
			c.setAutoCommit(false);
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
				aktie.setKuerzel(rs.getString(5));
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
			aktie.setKuerzel(rs.getString(5));
			return aktie;
		} else {
			return null;
		}

	}

	public Aktie insertAktie(Aktie aktie) throws SQLException {
		Statement stm = c.createStatement();
		int numero;
		numero = stm.executeUpdate("INSERT INTO `mytrade`.`tbl_aktie`(`name`,`nominalwert`,`dividende`,`kuerzel`)VALUES ('"
				+ aktie.getName() + "', "+ aktie.getNominalwert() + ", "+ aktie.getDividende() +",'" + aktie.getKuerzel()+"')", Statement.RETURN_GENERATED_KEYS);
		c.commit();
		aktie.setId(numero);
		return aktie;
	}

	public Aktie updateAktie(Aktie aktie) throws SQLException {
		Statement stm = c.createStatement();
		String sql = "UPDATE `mytrade`.`tbl_benutzer` SET " + "`name` = '"+ aktie.getName() + "'" + ", `nominalwert` = " + aktie.getNominalwert()
				+ ", `dividende` = " + aktie.getDividende() +",'" + aktie.getKuerzel() + "' WHERE `aktien_pk` = " + aktie.getId();
		stm.executeUpdate(sql);
		c.commit();
		return aktie;
	}

	public int getAnzahlAktieBySymbol(String kuerzel){
		int anzahlAktien = 0;
		try{
			Benutzer benutzer = new Benutzer().getUserObjectFromSession();
			System.out.println("Connection: " + c);
			PreparedStatement preparedStatement = c.prepareStatement("SELECT aktien_pk FROM tbl_aktien WHERE kuerzel = ? AND fk_benutzerId = ?");
			preparedStatement.setString(1, kuerzel);
			preparedStatement.setInt(2, benutzer.getBenutzerID());
			
			ResultSet rs = preparedStatement.executeQuery();
						while(rs.next()){
				anzahlAktien ++;
			}
		connectionPooling.putConnection(con);
		
		} catch(SQLException sqle){
			System.out.println("Es trat ein Fehler im SQL auf.");
			sqle.printStackTrace();
			connectionPooling.putConnection(con);
		}
		
		return anzahlAktien;
	}
	

}
