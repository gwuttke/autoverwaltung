package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.michaelbaranov.microba.calendar.DatePicker;

import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.domain.Texte;
import de.gw.auto.gui.Button.Funktionen;
import de.gw.auto.model.Spinner;

public class AddSonstigeAusgaben extends Funktionen {

	JFrame frame = new JFrame("Sonstige Ausgaben hinzufügen");

	public AddSonstigeAusgaben(final Settings setting) {
		// Lable
		JLabel lDatum = new JLabel(Texte.Form.Label.DATUM);
		JLabel lBezeichnung = new JLabel("Bezeichnung");
		JLabel lKmStand = new JLabel("Km Stand");
		JLabel lKosten = new JLabel("Kosten");

		// Eingaben
		final DatePicker dp = new DatePicker();
		final JTextField tfBezeichnung = new JTextField();
		final JSpinner spKmStand = new Spinner(50000, setting.getAuto()
				.getKmAktuell(), 999999, 1000).getSpinner();
		final JSpinner spKosten = new Spinner(0d, 0d, 99999.99, 100d)
				.getSpinner();

		// Buttons
		JButton btnHinzufuegen = new JButton("Hinzufügen");
		JButton btnCancel = new JButton("Abbruch");

		Container con = new Container();
		con = frame.getContentPane();
		con.setLayout(new BorderLayout());
		JPanel jpEingaben = new JPanel(new GridLayout(4, 2));

		jpEingaben.add(lDatum);
		jpEingaben.add(dp);
		jpEingaben.add(lBezeichnung);
		jpEingaben.add(tfBezeichnung);
		jpEingaben.add(lKmStand);
		jpEingaben.add(spKmStand);
		jpEingaben.add(lKosten);
		jpEingaben.add(spKosten);

		JPanel jpBtn = new JPanel();

		jpBtn.add(btnHinzufuegen);
		jpBtn.add(btnCancel);

		con.add(jpEingaben, BorderLayout.CENTER);
		con.add(jpBtn, BorderLayout.SOUTH);

		btnHinzufuegen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SonstigeAusgabenDao saD = new SonstigeAusgabenDao(setting);
				SonstigeAusgaben sa = new SonstigeAusgaben();
				sa.setAutoId(setting.getAuto().getId());
				sa.setDatum(new Date(dp.getDate().getTime()));
				sa.setKmStand((int) spKmStand.getValue());
				sa.setKommentar(tfBezeichnung.getText());
				sa.setKosten(new BigDecimal((double) spKosten.getValue()));

				saD.intoDatabase(sa);
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancel(frame);

			}
		});

		frame.pack();
		frame.setVisible(true);
	}

}
