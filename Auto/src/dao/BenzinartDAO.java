package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.*;
import domain.Benzinart;

public class BenzinartDAO extends SqlServer {
	SqlServer sqlS = new SqlServer();
	Procedures proced = new Procedures();

	private List<Benzinart> benzinList = new ArrayList<Benzinart>();

	public void setBenzinList() {
		ResultSet rs = this.BenzinartRS();

		if (rs != null) {
			try {
				while (rs.next()) {
					Benzinart benzin = new Benzinart();
					benzin.setName(rs.getString("Benzienart"));
					benzin.setId(rs.getInt("ID"));
					benzinList.add(benzin);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeResults(getSt(), getRs(), getConn());
			}
		} else {
			System.out.println("keine Benzinarten zugewiesen");
		}
	}

	public List<Benzinart> getBenzinList() {
		if (benzinList.isEmpty()) {
			setBenzinList();
		}
		return benzinList;
	}

	private ResultSet BenzinartRS() {

		ResultSet rsBenzin = null;

		try {
			openConnection();
			return sqlS.retrieveRS(SqlAbfrage.SQL_BENZINART);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rsBenzin;

	}

}
