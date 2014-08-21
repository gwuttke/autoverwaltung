package de.gw.auto.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.gui.model.Tabelle;

public class ShowSonstigeAusgaben {

	private final String[] columnNamesTanken = new String[] { "Datum",
			"Bezeichnung", "Km Stand", "Kosten" };

	private JPanel jpSonstigeAusgabenTable = new JPanel(new GridLayout(1, 1));

	public ShowSonstigeAusgaben(SonstigeAusgabenDao sADao) {
		Object[][] sonstigeAusgabenData = new Object[0][0];

		if (sADao.getSonstigeAusgabenList() == null
				|| sADao.getSonstigeAusgabenList().size() == 0) {
			sonstigeAusgabenData = new Object[0][0];
		} else {
			sonstigeAusgabenData = loadSonstigeAusgaben(sADao);
		}

		JTable jTableSonstigeAusgaben = new Tabelle(columnNamesTanken, sonstigeAusgabenData)
				.getJTable();

		JScrollPane spSonstigeAusgaben = new JScrollPane(jTableSonstigeAusgaben);

		jpSonstigeAusgabenTable.add(spSonstigeAusgaben);
	}

	public JPanel getJpSonstigeAusgabenTable() {
		return jpSonstigeAusgabenTable;
	}

	private Object[][] loadSonstigeAusgaben(SonstigeAusgabenDao sADao) {
		Berechnung berechnung = new Berechnung();
		Object[][] o;
		int index = 0;
		o = new Object[sADao.getSonstigeAusgabenList().size()][columnNamesTanken.length];

		for (SonstigeAusgaben sa : sADao.getSonstigeAusgabenList()) {
			o[index][0] = sa.getDatum();
			o[index][1] = sa.getKommentar();
			o[index][2] = sa.getKmStand();
			o[index][3] = berechnung.getRound(sa.getKosten(), 2);
			index++;
		}
		return o;
	}

}
