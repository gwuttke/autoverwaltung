package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

public class ShowGui {

	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton(
			"Sonstige Ausgaben hinzufügen");
	private JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>();
	private JPanel jpAusgabe = new JPanel(new BorderLayout());
	JFrame frame = new JFrame("Tanken");

	private ShowTanken showTanken = null;
	private ShowInfos showInfos = null;
	private JTabbedPane tab = new JTabbedPane();

	public ShowGui(Settings setting) {
		loadDaten(setting);

		comboBoxAutos = new JComboBox<Auto>(setting.getAutosArray());

		comboBoxAutos.getModel().setSelectedItem(setting.getAktuellAuto());
		setActions(setting);

		// tabelle Inizaliesieren
		
		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel jpEingaben = new JPanel(new BorderLayout());


		jpEingaben.add(comboBoxAutos, BorderLayout.EAST);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(tab, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}
	
	private JPanel loadButtons(){
		JPanel jpBtn = new JPanel(new GridLayout(1, 2));
		jpBtn.add(btnTanken);
		jpBtn.add(btnSonstigeAusgaben);
		return jpBtn;
	}

	private void loadAusgaben() {
		jpAusgabe.removeAll();

		jpAusgabe.add(showInfos.getJpInfosTable(), BorderLayout.CENTER);
		jpAusgabe.add(loadButtons(), BorderLayout.SOUTH);

	}

	private void loadDaten(Settings setting) {
		showTanken = new ShowTanken(setting);
		tab.removeAll();
		tab.addTab(Constans.TANKEN, showTanken.getJpTankenTable());
		tab.addTab(Constans.SONSTIGE_AUSGABEN,
				new ShowSonstigeAusgaben(setting).getJpSonstigeAusgabenTable());

		showInfos = new ShowInfos(setting);
		loadAusgaben();
	}

	private void setActions(final Settings setting) {

		comboBoxAutos.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Auto auto = (Auto) e.getItem();
				setting.setAktuellAuto(auto);
				loadDaten(setting);
			}
		});

		btnTanken.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddTanken(setting);
				frame.dispose();
			}
		});
		btnSonstigeAusgaben.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddSonstigeAusgaben(setting);
				frame.dispose();
			}
		});
	}
}
