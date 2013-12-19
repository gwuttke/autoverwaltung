package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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

import dao.AutoDAO;
import dao.BenzinartDAO;
import dao.LandDao;
import dao.OrtDao;
import dao.SonstigeAusgabenDao;
import dao.TankenDao;
import database.SqlServer;
import domain.Auto;
import domain.Settings;

public class Willkommen extends SqlServer {

	private static AutoDAO aDAO = new AutoDAO();
	private static JFrame frame = new JFrame();
	private static JTextField tfEingabe = new JTextField();
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
				frame.dispose();
				System.exit(0);
			}
		});
		
		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				inizialsieren();
				lStatus.setText("Bitte geben sie ihr Kennzeichen ein.");
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				do {
					Settings.setAuto(AutoDAO.getAuto(tfEingabe.getText()));
				} while (tfEingabe.getText() == null);

				if (Settings.getAuto() == null){
					lStatus.setText("Falsche eingabe bitte versuchen sie es Erneut!!");
					tfEingabe.setText(null);
					tfEingabe.setFocusable(true);
					return ;
				}
				if (Settings.getAuto() != null) {
					try {

						BenzinartDAO.setBenzinList();
						LandDao.setLaender();
						OrtDao.setOrtList();
						SonstigeAusgabenDao.setSonstigeAusgabenList();
						TankenDao.setTankenList();
						lStatus.setText("Daten wurden geladen");
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						// sqlS.closeResults(sqlS.getSt(), sqlS.getRs(),
						// sqlS.getConn());
					}
				}
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

	private static void inizialsieren() {
		System.out.println("Datenbank abfrage");

		openConnection();

		try {
			AutoDAO.setAutoList();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (AutoDAO.getAutoList() != null) {
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
