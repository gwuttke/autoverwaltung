package de.gw.auto.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.gui.model.FormatRenderer;
import de.gw.auto.gui.model.NumberRenderer;
import de.gw.auto.gui.model.Tabelle;

public class ShowSonstigeAusgaben {

	private final String[] columnNamesTanken = new String[] { "Datum",
			"Bezeichnung", "Km Stand", "Kosten" };

	private JPanel jpSonstigeAusgabenTable = new JPanel(new GridLayout(1, 1));
	private JTable jTableSonstigeAusgaben = null;

	public ShowSonstigeAusgaben(SonstigeAusgabenDao sADao) {
		Object[][] sonstigeAusgabenData = new Object[0][0];

		if (sADao.getSonstigeAusgabenList() == null
				|| sADao.getSonstigeAusgabenList().size() == 0) {
			sonstigeAusgabenData = new Object[0][0];
		} else {
			sonstigeAusgabenData = loadSonstigeAusgaben(sADao);
		}

		jTableSonstigeAusgaben = new Tabelle(columnNamesTanken, sonstigeAusgabenData)
				.getJTable();
		
		if (jTableSonstigeAusgaben.getColumnCount() > 1){
			setTableStyle();
		}
		

		JScrollPane spSonstigeAusgaben = new JScrollPane(jTableSonstigeAusgaben);

		jpSonstigeAusgabenTable.add(spSonstigeAusgaben);
	}
	
	private void setTableStyle(){
		TableColumnModel m = jTableSonstigeAusgaben.getColumnModel();
		m.getColumn(0).setCellRenderer(FormatRenderer.getDateRenderer());
		m.getColumn(2).setCellRenderer(NumberRenderer.getKilometerRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer(2));
		jTableSonstigeAusgaben.setColumnModel(m);
	}

	public JPanel getJpSonstigeAusgabenTable() {
		return jpSonstigeAusgabenTable;
	}

	private Object[][] loadSonstigeAusgaben(SonstigeAusgabenDao sADao) {
		Object[][] o;
		int index = 0;
		o = new Object[sADao.getSonstigeAusgabenList().size()][columnNamesTanken.length];

		for (SonstigeAusgaben sa : sADao.getSonstigeAusgabenList()) {
			o[index][0] = sa.getDatum();
			o[index][1] = sa.getKommentar();
			o[index][2] = sa.getKmStand();
			o[index][3] = sa.getKosten();
			index++;
		}
		return o;
	}

}
