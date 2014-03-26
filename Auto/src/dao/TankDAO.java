package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.AllException;
import database.SqlAbfrage;
import database.SqlServer;
import domain.Tank;

public class TankDAO extends SqlServer {
	
	

	private static List<Tank> tankfuellungList = new ArrayList<Tank>();

	public List<Tank> getTankList() {
		if (tankfuellungList.isEmpty() == true){
			try {
				setTankList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			getTankList();
		}
		
		return tankfuellungList;
	}
	
	
	public Tank getTank(int id){
		for(Tank ta : tankfuellungList){
			if(ta.getId() == id){
				return ta;
			}
		}
		return null;
	}

	public static void setTankList() throws SQLException {
		ResultSet rs = null;
		
		rs = retrieveRS(SqlAbfrage.SQL_Tankfuellung);

		if (rs != null) {
			try {
				while (rs.next()) {
					Tank tank = new Tank();
					tank.setBeschreibung(rs.getString("Beschreibung"));
					tank.setId(rs.getInt("Id"));
					tankfuellungList.add(tank);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeResults(getSt(), getRs(), getConn());
			}
		} else {
			AllException.messageBox("Daten fehler", "Es wurden keine Daten gefunden");
		}
	}
	

}
