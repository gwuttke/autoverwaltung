package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.gw.auto.Constans;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;

public class GuiShowTanken {


	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton(
			"Sonstige Ausgaben hinzufügen");

	public GuiShowTanken(Settings setting) {
		TankenDao tankenDao = new TankenDao(setting);
		SonstigeAusgabenDao sADao = new SonstigeAusgabenDao(setting);
		

		JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>(
				setting.getAutosArray());

		comboBoxAutos.getModel().setSelectedItem(setting.getAktuellAuto());
		setActions(setting);
		
		//tabelle Inizaliesieren

		JFrame frame = new JFrame("Tanken");
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		
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

		jpAusgabe.add(new ShowInfos(tankenDao, setting, sADao).getJpInfosTable(), BorderLayout.CENTER);
		jpAusgabe.add(jpBtn, BorderLayout.SOUTH);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(tab, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

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
