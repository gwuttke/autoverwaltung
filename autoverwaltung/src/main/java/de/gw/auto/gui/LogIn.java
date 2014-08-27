package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.Button.Vordeffiniert;

public class LogIn {
	
	private Settings settings = null;
	private JFrame frame = new JFrame();
	private JLabel lBenutzer = new JLabel(Texte.Form.Label.BENUTZER +":");
	private JLabel lPasswort = new JLabel(Texte.Form.Label.PASSWORT + ":");
	private JTextField tfBenutzer = new JTextField();
	private JPasswordField tfPasswort = new JPasswordField();
	private JButton btnLogIn = new JButton("LogIn");
	private JButton btnRegistrieren = new JButton("Registrieren");
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
		//jpCenter.add(btnRegistrieren);
		jpCenter.add(btnExit);
		
		con.add(jpCenter, BorderLayout.CENTER);
		//con.add(btnExit, BorderLayout.SOUTH);
		setListeners();
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private void setListeners(){
		btnLogIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Benutzer benutzer = null;
				try {
					settings = new de.gw.auto.service.LogIn().Login(tfBenutzer.getText(), new String(tfPasswort.getPassword()));
					benutzer = settings.getBenutzer();
				} catch (Exception e) {
					AllException.messageBox("Falscher Benutzer", e.getMessage());
					return;
				}
				if(settings.getBenutzer() != null){
					if(settings.getAutos().isEmpty()){ 
						new AddAuto(settings);
						
					}else{
						new ShowGui(settings);
					}					
					frame.dispose();
				}else{
					AllException.messageBox("Falscher Benutzer", "Falsche eingabe bitte versuchen sie es noch einmal");
					return;	
				}
			}
		});
		
	}
	
	
}
