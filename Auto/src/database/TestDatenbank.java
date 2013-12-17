package database;

import java.sql.SQLException;

import domain.Auto;

public class TestDatenbank extends SqlAbfrage {

	public static void main(String[] args) {
		testDB();
	}

	private static void testDB() {
		openConnection();
		ausgabe();
		closeConnection();

	}

	private static void ausgabe() {

		giveAuto();
		try {
			while (getRs().next()) {
				Auto a = new Auto(getRs().getString("Kennzeichen"), getRs()
						.getInt("Auto_ID"), getRs().getInt("Anfangs_Km"),
						getRs().getDate("Kauf_Datum"), getRs().getDate(
								"ErstZulassung"), null, getRs().getInt(
								"Aktuell_Km"));
				a.toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
