package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import database.Procedures;
import database.SqlServer;
import domain.Auto;

public class AutoDAO {
	SqlServer sqlS = new SqlServer();
	Procedures proc = new Procedures();
	Auto a = new Auto();

	private List<Auto> autoList = new ArrayList<Auto>();

	private ResultSet autoBenzinRS = null;

	public ResultSet getAutoBenzinRS(String kfz) {
		if (this.autoBenzinRS == null) {
			setAutoBenzinRS(kfz);
		}
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

		String[] autoBenzin;

		while (rsAuto.next()) {
			final Auto auto = new Auto();
			int i = 0;

			autoBenzin = new String[length(getAutoBenzinRS(rsAuto
					.getString("KENNZEICHEN")))];
			auto.setId(rsAuto.getInt("AUTO_ID"));
			auto.setKfz(rsAuto.getString("KENNZEICHEN"));

			// länge des String Arrays Benzinarten je Auto setzen

			// Auto Benzinarten füllen
			while (getAutoBenzinRS(rsAuto.getString("KENNZEICHEN")).next()) {
				autoBenzin[i] = getAutoBenzinRS(rsAuto.getString("KENNZEICHEN"))
						.getString("Benzinart");
				i++;
			}

			auto.setBenzinArten(autoBenzin);
			auto.setErstZulassung(rsAuto.getDate("ErstZulassung"));
			auto.setKauf(rsAuto.getDate("Kauf_Datum"));
			auto.setKmKauf(rsAuto.getInt("Anfangs_Km"));
			auto.setKmAktuell(0);
			//berechnung des alters vom Auto
			auto.setAlter();
			
			autoList.add(auto);

		}
	}

	public void updateAutoList(int autoID, String[] benzinarten, Date eZulassung, Date kauf, int anfKm, int aktuKm){
	for (Auto a : getAutoList()){
		if (a.getId() == autoID){
			if (benzinarten != null){
				a.setBenzinArten(benzinarten);
			}
			if (eZulassung != null){
				a.setErstZulassung((java.sql.Date) eZulassung);
			}
			if (kauf != null){
				a.setKauf((java.sql.Date) kauf);
			}
			if (anfKm > 0){
				a.setKmKauf(anfKm);
			}
			if (aktuKm > 0 && aktuKm > a.getKmKauf()){
				a.setKmAktuell(aktuKm);
				a.setKmGefahren();
			}
			
		}
	}
	
	}
	
	private int length(ResultSet rs) {
		int count = 0;

		try {
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
			return count;
		} catch (SQLException e) {
			return -1;
		}

	}
}
