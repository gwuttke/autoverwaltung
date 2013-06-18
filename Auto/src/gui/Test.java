package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.SqlServer;



public class Test extends SqlServer {

	private static SqlServer sqlS = new SqlServer();
	private static Test t = new Test();
	
	private void displayRow(String titel, ResultSet rs) throws SQLException{
		System.out.println(titel);
		while (rs.next()){
			//System.out.println(rs.getString(1)+":" + rs.getString(2));
			System.out.println(rs.getString(2));
		}
	}
	
		
	public static void main (String args[]){
	
		try {
			sqlS.openConnection();
			t.displayRow("Benzienarten", sqlS.retrieveRS("SELECT * FROM T_BenzinArt"));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Das Passwort oder die Kennung ist falsch");
			
			e.printStackTrace();
		}
		
	}
	
}
