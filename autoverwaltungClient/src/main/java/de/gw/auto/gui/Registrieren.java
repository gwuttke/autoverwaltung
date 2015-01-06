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

import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.service.BenutzerService;

public class Registrieren {

	public Registrieren() {
		final JFrame frame = new JFrame();

		// Lable
		JLabel lName = new JLabel("Name:");
		JLabel lVorname = new JLabel("Vorname:");
		JLabel lBenutzername = new JLabel("Benutzername:");
		JLabel lPasswort = new JLabel(Texte.Form.Label.PASSWORT);
		JLabel lWiederholePasswort = new JLabel("wiederhole "
				+ Texte.Form.Label.PASSWORT);
		JLabel leMail = new JLabel("eMail Adresse:");

		// Eingaben
		final JTextField tfName = new JTextField();
		final JTextField tfVornahme = new JTextField();
		final JTextField tfBenutzername = new JTextField();
		final JPasswordField pfPasswort = new JPasswordField();
		final JPasswordField pfWiederholungPasswort = new JPasswordField();
		final JTextField tfEMail = new JTextField();

		// Buttons
		JButton btnHinzufuegen = new JButton("Hinzufügen");
		JButton btnCancel = new JButton("Abbruch");

		Container con = new Container();
		con = frame.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel jpEingaben = new JPanel(new GridLayout(6, 2));

		jpEingaben.add(lName);
		jpEingaben.add(tfName);
		jpEingaben.add(lVorname);
		jpEingaben.add(tfVornahme);
		jpEingaben.add(lBenutzername);
		jpEingaben.add(tfBenutzername);
		jpEingaben.add(lPasswort);
		jpEingaben.add(pfPasswort);
		jpEingaben.add(lWiederholePasswort);
		jpEingaben.add(pfWiederholungPasswort);
		jpEingaben.add(leMail);
		jpEingaben.add(tfEMail);

		JPanel jpBtn = new JPanel();

		jpBtn.add(btnHinzufuegen);
		jpBtn.add(btnCancel);

		con.add(jpEingaben, BorderLayout.CENTER);
		con.add(jpBtn, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);

		btnHinzufuegen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfName.getText() == "") {
					AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
							"Bitte geben sie Ihren Nachnamen an");
					return;
				}

				if (tfVornahme.getText() == "") {
					AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
							"Bitte geben sie Ihren Vornamen an");
					return;
				}

				if (tfBenutzername.getText() == "") {
					AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
							"Bitte geben sie einen Benutzernamen an");
					return;
				}

				if (pfPasswort.getPassword().length < 1) {
					AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
							"Bitte geben sie ein Passwort an");
					return;
				}

				if (pfPasswort.getPassword().equals(pfWiederholungPasswort
						.getPassword())) {
					AllException
							.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
									"Das zweite Passwort ist falsch, bitte versuchen Sie es erneut");
					return;
				}

				if (tfEMail.getText() == "") {
					AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
							"Bitte geben sie Ihre e-Mail Adresse an");
					return;
				} else {
					String patter = "[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}";
					if (tfEMail.getText().matches(patter) == false) {
						AllException
								.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
										"Bitte geben sie Ihre richtige e-Mail Adresse an");
						return;
					}
				}

				String name = tfName.getText();
				String vorname = tfVornahme.getText();
				String benutzername = tfBenutzername.getText();
				String passwort = String.valueOf(pfPasswort.getPassword());
				String eMail = tfEMail.getText();

				new BenutzerService().registry(name, vorname, benutzername,
						passwort, eMail);

				frame.dispose();

			}
		});

	}
}
