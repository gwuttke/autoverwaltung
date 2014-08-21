package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import de.gw.auto.Constans;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.SonstigeAusgabenInfo;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.gui.model.Tabelle;

public class GuiShowTanken {

	private String[] columnNamesTankenInfos = new String[] { "Bezeichnung",
			"Dieses Jahr", "letztes Jahr", "Gesammt" };
	private Object[][] tankenInfosData = new Object[0][0];

	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton(
			"Sonstige Ausgaben hinzufügen");

	public GuiShowTanken(Settings setting) {
		TankenDao tankenDao = new TankenDao(setting);
		SonstigeAusgabenDao sADao = new SonstigeAusgabenDao(setting);
		tankenInfosData = loadTankungenInfos(tankenDao, setting, sADao);

		JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>(
				setting.getAutosArray());

		comboBoxAutos.getModel().setSelectedItem(setting.getAktuellAuto());
		setActions(setting);

		JTable jTableTankenInfo = new Tabelle(columnNamesTankenInfos,
				tankenInfosData).getJTable();
		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JScrollPane spTankenInfo = new JScrollPane(jTableTankenInfo);
		spTankenInfo.setPreferredSize(new Dimension(100, 135));

		JTabbedPane tab = new JTabbedPane();
		tab.addTab(Constans.TANKEN,
				new ShowTanken(tankenDao).getJpTankenTable());
		tab.addTab(Constans.SONSTIGE_AUSGABEN,
				new ShowSonstigeAusgaben(sADao).getJpSonstigeAusgabenTable());

		JPanel jpEingaben = new JPanel(new BorderLayout());
		JPanel jpAusgabe = new JPanel(new BorderLayout());
		JPanel jpBtn = new JPanel(new GridLayout(1, 2));

		jpEingaben.add(comboBoxAutos, BorderLayout.EAST);

		jpBtn.add(btnTanken);
		jpBtn.add(btnSonstigeAusgaben);

		jpAusgabe.add(spTankenInfo, BorderLayout.CENTER);
		jpAusgabe.add(jpBtn, BorderLayout.SOUTH);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(tab, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}

	private Object[][] loadTankungenInfos(TankenDao tankenDao,
			Settings setting, SonstigeAusgabenDao sADao) {
		Object[][] o = new Object[0][0];
		int index = 0;

		TankenInfo tankenInfo = new TankenInfo(tankenDao, setting);
		SonstigeAusgabenInfo sonstigeAusgabenInfo = new SonstigeAusgabenInfo(
				sADao);

		List<Info> tankInfos = tankenInfo.getTankenInfos();
		List<Info> sonstigeAusgabenInfos = sonstigeAusgabenInfo
				.getSonstigeAusgabenInfos();
		List<Info> allInfos = new ArrayList<Info>();

		Info allKosten = new Info(Constans.GESAMT_KOSTEN);
		try {
			allKosten = tankenInfo.searchInfo(Constans.TANKEN_KOSTEN);
			allKosten = allKosten.add(sonstigeAusgabenInfo
					.searchInfo(Constans.SONSTIGEAUSGABEN_KOSTEN));
		} catch (NullPointerException ex) {
			allKosten = new Info(Constans.GESAMT_KOSTEN);
		} finally {
			allKosten.setName(Constans.GESAMT_KOSTEN);
		}

		allInfos.addAll(tankInfos);
		allInfos.addAll(sonstigeAusgabenInfos);
		allInfos.add(allKosten);

		if (tankInfos.size() == 0) {
			return o;
		}

		o = new Object[allInfos.size()][columnNamesTankenInfos.length];

		for (Info info : allInfos) {
			o[index][0] = info.getName();
			o[index][1] = info.getDiesesJahr();
			o[index][2] = info.getVorjahr();
			o[index][3] = info.getGesammt();
			index++;
		}
		return o;
	}

	private void setActions(final Settings setting) {
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
