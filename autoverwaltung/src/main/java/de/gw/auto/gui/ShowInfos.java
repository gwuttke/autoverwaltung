package de.gw.auto.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.dom4j.io.SAXWriter;

import de.gw.auto.Constans;
import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.SonstigeAusgabenInfo;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.gui.model.Tabelle;

public class ShowInfos {

	private String[] columnNamesTankenInfos = new String[] { "Bezeichnung",
			"Dieses Jahr", "letztes Jahr", "Gesammt" };
	private Object[][] infosData = new Object[0][0];

	private JPanel jpInfosTable = new JPanel(new GridLayout(1, 1));
	private JTable jTableInfos = null;

	public ShowInfos(TankenDao tankenDao, Settings setting,
			SonstigeAusgabenDao sADao) {

		if (tankenDao.getTankenList() != null && sADao.getSonstigeAusgabenList() != null){
		infosData = loadTankungenInfos(tankenDao, setting, sADao);
		}

		jTableInfos = new Tabelle(columnNamesTankenInfos, infosData)
				.getJTable();
		
		JScrollPane spInfos = new JScrollPane(jTableInfos);
		spInfos.setPreferredSize(new Dimension(100, 135));

		jpInfosTable.add(spInfos);

	}
	
	public JPanel getJpInfosTable() {
		return jpInfosTable;
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

}
