package de.gw.auto.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.Constans;
import de.gw.auto.dao.Settings;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.SonstigeAusgabenInfo;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Info;
import de.gw.auto.gui.model.NumberRenderer;
import de.gw.auto.gui.model.Tabelle;
import de.gw.auto.service.implementation.InfoService;
@Controller
public class ShowInfos {
	
	@Autowired
	private InfoService infoService;

	private String[] columnNamesTankenInfos = new String[] { "Bezeichnung",
			"Dieses Jahr", "letztes Jahr", "Gesammt" };
	private Object[][] infosData = new Object[0][0];

	private JPanel jpInfosTable = new JPanel(new GridLayout(1, 1));
	private JTable jTableInfos = null;

	protected ShowInfos(){}


	public void init(Settings setting) {
		
		infoService.init(setting);
		
		infosData = infoService.loadInfos();

		jTableInfos = new Tabelle(columnNamesTankenInfos, infosData)
				.getJTable();
		

		for (int column = 1; column < jTableInfos.getColumnCount(); column++) {
			prepareRenderer(NumberRenderer.getCurrencyRenderer(3), 0, column);
			prepareRenderer(NumberRenderer.getCurrencyRenderer(3), 1, column);
			prepareRenderer(NumberRenderer.getLiterRenderer(), 2, column);
			prepareRenderer(NumberRenderer.getKilometerRenderer(), 3, column);
			prepareRenderer(NumberRenderer.getLiterRenderer(), 4, column);
			prepareRenderer(NumberRenderer.getCurrencyRenderer(2), 5, column);
			prepareRenderer(NumberRenderer.getCurrencyRenderer(2), 6, column);
			prepareRenderer(NumberRenderer.getCurrencyRenderer(2), 7, column);
		}

		JScrollPane spInfos = new JScrollPane(jTableInfos);
		spInfos.setPreferredSize(new Dimension(100, 135));

		jpInfosTable.add(spInfos);
	}

	public JPanel getJpInfosTable() {
		return jpInfosTable;
	}

	private JComponent prepareRenderer(TableCellRenderer renderer, int row,
			int column) {
		Component c = jTableInfos.prepareRenderer(renderer, row, column);
		JLabel l = (JLabel) c;
		jTableInfos.setValueAt(l.getText(), row, column);
		JComponent jc = (JComponent) c;
		return jc;
	}



	public JTable getjTableInfos() {
		return jTableInfos;
	}



	public void setjTableInfos(JTable jTableInfos) {
		this.jTableInfos = jTableInfos;
	}
}
