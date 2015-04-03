package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.Constans;
import de.gw.auto.dao.Settings;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;
import de.gw.auto.reports.Drucken;

@Controller
public class ShowGui {

	private Settings setting;

	@Autowired
	AddAuto addAuto;

	@Autowired
	ShowAuto showAuto;

	@Autowired
	Drucken drucken;

	@Autowired
	private AddTanken addTanken;

	@Autowired
	private ShowTanken showTanken;

	@Autowired
	private ShowInfos showInfos;

	@Autowired
	private ShowSonstigeAusgaben showSonstigeAusgaben;

	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton(
			"Sonstige Ausgaben hinzufügen");
	private JButton btnAddAuto = new JButton("Auto Hinzufügn");
	private JButton btnPrint = new JButton("Drucken");
	private JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>();
	private JPanel jpAusgabe = new JPanel(new BorderLayout());
	JFrame frame = new JFrame("Auto Verwaltung");

	private JTable tankenTable = null;
	private JTable sonstigeAusgabenTable = null;
	private JScrollPane autoTabelle = null;
	private JTabbedPane tab = new JTabbedPane();

	protected ShowGui() {
	}

	public void init(Settings setting) {
		this.setting = setting;
		addAuto.init(setting);
		addTanken.init(setting);
		drucken.init(setting);
		showAuto.init(setting);
		showTanken.init(setting);
		showSonstigeAusgaben.init(setting);
		showInfos.init(setting);

		loadDaten(setting);
	}

	public void showGui() {
		
		comboBoxAutos = new JComboBox<Auto>(setting.getAutosArray());

		comboBoxAutos.getModel().setSelectedItem(setting.getAktuellAuto());
		setActions(setting);

		// tabelle Inizaliesieren
		Dimension d = new Dimension(10, 5);
		Dimension dTable = new Dimension(200, 120);

		// btnAddAuto.setPreferredSize(d);
		// comboBoxAutos.setPreferredSize(d);
		autoTabelle.setPreferredSize(dTable);

		Container con = new Container();

		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel jpEingaben = new JPanel(new BorderLayout());
		JPanel jpButton = new JPanel();
		JPanel jpAuto = new JPanel(new GridLayout(2, 1));
		JPanel jpTables = new JPanel(new GridLayout(1, 1));

		jpButton.add(btnAddAuto);
		jpAuto.add(comboBoxAutos);
		jpAuto.add(autoTabelle);
		jpEingaben.add(jpButton, BorderLayout.WEST);
		jpEingaben.add(autoTabelle, BorderLayout.CENTER);
		jpEingaben.add(jpAuto, BorderLayout.EAST);
		jpTables.add(tab);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(jpTables, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.setMinimumSize(new Dimension(950, 250));
		frame.setSize(new Dimension(950, 800));
		frame.setVisible(true);
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}

	private JPanel loadButtons() {
		JPanel jpBtn = new JPanel(new GridLayout(1, 3));
		jpBtn.add(btnTanken);
		jpBtn.add(btnPrint);
		jpBtn.add(btnSonstigeAusgaben);
		return jpBtn;
	}

	private void loadAusgaben() {
		jpAusgabe.removeAll();

		jpAusgabe.add(showInfos.getJpInfosTable(), BorderLayout.CENTER);
		jpAusgabe.add(loadButtons(), BorderLayout.SOUTH);

	}

	private void loadDaten(Settings setting) {

		this.tankenTable = showTanken.getjTableTanken();
		this.sonstigeAusgabenTable = showSonstigeAusgaben
				.getjTableSonstigeAusgaben();
		this.autoTabelle = showAuto.getTable();
		tab.removeAll();
		tab.addTab(Constans.TANKEN, new JScrollPane(this.tankenTable));
		tab.addTab(Constans.SONSTIGE_AUSGABEN, new JScrollPane(
				this.sonstigeAusgabenTable));

		loadAusgaben();
	}

	private void setActions(final Settings setting) {

		tankenTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int columnCount = target.getColumnCount();
				if (columnCount > 1) {
					if (e.getClickCount() == 2) {

						int row = target.getSelectedRow();

						Tanken t = showTanken.getRowObject(row);

						new AddTanken(t, setting);
						frame.dispose();
					}
				}
			}
		});

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
				addTanken.init(setting);
				addTanken.showGui();
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

		btnAddAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addAuto.showGui();
				frame.dispose();
			}
		});

		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable[] tables = new JTable[2];
				if (tab.getSelectedIndex() == 0) {
					tables[0] = (JTable) showTanken.getjTableTanken();
				} else {
					tables[0] = (JTable) showSonstigeAusgaben
							.getjTableSonstigeAusgaben();
				}
				tables[1] = (JTable) showInfos.getjTableInfos();
				// new Drucken(setting).print(tables);
				drucken.printReport();

			}
		});
	}
}
