package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import de.gw.auto.Constans;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.service.Drucken;

public class ShowGui {

	private JButton btnTanken = new JButton("Tanken hinzufügen");
	private JButton btnSonstigeAusgaben = new JButton(
			"Sonstige Ausgaben hinzufügen");
	private JButton btnAddAuto = new JButton("Auto Hinzufügn");
	private JButton btnPrint = new JButton("Drucken");
	private JComboBox<Auto> comboBoxAutos = new JComboBox<Auto>();
	private JPanel jpAusgabe = new JPanel(new BorderLayout());
	JFrame frame = new JFrame("Auto Verwaltung");

	private ShowTanken showTanken = null;
	private ShowInfos showInfos = null;
	private JTable tankenTable = null;
	private JTable sonstigeAusgabenTable = null;
	private ShowSonstigeAusgaben showSonstigeAusgaben = null;
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

		jpEingaben.add(btnAddAuto, BorderLayout.WEST);
		jpEingaben.add(comboBoxAutos, BorderLayout.EAST);

		con.add(jpEingaben, BorderLayout.NORTH);
		con.add(tab, BorderLayout.CENTER);
		con.add(jpAusgabe, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

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
		showTanken = new ShowTanken(setting);
		showSonstigeAusgaben = new ShowSonstigeAusgaben(setting);
		this.tankenTable = showTanken.getjTableTanken();
		this.sonstigeAusgabenTable = showSonstigeAusgaben
				.getjTableSonstigeAusgaben();
		tab.removeAll();
		tab.addTab(Constans.TANKEN, new JScrollPane(this.tankenTable));
		tab.addTab(Constans.SONSTIGE_AUSGABEN, new JScrollPane(
				this.sonstigeAusgabenTable));

		showInfos = new ShowInfos(setting);
		loadAusgaben();
	}

	private void setActions(final Settings setting) {

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				frame.dispose();
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

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

		btnAddAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddAuto(setting);
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
				new Drucken(setting).print(tables);

			}
		});
	}
}
