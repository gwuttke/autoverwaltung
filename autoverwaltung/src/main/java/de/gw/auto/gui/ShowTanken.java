package de.gw.auto.gui;

import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import de.gw.auto.dao.Berechnung;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.gui.model.FormatRenderer;
import de.gw.auto.gui.model.NumberRenderer;
import de.gw.auto.gui.model.Tabelle;
import de.gw.auto.service.TankenService;

public class ShowTanken {

	private JPanel jpTankenTable = new JPanel(new GridLayout(1, 1));
	private JTable jTableTanken = null;
	private TankenService tankenService = null;

Settings setting = null;
private final String[] columnNames = new String[] { "Datum", "Benzinart",
		"Km Stand", "gefahrene Km", "Verbrauch / 100Km", "Ort", "Land",
		"inhalt getankt", "Liter", "Preis p. Liter", "Kosten" };


	public ShowTanken(Settings setting) {
		
		this.setting = setting;

		tankenService =	new TankenService(setting);
		/*		
		jTableTanken =new JTable(new TankenTabelle(tankenService.loadTankungen(0)));

		if (jTableTanken.getColumnCount() > 1) {
			setTableStyle();
		}
		*/
		
		//Oberhalb löschen
		Berechnung berechnung = new Berechnung();

		Object[][] tankungenData;

		tankungenData = tankenService.loadTankungen();

		jTableTanken = new Tabelle(columnNames, tankungenData)
			.getJTable();

		if (jTableTanken.getColumnCount() > 1) {
			setTableStyle();
		}

		JScrollPane spTanken = new JScrollPane(jTableTanken);

		jpTankenTable.add(spTanken);

	}

	public JPanel getJpTankenTable() {
		return jpTankenTable;
	}

	private void setTableStyle() {

		TableColumnModel m = jTableTanken.getColumnModel();
		m.getColumn(0).setCellRenderer(FormatRenderer.getDateRenderer());
		m.getColumn(2).setCellRenderer(NumberRenderer.getKilometerRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getKilometerRenderer());
		m.getColumn(4).setCellRenderer(NumberRenderer.getLiterRenderer());
		m.getColumn(8).setCellRenderer(NumberRenderer.getLiterRenderer());
		m.getColumn(9).setCellRenderer(NumberRenderer.getCurrencyRenderer(3));
		m.getColumn(10).setCellRenderer(NumberRenderer.getCurrencyRenderer(2));
		jTableTanken.setColumnModel(m);

		TableModel tm = jTableTanken.getModel();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tm);
		jTableTanken.setRowSorter(sorter);
		sorter.setComparator(2, Collections.reverseOrder(new Tanken()));

	}
	
	public Tanken getRowObject(int rowIndex){
		Object[] o = new Object[jTableTanken.getColumnCount()];
		for (int column = 0; column < jTableTanken.getColumnCount(); column++){
			o[column] = jTableTanken.getValueAt(rowIndex, column);
		}
		int kmStand = (Integer)o[2];
		Land land = (Land) o[6];
		Ort ort = (Ort) o[5];
		Tank tank = (Tank)o[7];
		BigDecimal kosten = (BigDecimal)o[10];
		Auto auto = setting.getAktuellAuto();
		Date datum = (Date)o[0];
		BigDecimal liter = (BigDecimal)o[8];
		BigDecimal preisProLiter = (BigDecimal) o[9];
		Benzinart benzinart = (Benzinart) o[1];		
		Tanken t = new Tanken(kmStand, land, ort, tank, kosten, auto, datum, liter, preisProLiter, benzinart);
		
		return t;
	}

	public JTable getjTableTanken() {
		return jTableTanken;
	}

	public void setjTableTanken(JTable jTableTanken) {
		this.jTableTanken = jTableTanken;
	}

}
