package database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.BenzinartDAO;
import domain.Auto;
import domain.Benzinart;

public class Procedures extends database.SqlServer {

	// Klassen
	//BenzinartDAO benzinartDao = new BenzinartDAO();

	private ResultSet rs = null;

	public ResultSet getAutos() {
		return this.callAuto();
	}

	public void getAutoUpdate(String kfz, int AktuellKm, int[] BenzinArten) {
		this.callUpdateAuto(kfz, AktuellKm, BenzinArten);
	}

	public void getNewSonstigeAusgabe(String kennzeichen, Date datum,
			int kmStand, String kommentar, Double kosten) {

		this.callAddSonstigeAusgaben(datum, kmStand, kommentar, kosten,
				kennzeichen);
	}

	public void getRefuel(String kennzeichen, int kmStand, String land,
			String ort, int voll, BigDecimal kosten, Date datum, BigDecimal liter,
			BigDecimal preisProLiter, int benzinArt) {

		callAddTanken(kennzeichen, kmStand, land, ort, voll, kosten, datum,
				liter, preisProLiter, benzinArt);

	}

	public ResultSet getKosten(String kfz, int jahr) {
		return this.callKosten(kfz, jahr);
	}

	public ResultSet getKennzeichen(int AutoId) {
		return this.callKennzeichen(AutoId);
	}

	public ResultSet getAutoBenzinArten(String kfz) {
		rs = callAutoBenzinArten(kfz);

		return rs;
	}

	public ResultSet getAutoAlter(String kfz) {
		String kennzeichen = kfz;
		return callAutoAlter(kennzeichen);
	}

	public void setAddAuto(String kfz, int kaufKm, Date kaufDatum,
		Date erstZulassung, int[] benzinArten) {
		
		
		Auto auto = new Auto();
		auto.setKfz(kfz);
		auto.setKmKauf(kaufKm);
		auto.setKauf(kaufDatum);
		auto.setErstZulassung(erstZulassung);
		auto.setBenzinArten(benzinArten);
		

		callAddAuto(auto.getKfz(), auto.getKmKauf(), auto.getKauf(),
				auto.getErstZulassung(), benzinArrayToString(benzinArten));
		
		

	}

	private ResultSet callKennzeichen(int AutoId) {
		String kennzeichenOut = null;
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"{call dbo.P_AutoKennzeichen (?,?) }");
			pstm.setString(1, kennzeichenOut);
			pstm.setInt(2, AutoId);
			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();

			return rs;

		} finally {

			closeResults(getSt(), getRs(), getConn());
		}
	}

	private ResultSet callAutoBenzinArten(String kennzeichen) {
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"{call dbo.P_AutoBenzinArten (?)}");
			pstm.setString(1, kennzeichen);
			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();

			return rs;
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}
	}

	private ResultSet callAutoAlter(String kfz) {
		int autoId = 0;
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"{call dbo.P_AutoAlter (?,?)}");
			pstm.setString(1, kfz);
			pstm.setInt(2, autoId);
			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			closeResults(getSt(), this.rs, getConn());
		}
	}

	private ResultSet callKosten(String kfz, int jahr) {
		int autoId = 0;
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.P_KostenGesammt (?,?,?)");
			pstm.setString(1, kfz);
			pstm.setInt(2, jahr);
			pstm.setInt(3, autoId);
			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}

	}

	private ResultSet callAuto() {
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.V_Auto ");

			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}

	}

	private ResultSet callAddAuto(String kfz, int kaufKm, Date kaufDatum,
			Date erstZulassung, String benzinArten) {
		int autoId = 0;

		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.P_addAuto (?,?,?,?,?,?)");
			pstm.setString(1, kfz);
			pstm.setInt(2, kaufKm);
			pstm.setDate(3, kaufDatum);
			pstm.setDate(4, erstZulassung);
			pstm.setInt(5, autoId);
			pstm.setString(6, benzinArten);

			rs = pstm.executeQuery();
			System.out.println("Die übertragung ist beendet!!");
			return rs;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}
		
	}

	private void callAddTanken(String kfz, int kmStand, String land,
			String ort, int voll, BigDecimal kosten, Date datum, BigDecimal liter,
			BigDecimal preisProLiter, int benzinArt) {
		int autoId = 0;
		int landId = 0;
		int ortId = 0;

		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.P_addTanken (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstm.setInt(1, kmStand);
			pstm.setString(2, land);
			pstm.setString(3, ort);
			pstm.setInt(4, voll);
			pstm.setBigDecimal(5, kosten);
			pstm.setString(6, kfz);
			pstm.setDate(7, datum);
			pstm.setBigDecimal(8, liter);
			pstm.setBigDecimal(9, preisProLiter);
			pstm.setInt(10, benzinArt);
			pstm.setInt(11, autoId);
			pstm.setInt(12, landId);
			pstm.setInt(13, ortId);

			rs = pstm.executeQuery();

		} catch (Exception e) {
			e.getMessage();
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}
	}

	private void callAddSonstigeAusgaben(Date datum, int kmStand,
			String kommentar, Double kosten, String kfz) {

		int autoId = 0;
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.P_addTanken (?,?,?,?,?,?)");
			pstm.setDate(1, datum);
			pstm.setInt(2, kmStand);
			pstm.setString(3, kommentar);
			pstm.setDouble(4, kosten);
			pstm.setString(5, kfz);
			pstm.setInt(6, autoId);

			rs = pstm.executeQuery();

		} catch (Exception e) {
			e.getMessage();
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}

	}

	private void callUpdateAuto(String kfz, int AktuellKm, int[] BenzinArten) {
		int autoID = 0;
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.P_addTanken (?,?,?,?)");
			pstm.setString(1, kfz);
			pstm.setInt(2, AktuellKm);
			pstm.setString(3, benzinArrayToString(BenzinArten));
			pstm.setInt(4, autoID);

			rs = pstm.executeQuery();

		} catch (Exception e) {
			e.getMessage();
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}

	}

	private String benzinArrayToString(int[] a) {
		String strBenzinArten = "";
		// Int Array in einen String umwandeln mit kommata getrennt
		for (int benzin : a) {
			strBenzinArten += strBenzinArten + benzin + ",";
		}
		// das letzte komma löschen
		strBenzinArten = strBenzinArten.substring(0,
				strBenzinArten.length() - 1);

		return strBenzinArten;

	}

}
