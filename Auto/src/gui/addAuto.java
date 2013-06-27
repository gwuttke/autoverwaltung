package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

public class addAuto extends JFrame implements ActionListener {

	// Klassen
	final static AutoDAO autoDao = new AutoDAO();

	// Komponenten
	final static JFrame frame = new JFrame();
	final static JList<String> ALL_AUTO_LIST = new JList<String>();
	final static JTextField KFZ_ZEICHEN_TEXT_FIELD = new JTextField();
	static JSpinner KM_STAND_SPINNER = new JSpinner(new SpinnerNumberModel());
	final static JSpinner KFZ_ERST_ZULASSUNG_SPINNER = new JSpinner(
			new SpinnerDateModel());
	final static JSpinner KFZ_KAUF_SPINNER = new JSpinner(
			new SpinnerDateModel());
	final static JButton NEW_AUTO_BUTTON = new JButton("neues Auto");
	final static JLabel KFZ_LABEL_LABEL = new JLabel("Kennzeichen:");
	final static JLabel KM_LABEL = new JLabel("Km-Stand:");
	final static JLabel KFZ_ERST_LABEL = new JLabel("Erst Zulassung:");
	final static JLabel KFZ_KAUF_LABEL = new JLabel("Kaufdatum:");

	
	private static JSpinner getDateSpinner() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
				
		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(date);
		JSpinner spinner = new JSpinner(model);
		
		JSpinner.DateEditor editor = (JSpinner.DateEditor) spinner
				.getEditor();
		DateFormat format = editor.getFormat();
		format.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		format.applyPettern("");
		editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		Dimension d = KM_STAND_SPINNER.getPreferredSize();
		d.width = 85;
		KM_STAND_SPINNER.setPreferredSize(d);
		JPanel panel = new JPanel();
		panel.add(KM_STAND_SPINNER);
		return KM_STAND_SPINNER;
	}
	
	
	
	private static JSpinner getKmSpinner() {
		double min = 0;
		double value = 10000;
		double max = 999999;
		double stepSize = 10;
		SpinnerNumberModel model = new SpinnerNumberModel(value, min, max,
				stepSize);
		KM_STAND_SPINNER = new JSpinner(model);
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor) KM_STAND_SPINNER
				.getEditor();
		DecimalFormat format = editor.getFormat();
		format.setMinimumFractionDigits(0);
		editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		Dimension d = KM_STAND_SPINNER.getPreferredSize();
		d.width = 85;
		KM_STAND_SPINNER.setPreferredSize(d);
		JPanel panel = new JPanel();
		panel.add(KM_STAND_SPINNER);
		return KM_STAND_SPINNER;
	}

	public void MailTestFrame() {
		setTitle("MailTest");
		setSize(300, 300);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;

		gbc.weightx = 0;
		add(new JLabel("Kennzeichen"), gbc, 0, 0, 1, 1);
		gbc.weightx = 100;
		JTextField kfzZeichen = new JTextField(20);
		add(kfzZeichen, gbc, 1, 0, 1, 1);

		gbc.weightx = 0;
		add(new JLabel("Km Stand beim Kauf"), gbc, 0, 1, 1, 1);
		gbc.weightx = 100;
		//JSpinner  = new JTextField(20);
		add(getKmSpinner(), gbc, 1, 1, 1, 1);

		gbc.weightx = 0;
		add(new JLabel("Km Stand Aktuell"), gbc, 0, 2, 1, 1);
		gbc.weightx = 100;
		//smtpServer = new JTextField(20);
		add(getKmSpinner(), gbc, 1, 2, 1, 1);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 100;
		message = new JTextArea();
		add(new JScrollPane(message), gbc, 0, 3, 2, 1);

		response = new JTextArea();
		add(new JScrollPane(response), gbc, 0, 4, 2, 1);

		gbc.weighty = 0;
		JButton sendButton = new JButton("Senden");
		sendButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(sendButton);
		add(buttonPanel, gbc, 0, 5, 2, 1);
	}

	private void add(Component c, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(c, gbc);
	}

	public static void main(String[] args) {

		frame.add(KFZ_LABEL_LABEL, BorderLayout.BEFORE_LINE_BEGINS);
		frame.add(KFZ_ZEICHEN_TEXT_FIELD, BorderLayout.);
		frame.add(getKmSpinner(), BorderLayout.LINE_START);
		frame.add(KM_LABEL, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
}
