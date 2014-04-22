package de.gw.auto.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SqlServerOverJNDI {

	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	public static Statement getStmt() {
		return stmt;
	}

	public static void setStmt(Statement stmt) {
		SqlServerOverJNDI.stmt = stmt;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		SqlServerOverJNDI.rs = rs;
	}

	public static Connection openConnection() {
		if (hasConnection() == false) {
			DataSource ds;
			try {
				ds = (DataSource) new InitialContext().lookup("Auto");
				con = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}

	public static void closeConnection() {
		if (hasConnection() == true) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean hasConnection() {
		if (con != null) {
			return true;
		}
		return false;
	}

	public static ResultSet datenAbfragen(String sql) {

		openConnection();
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.getSQLState();
		}
		closeConnection();

		return SqlServerOverJNDI.rs;

	}

	public static int updateDaten(String sql) {
		int rows = 0;
		try {
			openConnection();
			con.setAutoCommit(false);
			rows = stmt.executeUpdate(sql);
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return rows;
	}

	public static void closeResults() {
		if (SqlServerOverJNDI.stmt != null) {
			try {
				SqlServerOverJNDI.stmt.close();
				SqlServerOverJNDI.stmt = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (SqlServerOverJNDI.rs != null){
			try {
				SqlServerOverJNDI.rs.close();
				SqlServerOverJNDI.rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
