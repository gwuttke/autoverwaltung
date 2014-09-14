package de.gw.auto.gui;

import java.awt.ScrollPane;
import java.lang.reflect.Field;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;

public class ShowAuto {

	private Settings setting;
	private String[] columns = { "Beschreibung", "Wert" };
	private String[] dataBeschreibung = { "Kennzeichen", "Km Aktuell",
			"Km beim Kauf", "Datum Erstzulassung", "Kaufdatum", "Benzinarten" };
	private Object[][] autoDetails = new Object[6][2];
	private DefaultTableModel dtm = null;
	private JTable table = null;

	public ShowAuto(Settings setting) {
		super();
		this.setting = setting;
		insertData();
		dtm = new DefaultTableModel(autoDetails, columns);
		table = new JTable(dtm);

	}

	private void insertData() {
		Auto a = this.setting.getAktuellAuto();
		int i = 0;
		int index = 0;
		for (String s : dataBeschreibung) {
			autoDetails[index][i] = s + ":";
			index++;
		}
		i++;
		autoDetails[0][i] = a.getKfz();
		autoDetails[1][i] = a.getKmAktuell();
		autoDetails[2][i] = a.getKmKauf();
		autoDetails[3][i] = a.getErstZulassung();
		autoDetails[4][i] = a.getKauf();
		autoDetails[5][i] = a.getBenzinartenString();
	}
	
	public JScrollPane getTable() {
		return new JScrollPane(table);
	}
}
