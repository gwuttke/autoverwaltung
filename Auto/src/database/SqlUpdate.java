package database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import domain.Auto;
import domain.Benzinart;
import domain.Land;
import domain.Ort;
import domain.SonstigeAusgaben;

public class SqlUpdate extends SqlServer {

	public static void callInsertSonstigeAusgaben(SonstigeAusgaben sa) {
		final String insertSonAus = "INSERT INTO dbo.T_Sonstige_Ausgaben(Datum, Km_Stand, Komentar, Kosten, Auto_ID) VALUES ('"
				+ sa.getDatum()
				+ "', '"
				+ sa.getKmStand()
				+ "', '"
				+ sa.getKommentar()
				+ "', '"
				+ sa.getKmStand()
				+ "', '"
				+ sa.getAutoId() + "');";

		openConnection();
		try {
			executeUpdateStatemant(insertSonAus);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

	public static void callInsertTanken(Auto auto, int kmStand, Land land,
			Ort ort, int voll, BigDecimal kosten, Date sqlDate, double liter,
			BigDecimal preisPLiter, Benzinart benzinart) {
		final String INSERT_TANKEN = "INSERT INTO dbo.T_Tanken(Km_Stand, Land, Ort, Voll, Kosten, Auto_ID, Datum, Liter, Preis_p_Liter, BinzinartID) VALUES ('"
				+ kmStand
				+ "', '"
				+ land.getId()
				+ "', '"
				+ ort.getId()
				+ "', '"
				+ voll
				+ "', '"
				+ kosten
				+ "', '"
				+ auto.getId()
				+ "', '"
				+ sqlDate
				+ "', '"
				+ liter
				+ "', '"
				+ preisPLiter
				+ "', '" + benzinart.getId() + "');";

		openConnection();
		try {
			executeUpdateStatemant(INSERT_TANKEN);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();

	}

}
