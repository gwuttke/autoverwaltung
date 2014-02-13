package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.*;
import domain.Benzinart;
import domain.SonstigeAusgaben;

public class BenzinartDAO extends SqlServer {
	SqlServer sqlS = new SqlServer();
	Procedures proced = new Procedures();

	private static List<Benzinart> benzinartList = new ArrayList<Benzinart>();

	public static List<Benzinart> getBenzinartList() {
		return benzinartList;
	}
	
	public void intoDatabase(SonstigeAusgaben sa){
		
	}
	
	public Benzinart getBenzinart(int id){
		for(Benzinart ba : benzinartList){
			if(ba.getId().equals(id)){
				return ba;
			}
		}
		return null;
	}

	public static void setBenzinList() throws SQLException {
		ResultSet rs = null;
		
		rs = retrieveRS(SqlAbfrage.SQL_BENZINART);

		if (rs != null) {
			try {
				while (rs.next()) {
					Benzinart benzin = new Benzinart();
					benzin.setName(rs.getString("Benzienart"));
					benzin.setId(rs.getInt("ID"));
					benzinartList.add(benzin);
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
	

}
