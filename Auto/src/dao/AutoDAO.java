package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Procedures;
import database.SqlServer;
import domain.Auto;

public class AutoDAO {
	SqlServer sqlS = new SqlServer();
	Procedures proc = new Procedures();
	private ResultSet rs;

	private String autoSQL2 = "SELECT Auto_ID, Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung FROM T_Auto ORDER BY Auto_ID;";

	public List<Auto> autos = new ArrayList<Auto>();

	public List<Auto> getAutos() {
		return autos;
	}

	private ResultSet getAutosRS() {

		ResultSet rs = null;
		try {
			sqlS.openConnection();
			rs = sqlS.retrieveRS(autoSQL2);
			// sqlS.closeResults(, sqlS.getRs(), sqlS.getConn());

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void setAutoList() throws SQLException {

		ResultSet rsAuto = this.getAutosRS();
		ResultSet rsAutoBenzinArten = AutoBenzin();
		int i = 0;
		String[] autoBenzin = new String[2];
		if (rsAutoBenzinArten != null) {
			autoBenzin = new String[this.length(rsAutoBenzinArten)];
			while (rsAutoBenzinArten.next()) {
				autoBenzin[i] = new String(
						rsAutoBenzinArten.getString("Benzinart"));
				i++;

			}
		}
		while (rsAuto.next()) {
			final Auto auto = new Auto();
			auto.setId(rsAuto.getInt("AUTO_ID"));
			auto.setKfz(rsAuto.getString("KENNZEICHEN"));

			auto.setBenzinArten(autoBenzin);

			auto.setErstZulassung(rsAuto.getDate("ErstZulassung"));
			auto.setKauf(rsAuto.getDate("Kauf_Datum"));
			auto.setKm(rsAuto.getInt("Anfangs_Km"));
			autos.add(auto);

		}

	}

	private ResultSet AutoBenzin() {
		rs = proc.getAutoBenzinArten();
		return rs;
	}

	private int length(ResultSet rs) {
		int count = 0;

		try {
			while (rs.next()) {
				count++;
			}
			return count;
		} catch (SQLException e) {
			return -1;
		}

	}

	public void addAuto(String kennzeichen, double anfangKm, Date kaufDatum,
			Date ErstZulassung) {

		String insertAuto = "INSERT INTO T_Auto(Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung) "
				+ "VALUES ('"
				+ kennzeichen
				+ "','"
				+ anfangKm
				+ "','"
				+ kaufDatum + "','" + ErstZulassung + "')";

		try {
			sqlS.executeUpdateStatemant(insertAuto);
		} catch (SQLException e) {
			System.out.println("The Table doesn't exists");
		} catch (ClassNotFoundException e) {
			System.out.println("Connection Failed!!");
		}

	}

}
