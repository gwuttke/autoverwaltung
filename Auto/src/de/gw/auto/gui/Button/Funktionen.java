package de.gw.auto.gui.Button;

import javax.swing.JFrame;

public class Funktionen extends JFrame {

	public void cancel(JFrame frame) {
		frame.setVisible(false);
	}
	public void exit(JFrame frame) {
		frame.dispose();
		System.exit(0);
	}
}
