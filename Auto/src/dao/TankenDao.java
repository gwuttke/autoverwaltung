package dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlAbfrage;
import database.SqlServer;
import domain.Tanken;

public class TankenDao extends SqlServer {

	private static List<Tanken> tankenList = new ArrayList<Tanken>();


	public static List<Tanken> getTankenList() {
		return tankenList;
	}

	
	public static void setTankenList() throws SQLException {
		
		ResultSet rsTanken = retrieveRS(SqlAbfrage.SQL_TANKEN);

		try {
			while (rsTanken.next()) {
				Tanken t = new Tanken();

				t.setAutoId(rsTanken.getInt("Auto_ID"));
				t.setBenzinArtId(rsTanken.getInt("BenzinartID"));
				t.setKmStand(rsTanken.getInt("Km_Stand"));
				t.setKosten((BigDecimal) rsTanken.getBigDecimal("Kosten"));
				t.setLandId(rsTanken.getInt("Land"));
				t.setLiter(rsTanken.getBigDecimal("Liter"));
				t.setOrtId(rsTanken.getInt("Ort"));
				t.setPreisProLiter(rsTanken.getBigDecimal("Preis_p_Liter"));
				t.setVoll(rsTanken.getInt("Voll"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
