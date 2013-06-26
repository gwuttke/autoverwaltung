package dao;

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

	private List<Auto> autoList = new ArrayList<Auto>();

	private ResultSet autoBenzinRS;

	public ResultSet getAutoBenzinRS() {
		return autoBenzinRS;
	}

	public void setAutoBenzinRS(String kfz) {
		this.autoBenzinRS = proc.getAutoBenzinArten(kfz);

	}

	public List<Auto> getAutoList() {
		return autoList;
	}

	private ResultSet getAutosRS() {

		ResultSet rs = null;

		rs = proc.getAutos();

		return rs;
	}

	public void setAutoList() throws SQLException {

		ResultSet rsAuto = this.getAutosRS();
	
		int i = 0;
		String[] autoBenzin = new String[this.length(autoBenzinRS)];
		while (autoBenzinRS.next()) {
			autoBenzin[i] = autoBenzinRS.getString("Benzinart");
			i++;
		}

		while (rsAuto.next()) {
			final Auto auto = new Auto();

			auto.setId(rsAuto.getInt("AUTO_ID"));
			auto.setKfz(rsAuto.getString("KENNZEICHEN"));
			auto.setBenzinArten(autoBenzin);
			auto.setErstZulassung(rsAuto.getDate("ErstZulassung"));
			auto.setKauf(rsAuto.getDate("Kauf_Datum"));
			auto.setKmKauf(rsAuto.getInt("Anfangs_Km"));
			//auto.setKmAktuell(kmAktuell)
			//auto.setAutoAlter(autoAlter)
			//auto.setKaufAlter(kaufAlter)
			//auto.setKmGefahren();
			
			autoList.add(auto);

		}

	}

	private int length(ResultSet rs) {
		int count = 0;

		try {
			rs.last();
			count = rs.getRow();
			return count;
		} catch (SQLException e) {
			return -1;
		}

	}

}
