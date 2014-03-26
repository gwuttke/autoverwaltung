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

public class GuiShowTanken {
	TankenDao tDao = new TankenDao();

	String[] columnNames = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	Object[][] obj;

	public GuiShowTanken(Settings setting) {
		obj = loadData(setting);
		//Tabelle t = new Tabelle(columnNames, obj);
		JTable jT = new Tabelle(columnNames, new Object[1][1]).getJTable();
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());		
		
		JPanel jpTable = new JPanel(new BorderLayout());
		jpTable.add(jT, BorderLayout.CENTER);
		
		con.add(jpTable, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		

	}

	private Object[][] loadData(Settings setting) {
		int index = 0;
		Object[][] o;

		if (tDao.getTankenList() == null) {
			try {
				tDao.setTankenList(setting);
			} catch (SQLException e) {
				AllException.messageBox(Texte.Error.Titel.CONNECTION,
						Texte.Error.Text.CONNECTION_TEXT);
				return null;
			}
		}

		o = new Object[tDao.getTankenList().size()][8];
		for (Tanken t : tDao.getTankenList()) {
			o[0][index] = t.getDatum();
			o[1][index] = t.getBenzinArt();
			o[2][index] = t.getKmStand();
			o[3][index] = t.getOrt().getOrt();
			o[4][index] = t.getLand().getName();
			o[5][index] = t.getTank().getBeschreibung();
			o[6][index] = t.getLiter();
			o[7][index] = t.getPreisProLiter();
			o[8][index] = t.getKosten();
		}
		return o;

	}

}
