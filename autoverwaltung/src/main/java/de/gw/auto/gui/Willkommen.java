package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.gw.auto.Constans;
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

public class Willkommen implements Runnable{

	private static JFrame frame = new JFrame();
	private static JLabel lStatus = new JLabel("Daten werden geladen...");

	public Willkommen() {
		run();
	}
	
	private void show(){
		frame = new JFrame();
		frame.setTitle("Willkommen");
		

		Container con = new Container();
		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		con.add(lStatus, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
		setListeners();
	}

	public static void setStatus(String statusText) {
		lStatus.setText(statusText);
	}

	private void setListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Funktionen.exit(frame);
			}
		});

		lStatus.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (lStatus.getText() == Constans.CLOSE_WINDOW) {
					frame.dispose();
				}

			}
		});

	}

	@Override
	public void run() {
		this.show();
	}

}