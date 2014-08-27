package de.gw.auto.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Tanken;
import de.gw.auto.gui.model.FormatRenderer;
import de.gw.auto.gui.model.NumberRenderer;
import de.gw.auto.gui.model.Tabelle;

public class ShowTanken {
	
	private final String[] columnNamesTanken = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	
	private JPanel jpTankenTable = new JPanel(new GridLayout(1, 1));
	private JTable jTableTanken = null;
	
	public ShowTanken(TankenDao tankenDao) {
		
		Object[][] tankungenData = new Object[0][0];
		Berechnung berechnung = new Berechnung();
		
		if (tankenDao.getTankenList() == null) {
			tankungenData = new Object[0][0]; 
		} else {
			tankungenData = loadTankungen(tankenDao, berechnung);
		}
		
		jTableTanken = new Tabelle(columnNamesTanken, tankungenData).getJTable();
		
		if (jTableTanken.getColumnCount() > 1){
		setTableStyle();
		}
		
		JScrollPane spTanken = new JScrollPane(jTableTanken);
		
		jpTankenTable.add(spTanken);		

	}

	public JPanel getJpTankenTable() {
		return jpTankenTable;
	}
	
	private void setTableStyle(){
		
		TableColumnModel m = jTableTanken.getColumnModel();
		m.getColumn(0).setCellRenderer(FormatRenderer.getDateRenderer());
		m.getColumn(2).setCellRenderer(NumberRenderer.getKilometerRenderer());
		m.getColumn(6).setCellRenderer(NumberRenderer.getLiterRenderer());
		m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer(3));
		m.getColumn(8).setCellRenderer(NumberRenderer.getCurrencyRenderer(2));
		jTableTanken.setColumnModel(m);
		
		TableModel tm = jTableTanken.getModel();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tm);
		jTableTanken.setRowSorter(sorter);
		sorter.setComparator(2,new Tanken());
		
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
			o[index][8] = t.getKosten();
			index++;
		}
		return o;
	}
	
}
