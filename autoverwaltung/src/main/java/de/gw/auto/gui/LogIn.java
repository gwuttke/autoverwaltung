package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Texte;
import de.gw.auto.gui.Button.Vordeffiniert;

public class LogIn {
	
	private Benutzer benutzer;
	private JFrame frame = new JFrame();
	private JLabel lBenutzer = new JLabel(Texte.Form.Label.BENUTZER +":");
	private JLabel lPasswort = new JLabel(Texte.Form.Label.PASSWORT + ":");
	private JTextField tfBenutzer = new JTextField();
	private JPasswordField tfPasswort = new JPasswordField();
	private JButton btnLogIn = new JButton("LogIn");
	private JButton btnExit = new Vordeffiniert().getBtnExit(frame);
	
	public static void main(String[] args) {
		new LogIn();
	}
	
	public LogIn() {
		
		Container con = frame.getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel jpCenter = new JPanel(new GridLayout(3, 2));
		jpCenter.add(lBenutzer);
		jpCenter.add(tfBenutzer);
		jpCenter.add(lPasswort);
		jpCenter.add(tfPasswort);
		jpCenter.add(btnLogIn);
		jpCenter.add(btnExit);
		
		con.add(jpCenter, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
	}
}
