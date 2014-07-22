package de.gw.auto.gui.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.gw.auto.dao.BenzinartDAO;
import de.gw.auto.database.SqlServer;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Settings;



public class Test extends SqlServer {

	private BenzinartDAO bDAO = new BenzinartDAO();
		
	private static void displayRow(String titel, ResultSet rs) throws SQLException{
		System.out.println(titel);
		while (rs.next()){
			//System.out.println(rs.getString(1)+":" + rs.getString(2));
			System.out.println(rs.getString(2));
		}
	}
	
	public static void main(String[] args) {
		new Test();
	}
		
	public Test(){
		for(Benzinart b : bDAO.getBenzinartList()){
			System.out.println(b.getName());
		}
	}	
}
