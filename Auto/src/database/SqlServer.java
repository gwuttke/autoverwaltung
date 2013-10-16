package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SqlServer {

	private final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Auto;integratedSecurity=true;";
	// "jdbc:sqlserver://localhost:1433;databaseName=Auto;user=Test;passwort=Test123;";

	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public Connection getConn() {
		return conn;
	}

	private void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public PreparedStatement getPs() {
		return ps;
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
		if (hasConnection() == false) {
			try {
				this.openConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			this.setSt(this.conn.createStatement());
			this.setRs(this.st.executeQuery(sql));
			
			return this.getRs();
	}

	public ResultSet retrievePs(String sp_name, String string, String values)
			throws SQLException {
		this.setPs(getConn().prepareStatement("{call " + sp_name + "}"), string);
		ps.setEscapeProcessing(true);
		ps.setQueryTimeout(90);
		// if (values.length - 1 < 0) {
		// for (int i = 0; i <= values.length - 1; i++) {
		ps.setString(1, values);
		// }
		// } else {
		// return null;
		// }
		this.setRs(ps.executeQuery());

		return this.getRs();

	}

	public int executeUpdateStatemant(String sql) throws SQLException,
			ClassNotFoundException {

		openConnection();
		this.setSt(conn.createStatement());
		int count = st.executeUpdate(sql);
		// System.out.println("ROWS AFFECTED: " + count);
		st.close();
		return count;
	}

	public Connection openConnection() throws ClassNotFoundException,
			SQLException {
		if (hasConnection() == false) {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.setConn(DriverManager.getConnection(connectionUrl));
			return this.getConn();

		} else {
			return this.getConn();
		}

	}

	public boolean hasConnection() {
		if ((this.conn != null)) {
			return true;
		}
		return false;
	}

	public void closeResults(Statement st, ResultSet rs, Connection conn) {
		try {
			if (this.st != null) {
				this.st.close();
				this.setSt(null);
			}

			if (this.rs != null) {
				this.rs.close();
				this.setRs(null);
			}
			if (this.conn != null) {
				this.closeConnection();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void closeConnection() {

		if (this.hasConnection() == true) {
			try {
				this.conn.close();
				this.setConn(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
