package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.*;
import domain.Benzinart;

public class BenzinartDAO {
	SqlServer sqlS = new SqlServer();
	Procedures proced = new Procedures();

	public List<Benzinart> benzinList = new ArrayList<Benzinart>();
	

	public void setBenzinList() throws SQLException {
		ResultSet rs = this.BenzinartRS();

		if (rs != null) {
			try {
				while (rs.next()) {
					final Benzinart benzin = new Benzinart();
					benzin.setName(rs.getString("Benzinart"));
					benzinList.add(benzin);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			System.out.println("keine Benzinarten zugewiesen");
		}
	}

	public List<Benzinart> getBenzinList() {
		return benzinList;
	}

	private ResultSet BenzinartRS() {

		ResultSet rsBenzin = null;

		try {
			sqlS.openConnection();
			rsBenzin = sqlS
					.retrieveRS("SELECT Benzienart FROM dbo.T_Benzinart");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return rsBenzin;

	}

}
