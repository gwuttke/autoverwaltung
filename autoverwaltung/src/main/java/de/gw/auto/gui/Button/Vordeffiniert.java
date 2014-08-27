package de.gw.auto.gui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.gw.auto.domain.Texte;

public class Vordeffiniert {

	
	JButton btnExit = new JButton(Texte.Form.Button.BEENDEN);
	
	
	public JButton getBtnExit(final JFrame frame) {
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Funktionen.exit(frame);
			}
		});
		return btnExit;
	}
	
}
