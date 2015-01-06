package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.gw.auto.Constans;
import de.gw.auto.dao.Berechnung;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.Button.Funktionen;
import de.gw.auto.gui.Button.Vordeffiniert;
import de.gw.auto.service.Versionierungsservice;

public class LogIn implements ComponentListener {

	private Settings settings = null;
	private JFrame frame = new JFrame("Anmeldung");
	private JLabel lBenutzer = new JLabel(Texte.Form.Label.BENUTZER + ":");
	private JLabel lPasswort = new JLabel(Texte.Form.Label.PASSWORT + ":");
	private JLabel lVersion = new JLabel(Versionierungsservice.getCurrentVersionString());
	private JTextField tfBenutzer = new JTextField();
	private JPasswordField tfPasswort = new JPasswordField();
	private JButton btnLogIn = new JButton("Anmelden");
	private JButton btnRegistrieren = new JButton("Registrieren");
	private JButton btnExit = new Vordeffiniert().getBtnExit(frame);

	public static void main(String[] args) {
		new LogIn();
	}

	public LogIn() {

		Container con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel jpCenter = new JPanel(new GridLayout(4, 2));
		JPanel jpSouth = new JPanel(new GridLayout(1, 2));
		jpCenter.add(lBenutzer);
		jpCenter.add(tfBenutzer);
		jpCenter.add(lPasswort);
		jpCenter.add(tfPasswort);
		jpCenter.add(btnRegistrieren);
		jpCenter.add(btnLogIn);
		jpSouth.add(btnExit);
		jpCenter.add(lVersion);
		jpCenter.add(jpSouth);

		con.add(jpCenter, BorderLayout.CENTER);
		//con.add(jpSouth, BorderLayout.SOUTH);
		setListeners();
		frame.pack();
		frame.setSize(new Dimension(500, 200));
		frame.setVisible(true);

	}

	private void setListeners() {

		frame.addComponentListener(this);
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Willkommen willkommen = new Willkommen();
				willkommen.setStatus("Verbinde zur Datenbank...");
				Benutzer benutzer = null;
				try {
				willkommen.setStatus("Benutzerdaten werden überprüft...");
					settings = new de.gw.auto.service.BenutzerService().Login(
							tfBenutzer.getText(),
							new String(tfPasswort.getPassword()));
					benutzer = settings.getBenutzer();
				} catch (Exception e) {
					AllException.messageBox("Falscher Benutzer", e.getMessage());
					return;
				}
				willkommen.setStatus("Prüfen ob ein Auto schon vorhanden...");
				if (settings.getBenutzer() != null) {
					if (settings.getAutos().isEmpty()) {
						new AddAuto(settings);

					} else {
						new ShowGui(settings);
					}
					willkommen.setStatus(Constans.CLOSE_WINDOW);
					Funktionen.cancel(frame);
					
				} else {
					AllException
							.messageBox("Falscher Benutzer",
									"Falsche eingabe bitte versuchen sie es noch einmal");
					return;
				}
			}
		});

		btnRegistrieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Registrieren();

			}
		});

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent ecomp) {
		if (ecomp.getSource() == frame) {
			System.out.println(frame.getWidth());
			lPasswort.setFont(Berechnung.updateFont(lPasswort, lPasswort.getText(), Font.BOLD));
			lBenutzer.setFont(Berechnung.updateFont(lBenutzer, lBenutzer.getText(), Font.BOLD));
			btnLogIn.setFont(Berechnung.updateFont(btnLogIn, btnLogIn.getText(), Font.ITALIC));
			btnExit.setFont(Berechnung.updateFont(btnExit, btnExit.getText(), Font.ITALIC));
			btnRegistrieren.setFont(Berechnung.updateFont(btnRegistrieren, btnRegistrieren.getText(), Font.ITALIC));
			tfBenutzer.setFont(Berechnung.updateFont(tfBenutzer, tfBenutzer.getText(), Font.PLAIN));
			tfPasswort.setFont(Berechnung.updateFont(tfPasswort, String.valueOf(tfPasswort.getPassword()), Font.PLAIN));
		}

		/*
		 * lBenutzer.setFont(Berechnung.calculateFontSize(lBenutzer.getGraphics()
		 * , "Dialog", Font.BOLD, lBenutzer.getText(), frame.getWidth(),
		 * frame.getHeight()));}
		 */
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
