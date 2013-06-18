package database;

import gui.Test2Form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Procedures extends database.SqlServer {
	final static Test2Form tf = new Test2Form();
	private ResultSet rs = null;

	public ResultSet getAutoBenzinArten() {
		if(tf.getBenzinCombo().getItemCount() > 0){
		String kennzeichen = tf.getBenzinCombo().getSelectedItem().toString();
		rs = ProcedurKennzeichen("dbo.P_AutoBenzinArten (?)", kennzeichen);
		} else{
			Test2Form.getBenzinCombo().addItem("Null");
			}
		return rs;
	}

	public ResultSet getAutoAlter(){
		String kennzeichen = tf.getAutoCombo().getSelectedItem().toString();
		rs = ProcedurKennzeichen("dbo.P_AutoAlter (?)", kennzeichen);
		return rs;
	}
	/*
	 * // oder volgende Methode public ResultSet getAutoBenzinArten() { String
	 * kennzeichen = tf.getCombo2().getSelectedItem().toString(); rs =
	 * PAutoBenzinArten(kennzeichen); return rs; }
	 */
	private ResultSet ProcedurKennzeichen(String procedure, String kennzeichen) {
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"{call " + procedure + "}");
			pstm.setString(1, kennzeichen);
			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();

			return rs;
		}
		// } finally {
		// try {
		// closeResults(getSt(), getRs(), getConn());
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private ResultSet PAutoBenzinArten(String kennzeichen) {
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
		}
		// } finally {
		// try {
		// closeResults(getSt(), getRs(), getConn());
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private ResultSet PAutoAlter(String kennzeichen) {
		try {
			openConnection();
			PreparedStatement pstm = getConn().prepareStatement(
					"{call dbo.P_AutoAlter (?)}");
			pstm.setString(1, kennzeichen);
			rs = pstm.executeQuery();

			return rs;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			try {
				closeResults(getSt(), this.rs, getConn());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
