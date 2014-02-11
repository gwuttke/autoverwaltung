package gui.Button;

import javax.swing.JFrame;

public class Funktionen extends JFrame {

	protected void cancel(JFrame frame) {
		frame.setVisible(false);
	}
	protected void exit(JFrame frame) {
		frame.dispose();
		System.exit(0);
	}
}
