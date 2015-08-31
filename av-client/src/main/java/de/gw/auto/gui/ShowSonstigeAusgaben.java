package de.gw.auto.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.dao.Settings;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.gui.model.FormatRenderer;
import de.gw.auto.gui.model.NumberRenderer;
import de.gw.auto.gui.model.Tabelle;
import de.gw.auto.service.implementation.SonstigeAusgabenService;

@Controller
public class ShowSonstigeAusgaben {

	@Autowired
	private SonstigeAusgabenService sonstigeAusgabenService;

	private final String[] columnNamesTanken = new String[] { "Datum",
			"Bezeichnung", "Km Stand", "Kosten" };

	private JPanel jpSonstigeAusgabenTable = new JPanel(new GridLayout(1, 1));
	private JTable jTableSonstigeAusgaben = null;

	protected ShowSonstigeAusgaben() {
	}

	public void init(Settings setting) {
		sonstigeAusgabenService.init(setting);
		load();
	}

	private void load() {
		Object[][] sonstigeAusgabenData;
		sonstigeAusgabenData = sonstigeAusgabenService.loadSonstigeAusgaben();

		jTableSonstigeAusgaben = new Tabelle(columnNamesTanken,
				sonstigeAusgabenData).getJTable();

		if (jTableSonstigeAusgaben.getColumnCount() > 1) {
			setTableStyle();
		}

		JScrollPane spSonstigeAusgaben = new JScrollPane(jTableSonstigeAusgaben);

		jpSonstigeAusgabenTable.add(spSonstigeAusgaben);
	}

	private void setTableStyle() {
		TableColumnModel m = jTableSonstigeAusgaben.getColumnModel();
		m.getColumn(0).setCellRenderer(FormatRenderer.getDateRenderer());
		m.getColumn(2).setCellRenderer(NumberRenderer.getKilometerRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer(2));
		jTableSonstigeAusgaben.setColumnModel(m);
	}

	public JPanel getJpSonstigeAusgabenTable() {
		return jpSonstigeAusgabenTable;
	}

	public JTable getjTableSonstigeAusgaben() {
		return jTableSonstigeAusgaben;
	}

	public void setjTableSonstigeAusgaben(JTable jTableSonstigeAusgaben) {
		this.jTableSonstigeAusgaben = jTableSonstigeAusgaben;
	}

}
