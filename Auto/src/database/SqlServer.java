package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SqlServer {

	//private final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Auto;integratedSecurity=true;";
	private final static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Auto;user=User;password=123;";

	private static Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public Connection getConn() {
		return conn;
	}

	private static void setConn(Connection conn2) {
		conn = conn2;
	}

	public Statement getSt() {
		return this.st;
	}

	public  void setSt(Statement st1) {
		this.st = st1;
	}

	
	public PreparedStatement getPs() {
		return this.ps;
	}

	public void setPs(PreparedStatement ps, String x) {
		this.ps = ps;
		try {
			this.ps.setString(1, x);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
	}

	public ResultSet retrieveRS(String sql) throws SQLException {
			openConnection();
			
			this.setSt(conn.createStatement());
			this.rs = this.st.executeQuery(sql);
			
			return this.rs;
	}

	public ResultSet retrievePs(String sp_name, String string, String values)
			throws SQLException {
		this.setPs(getConn().prepareStatement("{call " + sp_name + "}"), string);
		this.ps.setEscapeProcessing(true);
		this.ps.setQueryTimeout(90);
		// if (values.length - 1 < 0) {
		// for (int i = 0; i <= values.length - 1; i++) {
		this.ps.setString(1, values);
		// }
		// } else {
		// return null;
		// }
		this.rs = this.ps.executeQuery();

		return this.rs;

	}

	public int executeUpdateStatemant(String sql) throws SQLException,
			ClassNotFoundException {

		openConnection();
		this.st = conn.createStatement();
		int count = this.st.executeUpdate(sql);
		// System.out.println("ROWS AFFECTED: " + count);
		this.st.close();
		return count;
	}

	public static Connection openConnection() {
		if (hasConnection() == false) {
			try {
				setConn(DriverManager.getConnection(connectionUrl));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return conn;
	}

	public static boolean hasConnection() {
		if (conn != null) {
			return true;
		}
		return false;
	}

	public void closeResults(Statement st, ResultSet rs, Connection conn) {
		try {
			if (st != null) {
				st.close();
				setSt(null);
			}

			if (rs != null) {
				rs.close();
				setRs(null);
			}
			if (conn != null) {
				closeConnection();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void closeConnection() {

		if (hasConnection() == true) {
			try {
				conn.close();
				setConn(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
