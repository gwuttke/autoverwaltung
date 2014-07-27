package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
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

import de.gw.auto.dao.AutoDAO;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Settings;

public class AddAuto extends JFrame {


	private Settings setting;
	// Klassen
	final AutoDAO autoDao = new AutoDAO(setting );
	// Komponenten
	final static JList<String> ALL_AUTO_LIST = new JList<String>();
	final static JTextField KFZ_ZEICHEN_TEXT_FIELD = new JTextField();
	static JSpinner KM_STAND_SPINNER = new JSpinner(new SpinnerNumberModel());
	final static JSpinner KFZ_ERSTDATUM_SPINNER =  getDateSpinner("KFZ_ERSTDATUM_SPINNER");
	final static JSpinner KFZ_KAUF_SPINNER = getDateSpinner("KFZ_KAUF_SPINNER");
	final static JButton BTN_NEW_AUTO = new JButton("neues Auto");
	final static JLabel KFZ_ZEICHEN_LABEL = new JLabel("Kennzeichen:");
	final static JLabel KM_LABEL = new JLabel("Km-Stand:");
	final static JLabel KFZ_ERST_LABEL = new JLabel("Erst Zulassung:");
	final static JLabel KFZ_KAUF_LABEL = new JLabel("Kaufdatum:");

	Set<Benzinart> benzinArten = new HashSet<Benzinart>();
	
	private static JSpinner getDateSpinner(final String name) {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = (java.util.Date) cal.getTime();

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(date);
		JSpinner spinner = new JSpinner(model);
		spinner.setName(name);
		JSpinner.DateEditor editor = (JSpinner.DateEditor) spinner.getEditor();
		DateFormat format = editor.getFormat();
		format.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		// format.applyPettern("dd.MM.yyyy");
		editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		Dimension d = spinner.getPreferredSize();
		d.width = 85;
		spinner.setPreferredSize(d);
		return spinner;
	}

	public AddAuto(Settings setting) {
		super();
		
		Container con = new Container();
		con.setLayout(new BorderLayout());
		con = this.getContentPane();
		
		JPanel jplLable = new JPanel(new GridLayout(0,1));
		JPanel jplTb = new JPanel(new GridLayout(0,1));
		
		jplLable.add(KFZ_ZEICHEN_LABEL);
		jplLable.add(KFZ_KAUF_LABEL);
		jplLable.add(KFZ_ERST_LABEL);
		jplLable.add(KM_LABEL);
		jplTb.add(KFZ_ZEICHEN_TEXT_FIELD);
		jplTb.add(KFZ_KAUF_SPINNER);
		jplTb.add(KFZ_ERSTDATUM_SPINNER);
		jplTb.add(KM_STAND_SPINNER);
		
		
		con.add(jplLable, BorderLayout.WEST);
		con.add(jplTb, BorderLayout.CENTER);
		con.add(BTN_NEW_AUTO, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		BTN_NEW_AUTO.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 
				autoDao.CarIntoDatabase(KFZ_ZEICHEN_TEXT_FIELD.getText(), Integer.parseInt(KM_STAND_SPINNER.getValue().toString()), (java.util.Date) KFZ_ERSTDATUM_SPINNER.getValue(),
						(java.util.Date) KFZ_KAUF_SPINNER.getValue(), benzinArten, Integer.parseInt(KM_STAND_SPINNER.getValue().toString()));
				
			}
		});		
		pack();
		setVisible(true);
		
	}

}
