package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import Exception.AllException;
import Model.Tabelle;
import dao.TankenDao;
import domain.Settings;
import domain.Tanken;
import domain.Texte;

public class showTanken {
	TankenDao tDao = new TankenDao();

	String[] columnNames = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	Object[][] obj;

	public showTanken(Settings setting) {
		obj = loadData(setting);
		Tabelle t = new Tabelle(columnNames, obj);
		JTable jT = new JTable(t);
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());		
		
		JPanel jpTable = new JPanel();
		jpTable.add(jT);
		
		con.add(jpTable, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		

	}

	private Object[][] loadData(Settings setting) {
		int index = 0;
		Object[][] o = null;

		if (tDao.getTankenList() == null) {
			try {
				tDao.setTankenList(setting);
			} catch (SQLException e) {
				AllException.messageBox(Texte.Error.Titel.CONNECTION,
						Texte.Error.Text.CONNECTION_TEXT);
				return null;
			}
		}

		for (Tanken t : tDao.getTankenList()) {
			o[index][0] = t.getDatum();
			o[index][1] = t.getBenzinArt();
			o[index][2] = t.getKmStand();
			o[index][3] = t.getOrt().getOrt();
			o[index][4] = t.getLand().getName();
			o[index][5] = t.getTank().getBeschreibung();
			o[index][6] = t.getLiter();
			o[index][7] = t.getPreisProLiter();
			o[index][8] = t.getKosten();
		}
		return o;

	}

}
