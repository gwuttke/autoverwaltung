package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.gw.auto.Constans;
import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.model.Tabelle;

public class GuiShowTanken {

	private String[] columnNames = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	private Object[][] obj = new Object[1][1];
	
	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton("Sonstige Ausgaben hinzufügen");

	public GuiShowTanken(Settings setting, TankenDao tankenDao) {
		obj = loadData(setting, tankenDao);
		JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>(setting
				.getAutosArray());
		Berechnung berechnung = new Berechnung();
		Info tankInfoKosten = berechnung.getAusgabenBerechnungen().get(
				Constans.KOSTEN);
		JLabel lKGes = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_GES + " ");
		JLabel lKLetJahr = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_LET_JAHR
				+ " ");
		JLabel lKDiesJahr = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_AKT_JAHR
				+ " ");
		

		comboBoxAutos.getModel().setSelectedItem(setting.getAktuellAuto());
		setActions(setting);

		if (obj.length == 0) {
			lKGes.setText(lKGes.getText() + 0);
			lKLetJahr.setText(lKLetJahr.getText() + 0);
			lKDiesJahr.setText(lKDiesJahr.getText() + 0);
		} else {

			lKGes.setText(lKGes.getText() + tankInfoKosten.getGesammt());
			lKLetJahr
					.setText(lKLetJahr.getText() + tankInfoKosten.getVorjahr());
			lKDiesJahr.setText(lKDiesJahr.getText()
					+ tankInfoKosten.getDiesesJahr());
		}
		JTable jTableTanken = new Tabelle(columnNames, obj).getJTable();
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(jTableTanken);

		JPanel jpTable = new JPanel(new BorderLayout());
		jpTable.add(sp, BorderLayout.CENTER);

		JPanel jpEingaben = new JPanel(new BorderLayout());
		JPanel jpEinrueckung = new JPanel(new GridLayout(1, 3));
		JPanel jpAusgabe = new JPanel(new BorderLayout());
		JPanel jpBtn = new JPanel(new GridLayout(1,2));
		
		jpEingaben.add(comboBoxAutos, BorderLayout.EAST);

		jpEinrueckung.add(new Label());
		jpEinrueckung.add(lKDiesJahr);
		jpEinrueckung.add(new Label());
		
		jpBtn.add(btnTanken);
		jpBtn.add(btnSonstigeAusgaben);

		jpAusgabe.add(lKLetJahr, BorderLayout.WEST);
		jpAusgabe.add(jpEinrueckung, BorderLayout.CENTER);
		jpAusgabe.add(lKGes, BorderLayout.EAST);
		jpAusgabe.add(jpBtn, BorderLayout.SOUTH);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(jpTable, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}

	private Object[][] loadData(Settings setting, TankenDao tankenDao) {
		int index = 0;
		Object[][] o = new Object[0][0];

		if (tankenDao.getTankenList() == null) {
			tankenDao.setTankenList(setting);
		}

		Berechnung berechnung = new Berechnung();
		if (tankenDao.getTankenList() == null) {
			return o;
		} else {
			o = new Object[tankenDao.getTankenList().size()][9];
			for (Tanken t : tankenDao.getTankenList()) {

				o[index][0] = t.getDatum();
				o[index][1] = t.getBenzinArt();
				o[index][2] = t.getKmStand();
				o[index][3] = t.getOrt().getOrt();
				o[index][4] = t.getLand().getName();
				o[index][5] = t.getTank().getBeschreibung();
				o[index][6] = t.getLiter();
				o[index][7] = t.getPreisProLiter();
				o[index][8] = berechnung.getRound(t.getKosten(), 2);
				index++;
			}
		}
		return o;

	}
	
	private void setActions(final Settings setting){
		btnTanken.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddTanken(setting);
				
			}
		});
		btnSonstigeAusgaben.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddSonstigeAusgaben(setting);
				
			}
		});
	}

}
