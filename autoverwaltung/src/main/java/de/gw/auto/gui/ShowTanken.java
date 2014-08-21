package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.gui.model.Tabelle;

public class ShowTanken {
	
	private final String[] columnNamesTanken = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	
	private JPanel jpTankenTable = new JPanel(new GridLayout(1, 1));
	
	public ShowTanken(TankenDao tankenDao) {
		
		Object[][] tankungenData = new Object[0][0];
		Berechnung berechnung = new Berechnung();
		
		if (tankenDao.getTankenList() == null) {
			tankungenData = new Object[0][0]; 
		} else {
			tankungenData = loadTankungen(tankenDao, berechnung);
		}
		
		JTable jTableTanken = new Tabelle(columnNamesTanken, tankungenData).getJTable();
		
		JScrollPane spTanken = new JScrollPane(jTableTanken);
			
		jpTankenTable.add(spTanken);		

	}

	public JPanel getJpTankenTable() {
		return jpTankenTable;
	}
	
	private Object[][] loadTankungen(TankenDao tankenDao, Berechnung berechnung) {
		Object[][] o;
		int index = 0;
		o = new Object[tankenDao.getTankenList().size()][columnNamesTanken.length];
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
