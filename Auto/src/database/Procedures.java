package database;

import gui.Test2Form;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;

public class Procedures extends database.SqlServer {
	
	private ResultSet rs = null;

	public ResultSet getKosten(JComboBox<String> comboBox, int jahr){
		String kennzeichen = comboBox.getSelectedItem().toString();
		return this.callKosten(kfz, jahr)
		
	}
	
	
	public ResultSet getKennzeichen(int AutoId) {
		return this.callKennzeichen(AutoId);
	}

	public ResultSet getAutoBenzinArten(JComboBox<String > comboBox) {
		if (comboBox.getItemCount() > 0) {
			String kennzeichen = comboBox.getSelectedItem()
					.toString();
			rs = callAutoBenzinArten(kennzeichen);
		} else {
			comboBox.addItem("Null");
		}
		return rs;
	}

	public ResultSet getAutoAlter(JComboBox<String > comboBox) {
		String kennzeichen = comboBox.getSelectedItem().toString();
		return callAutoAlter(kennzeichen);
	}

	/*
	 * // oder volgende Methode public ResultSet getAutoBenzinArten() { String
	 * kennzeichen = tf.getCombo2().getSelectedItem().toString(); rs =
	 * PAutoBenzinArten(kennzeichen); return rs; }
	 */
	private ResultSet callKennzeichen(int AutoId) {
		String kennzeichenOut = null;
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"{cal dbo.P_AutoKennzeichen (?,?) }");
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

	private ResultSet callAddAuto(String kfz, int kaufKm, Date kaufDatum,
			Date erstZulassung, String BenzinArten) {
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
			pstm.setString(6, BenzinArten);

			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			closeResults(getSt(), getRs(), getConn());
		}
	}

	private void callAddTanken(String kfz, int kmStand, String land,
			String ort, int voll, Double kosten, Date datum, Double liter,
			Double preisProLiter, int[] benzinArt) {
		int autoId = 0;
		int landId = 0;
		int ortId = 0;
		String strBenzinArt = "";

		// Int Array in einen String umwandeln mit kommata getrennt
		for (int benzin : benzinArt) {
			strBenzinArt += strBenzinArt + benzin + ",";
		}
		strBenzinArt = strBenzinArt.substring(0, strBenzinArt.length() - 1);

		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"call dbo.P_addTanken (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstm.setInt(1, kmStand);
			pstm.setString(2, land);
			pstm.setString(3, ort);
			pstm.setInt(4, voll);
			pstm.setDouble(5, kosten);
			pstm.setString(6, kfz);
			pstm.setDate(7, datum);
			pstm.setDouble(8, liter);
			pstm.setDouble(9, preisProLiter);
			pstm.setString(10, strBenzinArt);
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
}
