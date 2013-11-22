package database;

public class TestDatenbank extends SqlServerOverJNDI{
	
	public static void main(String[] args) {
		testDB();
	}
	
	private static void testDB(){
		openConnection();
		System.out.println("Verbindung steht");
		closeConnection();
		
	}

}
