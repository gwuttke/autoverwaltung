package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.michaelbaranov.microba.calendar.DatePicker;

import de.gw.auto.dao.AutoDAO;
import de.gw.auto.dao.BenzinartDAO;
import de.gw.auto.dao.Settings;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.service.AutoService;

@Controller
public class AddAuto extends JFrame {

	private Settings setting;
	// Klassen
	@Autowired
	private AutoService autoService;

	@Autowired
	private BenzinartDAO benzinartDao;

	@Autowired
	private ShowGui showGui;

	private final static JTextField KFZ_ZEICHEN_TEXT_FIELD = new JTextField();
	private static JSpinner KM_STAND_SPINNER = new JSpinner(
			new SpinnerNumberModel());
	private final static DatePicker KFZ_ERSTDATUM_SPINNER = getDateSpinner("KFZ_ERSTDATUM_SPINNER");
	private final static DatePicker KFZ_KAUF_SPINNER = getDateSpinner("KFZ_KAUF_SPINNER");
	private final static JLabel KFZ_ZEICHEN_LABEL = new JLabel("Kennzeichen:");
	private final static JLabel KM_LABEL = new JLabel("Km-Stand:");
	private final static JLabel KFZ_ERST_LABEL = new JLabel("Erst Zulassung:");
	private final static JLabel KFZ_KAUF_LABEL = new JLabel("Kaufdatum:");
	private final static JLabel Benzinart_LABEL = new JLabel("Benzinarten:");
	private final static DefaultListModel<Benzinart> baModel = new DefaultListModel<Benzinart>();
	private final static DefaultComboBoxModel<Benzinart> bModel = new DefaultComboBoxModel<Benzinart>();
	private final static JList<Benzinart> autoBenzinarten = new JList<Benzinart>(
			baModel);
	private final static JComboBox<Benzinart> cmboBoxBenzinarten = new JComboBox<Benzinart>(
			bModel);
	private final static JButton BTN_NEW_AUTO = new JButton("neues Auto");
	private final static JButton BTN_ADD_BENZINART = new JButton("Hinzufügen");
	private final static JButton BTN_CANCEL = new JButton("Abbrechen");

	Set<Benzinart> benzinArten = new HashSet<Benzinart>();

	private static DatePicker getDateSpinner(final String name) {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = (java.util.Date) cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		sdf.applyPattern("dd.MM.yyyy");

		final DatePicker datepicker = new DatePicker(date, sdf);
		datepicker.setName(name);

		return datepicker;
	}

	protected AddAuto() {
	}
	
	public void init(Settings setting) {
	this.setting = setting;
	}

	void showGui() {
		fillComboBox();

		Container con = new Container();
		con = this.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel jplLable = new JPanel(new GridLayout(0, 1));
		JPanel jplTb = new JPanel(new GridLayout(0, 1));
		JPanel jplBenzinarten = new JPanel(new GridLayout(0, 1));
		JPanel jplAddBenzinarten = new JPanel(new GridLayout(0, 2));
		JPanel jplBtn = new JPanel(new GridLayout(0, 2));

		jplLable.add(KFZ_ZEICHEN_LABEL);
		jplLable.add(KFZ_KAUF_LABEL);
		jplLable.add(KFZ_ERST_LABEL);
		jplLable.add(KM_LABEL);

		jplTb.add(KFZ_ZEICHEN_TEXT_FIELD);
		jplTb.add(KFZ_KAUF_SPINNER);
		jplTb.add(KFZ_ERSTDATUM_SPINNER);
		jplTb.add(KM_STAND_SPINNER);

		jplAddBenzinarten.add(cmboBoxBenzinarten);
		jplAddBenzinarten.add(BTN_ADD_BENZINART);
		jplBenzinarten.add(Benzinart_LABEL);
		jplBenzinarten.add(autoBenzinarten);
		jplBenzinarten.add(jplAddBenzinarten);

		jplBtn.add(BTN_NEW_AUTO);
		jplBtn.add(BTN_CANCEL);

		con.add(jplLable, BorderLayout.WEST);
		con.add(jplTb, BorderLayout.CENTER);
		con.add(jplBenzinarten, BorderLayout.EAST);
		con.add(jplBtn, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		activateListeners(setting);

		pack();
		setVisible(true);
	}

	private void activateListeners(final Settings setting) {
		BTN_NEW_AUTO.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < baModel.getSize(); i++) {
					benzinArten.add(baModel.get(i));
				}
				Auto a = new Auto(KFZ_ZEICHEN_TEXT_FIELD.getText(), Integer
						.parseInt(KM_STAND_SPINNER.getValue().toString()),
						(java.util.Date) KFZ_KAUF_SPINNER.getDate(),
						(java.util.Date) KFZ_ERSTDATUM_SPINNER.getDate(),
						benzinArten, Integer.parseInt(KM_STAND_SPINNER
								.getValue().toString()));

				setting.set(autoService.addAuto(setting, a));
				showGui.setSetting(setting);
				showGui.showGui();
				dispose();

			}
		});

		BTN_ADD_BENZINART.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				baModel.addElement((Benzinart) bModel.getSelectedItem());
			}
		});

		BTN_CANCEL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (setting.getAutos().size() > 0) {
					showGui.setSetting(setting);
					showGui.showGui();
				} else {
					new LogIn();
				}
				dispose();
			}
		});
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}

	private void fillComboBox() {
		for (Benzinart b : benzinartDao.getBenzinartList()) {
			bModel.addElement(b);
		}
	}

}
