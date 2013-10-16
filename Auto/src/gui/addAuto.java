package gui;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.activation.MailcapCommandMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import dao.AutoDAO;
import database.Procedures;
import domain.Datum;

public class addAuto extends JFrame 
implements ActionListener {

	/*
	 *  
	 * Hier fehlt noch der Befehl Hinzufügen in die Datenbank 
	 * 
	 * 
	 */
	
	
	// Klassen
	final static AutoDAO autoDao = new AutoDAO();
	final static Procedures proc = new Procedures();
	// Komponenten
	final static JFrame frame = new JFrame();
	final static JList<String> ALL_AUTO_LIST = new JList<String>();
	final static JTextField KFZ_ZEICHEN_TEXT_FIELD = new JTextField();
	static JSpinner KM_STAND_SPINNER = new JSpinner(new SpinnerNumberModel());
	final static JSpinner KFZ_DATUM_SPINNER = new JSpinner(
			new SpinnerDateModel());
	final static JSpinner KFZ_KAUF_SPINNER = new JSpinner(
			new SpinnerDateModel());
	final static JButton NEW_AUTO_BUTTON = new JButton("neues Auto");
	final static JLabel KFZ_LABEL_LABEL = new JLabel("Kennzeichen:");
	final static JLabel KM_LABEL = new JLabel("Km-Stand:");
	final static JLabel KFZ_ERST_LABEL = new JLabel("Erst Zulassung:");
	final static JLabel KFZ_KAUF_LABEL = new JLabel("Kaufdatum:");
 
	
	private static JSpinner getDateSpinner(final String name) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
				
		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(date);
		JSpinner spinner = new JSpinner(model);
		spinner.setName(name);
		JSpinner.DateEditor editor = (JSpinner.DateEditor) spinner
				.getEditor();
		DateFormat format = editor.getFormat();
		format.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		//format.applyPettern("dd.MM.yyyy");
		editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		Dimension d = spinner.getPreferredSize();
		d.width = 85;
		spinner.setPreferredSize(d);
		JPanel panel = new JPanel();
		panel.add(spinner);
		return spinner;
	}
	
	
	
	private static JSpinner getKmSpinner(final String name) {
		double min = 0;
		double value = 10000;
		double max = 999999;
		double stepSize = 10;
		SpinnerNumberModel model = new SpinnerNumberModel(value, min, max,
				stepSize);
		JSpinner spinner = new JSpinner(model);
		spinner.setName(name);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner
				.getEditor();
		DecimalFormat format = editor.getFormat();
		format.setMinimumFractionDigits(0);
		editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		Dimension d = spinner.getPreferredSize();
		d.width = 85;
		spinner.setPreferredSize(d);
		JPanel panel = new JPanel();
		panel.add(spinner);
		return spinner;
	}

	

	private static void add(Component c, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		frame.getContentPane().add(c, gbc);
	}
	

	public static void main(String[] args) {
		addAuto addA = new addAuto();
	
		frame.setTitle("Auto Hinzufügen");
		frame.setSize(300, 300);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;

		gbc.weightx = 0;
		add(new JLabel("Kennzeichen"), gbc, 0, 0, 1, 1);
		gbc.weightx = 100;
		final JTextField kfzZeichen = new JTextField(20);
		add(kfzZeichen, gbc, 1, 0, 1, 1);

		gbc.weightx = 0;
		add(new JLabel("Km Stand beim Kauf"), gbc, 0, 1, 1, 1);
		gbc.weightx = 100;
		final JSpinner JKaufKm = getKmSpinner("kaufKm");
		//JSpinner  = new JTextField(20);
		add(JKaufKm, gbc, 1, 1, 1, 1);

		gbc.weightx = 0;
		add(new JLabel("Km Stand Aktuell"), gbc, 0, 2, 1, 1);
		gbc.weightx = 100;
		//smtpServer = new JTextField(20);
		final JSpinner JKmStand = getKmSpinner("KmStand");
		add(JKmStand, gbc, 1, 2, 1, 1);

		gbc.weightx = 0;
		add(new JLabel("Kauf Datum"), gbc, 0, 3, 1, 1);
		gbc.weighty = 100;
		//message = new JTextArea();
		final JSpinner JKaufDatum = getDateSpinner("DatumKaufen");
		add(JKaufDatum, gbc, 1, 3, 1, 1);
		
		gbc.weightx = 0;
		add(new JLabel("Erstzulassungs Datum"), gbc, 0, 4, 1, 1);
		gbc.weighty = 100;
		//message = new JTextArea();
		final JSpinner JDatumErstZulassung = getDateSpinner("DatumErstZulassung");
		add(JDatumErstZulassung, gbc, 1, 4, 1, 1);
				
		gbc.weighty = 0;
	      JButton addButton = new JButton("Hinzufügen");
	      addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	proc.setAddAuto(kfzZeichen.getText(), (int) JKaufKm.getValue(),(Date) JKaufDatum.getValue(),
				//		 "BenzinArten");
				
			}
		});
	      JPanel buttonPanel = new JPanel();
	      buttonPanel.add(addButton);
	      add(buttonPanel, gbc, 0, 5, 2, 1);
		
		
		frame.setVisible(true);
		frame.pack();
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
