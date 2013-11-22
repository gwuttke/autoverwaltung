package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SqlServerOverINDI {

	private static Connection con = null;

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
}
