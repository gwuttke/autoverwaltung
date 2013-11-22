package database;

public class TestDatenbank extends SqlServerOverINDI{
	
	public static void main(String[] args) {
		testDB();
	}
	
	private static void testDB(){
		openConnection();
		System.out.println("Verbindung steht");
		closeConnection();
		
	}

}
