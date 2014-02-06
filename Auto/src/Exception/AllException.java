package Exception;

import javax.swing.JOptionPane;

public class AllException extends Exception {

	public static void messageBox(String titel, String text){
		JOptionPane.showMessageDialog(null, text, titel, JOptionPane.OK_OPTION);
	}
	
	public static void error(String text, Exception exc) throws Exception{
		throw new Exception(text,exc);
	}
	
}
