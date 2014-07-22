package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.gw.auto.Constans;
import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.model.Tabelle;

public class GuiShowTanken {

	String[] columnNames = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	Object[][] obj;

	public GuiShowTanken(Settings setting, TankenDao tankenDao) {
		obj = loadData(setting, tankenDao);
		Berechnung berechnung = new Berechnung();
		Info tankInfoKosten = berechnung.getAusgabenBerechnungen().get(
				Constans.KOSTEN);

		JLabel lKGes = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_GES + " "
				+ tankInfoKosten.getGesammt());
		
		JLabel lKLetJahr = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_LET_JAHR
				+ " " + tankInfoKosten.getVorjahr());
		JLabel lKDiesJahr = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_AKT_JAHR
				+ " " + tankInfoKosten.getDiesesJahr());

		JTable jT = new Tabelle(columnNames, obj).getJTable();
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(jT);

		JPanel jpTable = new JPanel(new BorderLayout());
		jpTable.add(sp, BorderLayout.CENTER);

		JPanel jpAusgabe = new JPanel(new BorderLayout());
		JPanel jpEinrückung = new JPanel(new GridLayout(1, 3));
		jpEinrückung.add(new Label());
		jpEinrückung.add(lKDiesJahr);
		jpEinrückung.add(new Label());

		jpAusgabe.add(lKLetJahr, BorderLayout.WEST);
		jpAusgabe.add(jpEinrückung, BorderLayout.CENTER);
		jpAusgabe.add(lKGes, BorderLayout.EAST);

		con.add(jpTable, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}

	private Object[][] loadData(Settings setting, TankenDao tankenDao) {
		int index = 0;
		Object[][] o;

		if (tankenDao.getTankenList() == null) {
			try {
				tankenDao.setTankenList(setting);
			} catch (SQLException e) {
				AllException.messageBox(Texte.Error.Titel.CONNECTION,
						Texte.Error.Text.CONNECTION_TEXT);
				return null;
			}
		}

		o = new Object[tankenDao.getTankenList().size()][9];
		Berechnung berechnung = new Berechnung();
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
		return o;

	}

}
