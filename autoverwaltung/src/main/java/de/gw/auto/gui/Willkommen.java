package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.gw.auto.dao.AutoDAO;
import de.gw.auto.dao.BenzinartDAO;
import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.OrtDao;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.TankDAO;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;
import de.gw.auto.gui.Button.Funktionen;

public class Willkommen {

	private Benutzer benutzer = new Benutzer();
	private Settings setting = new Settings(benutzer);
	AutoDAO aDao = new AutoDAO(setting);
	TankenDao tDao = new TankenDao();
	private SonstigeAusgabenDao sADao = new SonstigeAusgabenDao(setting);

	private static JFrame frame = new JFrame();
	private static JTextField tfEingabe = new JTextField("M - WU 3194");
	private static JButton btnOk = new JButton("OK");
	private JLabel lAuto = new JLabel(
			"Bitte geben Sie das Kennzeichen des Autos ein:");
	private static JLabel lStatus = new JLabel("Daten werden geladen");

	public static void main(String[] args) {
		new Willkommen();
	}

	public Willkommen() {
		frame = new JFrame();
		frame.setTitle("Willkommen");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Funktionen funk = new Funktionen();
				funk.exit(frame);
			}
		});

		btnOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setting.setAktuellAuto(aDao.find(tfEingabe.getText()));
				aDao.setAutoList(setting);

				if (setting.getAktuellAuto() == null) {
					lStatus.setText("Falsche eingabe bitte versuchen sie es Erneut!!");
					tfEingabe.setText("");
					tfEingabe.setFocusable(true);
					return;
				}

				if (setting.getAktuellAuto() != null) {
					try {

						new BenzinartDAO().setBenzinList();
						new TankDAO().setTankList();
						LandDao.setLaender();
						OrtDao.setOrtList();
						sADao.setSonstigeAusgabenList(setting);
						tDao.setTankenList(setting);
						lStatus.setText("Daten wurden geladen");
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						// sqlS.closeResults(sqlS.getSt(), sqlS.getRs(),
						// sqlS.getConn());
					}
				}
				System.out.println(setting.getAktuellAuto().getId());
				
				new GuiShowTanken(setting, tDao);
			}
		});

		Container con = new Container();
		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel jpEingabe = new JPanel();
		jpEingabe.setLayout(new GridLayout(3, 1));

		tfEingabe.setEnabled(false);
		jpEingabe.add(lAuto);
		jpEingabe.add(tfEingabe);
		jpEingabe.add(btnOk);

		con.add(jpEingabe, BorderLayout.CENTER);
		con.add(lStatus, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	private void inizialsieren() {
		if (aDao.getAutoList() != null) {
			tfEingabe.setEnabled(true);
			lStatus.setText("Bitte geben sie Ihr kennzeichen ein");

		}
	}

	public static String eingabe() {
		String s = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			s = br.readLine();
		} catch (IOException e) {
			throw new IllegalArgumentException("falsche eingabe!!");
		}
		return s;
	}
}