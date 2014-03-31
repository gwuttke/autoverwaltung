package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ScrollPane;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Exception.AllException;
import Model.Tabelle;
import dao.TankenDao;
import domain.Settings;
import domain.Tanken;
import domain.Texte;

public class GuiShowTanken {

	String[] columnNames = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	Object[][] obj;

	public GuiShowTanken(Settings setting, TankenDao tankenDao) {
		obj = loadData(setting, tankenDao);
		// Tabelle t = new Tabelle(columnNames, obj);
		JTable jT = new Tabelle(columnNames, obj).getJTable();
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(jT);

		JPanel jpTable = new JPanel(new BorderLayout());
		jpTable.add(sp, BorderLayout.CENTER);

		con.add(jpTable, BorderLayout.CENTER);

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

		}
		return o;

	}

}
