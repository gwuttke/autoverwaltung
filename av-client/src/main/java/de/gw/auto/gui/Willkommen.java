package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sun.corba.se.impl.io.FVDCodeBaseImpl;

import de.gw.auto.Constans;
import de.gw.auto.dao.AutoDAO;
import de.gw.auto.dao.BenzinartDAO;
import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.OrtDao;
import de.gw.auto.dao.Settings;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.TankDAO;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.gui.Button.Funktionen;
import de.gw.auto.service.implementation.FtpService;
import de.gw.auto.service.implementation.Versionierungsservice;

@Controller
public class Willkommen extends Thread {
	
	@Autowired
	Versionierungsservice versionierungsservice;

	private JFrame frame = new JFrame();
	private JLabel lStatus = new JLabel("Programm Inizialisieren...");
	private JLabel lVersion = new JLabel(
			versionierungsservice
					.getVersionString(Constans.PROGRAMM_VERSION));
	private static boolean visible;

	public static void main(String[] args) {
		new Willkommen();
	}


	public Willkommen() {
		this.visible=false;
	}

	public void show() {
		if (visible) {
			//new LogIn();
			frame.setVisible(false);
			visible = false;
			return;
		}
		frame = new JFrame();
		frame.setTitle("Willkommen");

		Container con = new Container();
		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		con.add(lVersion, BorderLayout.CENTER);
		con.add(lStatus, BorderLayout.SOUTH);
		setListeners();

		frame.pack();
		frame.setVisible(true);
		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if(visible){
				frame.setCursor(Cursor.WAIT_CURSOR);
				}else{
					frame.setCursor(Cursor.DEFAULT_CURSOR);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		visible = true;
	}

	public void setStatus(String statusText) {
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
					Funktionen.cancel(frame);
				}

			}
		});

	}

	@Override
	public void run() {
		this.show();

		/*
		 * new FtpService().downloadCurrentVersion(); new LogIn();
		 * Funktionen.cancel(frame);
		 */
	}

}