package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;







import de.gw.auto.Constans;
import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.model.Tabelle;

public class GuiShowTanken {

	private String[] columnNamesTanken = new String[] { "Datum", "Benzinart", "Km Stand",
			"Ort", "Land", "inhalt getankt", "Liter", "Preis p. Liter",
			"Kosten" };
	private String[] columnNamesTankenInfos = new String[]{"Bezeichnung", "Dieses Jahr", "letztes Jahr", "Gesammt"};
	private Object[][] tankungenData = new Object[0][0];
	private Object[][] tankenInfosData = new Object[0][0];
	
	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton("Sonstige Ausgaben hinzufügen");

	public GuiShowTanken(Settings setting, TankenDao tankenDao) {
		List<Object[][]> data = loadData(setting, tankenDao);
		if (data.size() > 0){
			tankungenData = data.get(0);
			tankenInfosData = data.get(1);
		}
		
		JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>(setting
				.getAutosArray());
		Berechnung berechnung = new Berechnung();

		
		
		JLabel lKGes = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_GES + " ");
		JLabel lKLetJahr = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_LET_JAHR
				+ " ");
		JLabel lKDiesJahr = new JLabel(Texte.Form.Label.TANKEN_KOSTEN_AKT_JAHR
				+ " ");
		

		comboBoxAutos.getModel().setSelectedItem(setting.getAktuellAuto());
		setActions(setting);

		JTable jTableTankenInfo = new Tabelle(columnNamesTankenInfos, tankenInfosData).getJTable();
		JTable jTableTanken = new Tabelle(columnNamesTanken, tankungenData).getJTable();
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(jTableTanken);

		JPanel jpTable = new JPanel(new GridLayout(2, 1));
		jpTable.add(sp);
		jpTable.add(jTableTankenInfo);

		JPanel jpEingaben = new JPanel(new BorderLayout());
		JPanel jpEinrueckung = new JPanel(new GridLayout(1, 3));
		JPanel jpAusgabe = new JPanel(new BorderLayout());
		JPanel jpBtn = new JPanel(new GridLayout(1,2));
		
		jpEingaben.add(comboBoxAutos, BorderLayout.EAST);

		jpEinrueckung.add(new Label());
		jpEinrueckung.add(lKDiesJahr);
		jpEinrueckung.add(new Label());
		
		jpBtn.add(btnTanken);
		jpBtn.add(btnSonstigeAusgaben);

		jpAusgabe.add(lKLetJahr, BorderLayout.WEST);
		jpAusgabe.add(jpEinrueckung, BorderLayout.CENTER);
		jpAusgabe.add(lKGes, BorderLayout.EAST);
		jpAusgabe.add(jpBtn, BorderLayout.SOUTH);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(jpTable, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}

	private List<Object[][]> loadData(Settings setting, TankenDao tankenDao) {
		List<Object[][]> lObjects = new ArrayList<Object[][]>();
		Object[][] oTankungen = new Object[0][0];
		Object[][] oTankenInfos = new Object[0][0];

		if (tankenDao.getTankenList() == null) {
			tankenDao.setTankenList(setting);
		}

		Berechnung berechnung = new Berechnung();
		if (tankenDao.getTankenList() == null) {
			return new ArrayList<Object[][]>();
		} else {
			oTankungen = loadTankungen(tankenDao, berechnung);
			oTankenInfos = loadTankungenInfos(tankenDao, setting);
			lObjects.add(oTankungen);
			lObjects.add(oTankenInfos);
			
		}
		return lObjects;

	}

	private Object[][] loadTankungen(TankenDao tankenDao, Berechnung berechnung) {
		Object[][] o;
		int index = 0;
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
			o[index][8] = berechnung.getRound(t.getKosten(), 2);
			index++;
		}
		return o;
	}
	
	private Object[][] loadTankungenInfos(TankenDao tankenDao, Settings setting) {
		Object[][] o;
		int index = 0;
		
		List<Info> tankInfos = new TankenInfo(tankenDao, setting).getTankenInfos();
		o = new Object[tankInfos.size()][columnNamesTankenInfos.length];
		
		for (Info info :  tankInfos){
			o[index][0] = info.getName();
			o[index][1] = info.getDiesesJahr();
			o[index][2] = info.getVorjahr();
			o[index][3] = info.getGesammt();
			index++;
		}
		return o;
	}
	
	
	
	
	
	private void setActions(final Settings setting){
		btnTanken.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddTanken(setting);
				
			}
		});
		btnSonstigeAusgaben.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddSonstigeAusgaben(setting);
				
			}
		});
	}
}
