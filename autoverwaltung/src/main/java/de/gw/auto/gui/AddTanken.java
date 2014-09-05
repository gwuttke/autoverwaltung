package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.michaelbaranov.microba.calendar.DatePicker;

import de.gw.auto.Constans;
import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.OrtDao;
import de.gw.auto.dao.TankDAO;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.Button.Funktionen;
import de.gw.auto.gui.model.Spinner;
import de.gw.auto.service.TankenService;

public class AddTanken extends Funktionen {
	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();
	private static final Texte.Error.Titel textError = new Texte.Error.Titel();
	JFrame frame = new JFrame();

	Settings setting;
	LandModel lModel = new LandModel();
	OrtModel oModel = new OrtModel();
	TankenDao tankenDao = new TankenDao(setting);
	BenzinartModel bModel = new BenzinartModel();
	TankModel tModel = new TankModel();
	TankenService tankenService = null;

	// Eingaben
	private final DatePicker datepicker = new DatePicker(new Date());
	private JSpinner spKmStand;
	private JComboBox<Land> cbLand;
	private JComboBox<Ort> cbOrt;
	private JComboBox<Benzinart> cbBenzinart;
	private JComboBox<Tank> cbTank;
	private JSpinner spLiter;
	private JSpinner spPreisPLiter;
	private JSpinner spKosten;

	// Button
	private JButton btnAdd = new JButton(Texte.Form.Button.HINZUFUEGEN);
	private JButton btnCancel = new JButton(Texte.Form.Button.ABBRUCH);

	public AddTanken(final Settings set) {
		this.setting = set;
		tankenService = new TankenService(this.setting);
		// Label
		JLabel lDatum = new JLabel(Texte.Form.Label.DATUM);
		JLabel lLand = new JLabel(Texte.Form.Label.LAND);
		JLabel lOrt = new JLabel(Texte.Form.Label.ORT);
		JLabel lBenzinart = new JLabel("Benzinart");
		JLabel lLiter = new JLabel("Liter");
		JLabel lPreisPLiter = new JLabel("Preis pro Liter");
		JLabel lKosten = new JLabel("Kosten");
		JLabel lVoll = new JLabel("Tank inhalt");
		JLabel lKmStand = new JLabel("Km Stand");

		spKmStand = new Spinner(setting.getAktuellAuto().getKmAktuell() + 10,
				setting.getAktuellAuto().getKmAktuell(), 999999, 10)
				.getSpinner();
		spLiter = new Spinner(20d, 0.1d, 150d, 1.00).getSpinner();
		spPreisPLiter = new Spinner(1.509d, 0.1d, 3.0d, 0.01).getSpinner();
		spKosten = new Spinner(Berechnung.getKosten((Double) spLiter.getValue(), (Double) spPreisPLiter.getValue()), 0.1d * 0.1d, 150d * 3d, 0.50d).getSpinner();
		cbLand = lModel.getCombobox();
		cbOrt = oModel.getCombobox();
		cbBenzinart = bModel.getCombobox();
		cbTank = tModel.getCombobox();

		Container con = new Container();
		con = frame.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel jpEingaben = new JPanel(new GridLayout(9, 2));
		jpEingaben.add(lDatum);
		jpEingaben.add(datepicker);
		jpEingaben.add(lLand);
		jpEingaben.add(cbLand);
		jpEingaben.add(lOrt);
		jpEingaben.add(cbOrt);
		jpEingaben.add(lBenzinart);
		jpEingaben.add(cbBenzinart);
		jpEingaben.add(lVoll);
		jpEingaben.add(cbTank);
		jpEingaben.add(lKmStand);
		jpEingaben.add(spKmStand);
		jpEingaben.add(lLiter);
		jpEingaben.add(spLiter);
		jpEingaben.add(lPreisPLiter);
		jpEingaben.add(spPreisPLiter);
		jpEingaben.add(lKosten);
		jpEingaben.add(spKosten);

		JPanel jpButton = new JPanel();
		jpButton.add(btnAdd);
		jpButton.add(btnCancel);

		con.add(jpEingaben, BorderLayout.CENTER);
		con.add(jpButton, BorderLayout.SOUTH);

		setListener();

		frame.setTitle(Texte.Form.FensterTitel.ADD_TANKEN);
		frame.pack();
		frame.setVisible(true);
	}
	
	public AddTanken(Tanken tanken, Settings setting){
		this(setting);
		if (tanken ==null){
			return;
		}
		
		tanken = tankenService.search(tanken);
		
		try {
			datepicker.setDate(tanken.getDatum());
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		
		cbLand.getModel().setSelectedItem(tanken.getLand());
		cbOrt.getModel().setSelectedItem(tanken.getOrt());
		cbBenzinart.getModel().setSelectedItem(tanken.getBenzinArt());
		cbTank.getModel().setSelectedItem(tanken.getTank());
		spKmStand.setValue(tanken.getKmStand());
		spLiter.setValue(tanken.getLiter());
		spPreisPLiter.setValue(tanken.getPreisProLiter());
		spKosten.setValue(tanken.getKosten());
		btnAdd.setText("Bearbeiten");
	}

	private void setListener() {
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new ShowGui(setting);
				cancel(frame);

			}
		});

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cbLand.getSelectedIndex() == 0) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie ein Land aus");
					return;
				}

				if (cbOrt.getSelectedIndex() == 0) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}

				if (cbBenzinart.getSelectedIndex() == 0) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie eine Benzinart aus");
					return;
				}

				if (cbTank.getSelectedIndex() == 0) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie einen Tankfüllstand aus");
					return;
				}

				if (Integer.parseInt(spKmStand.getValue().toString()) <= setting
						.getAktuellAuto().getKmAktuell()) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie einen KM Stand der größer als "
									+ setting.getAktuellAuto().getKmAktuell()
									+ " Km ist.");
					return;
				}

				if ((Double) spKosten.getValue() < new Double(0)) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie Kosten, die größer als 0 sind.");
					return;
				}

				/*
				 * Daten werden überprüft
				 */

				int kmStand = Integer.parseInt(spKmStand.getValue().toString());
				Land land = (Land) cbLand.getModel().getSelectedItem();
				Ort ort = (Ort) cbOrt.getModel().getSelectedItem();
				Tank tank = (Tank) cbTank.getModel().getSelectedItem();
				BigDecimal kosten = new BigDecimal(String.valueOf(spKosten
						.getValue()));
				Auto auto = setting.getAktuellAuto();
				Date datum = datepicker.getDate();
				BigDecimal liter = new BigDecimal(String.valueOf(spLiter
						.getValue()));
				BigDecimal preisProLiter = new BigDecimal(String
						.valueOf(spPreisPLiter.getValue()));
				Benzinart benzinArt = (Benzinart) cbBenzinart.getModel()
						.getSelectedItem();

				Tanken t = new Tanken(kmStand, land, ort, tank, kosten, auto,
						datum, liter, preisProLiter, benzinArt);
				
				if (btnAdd.getText() == Texte.Form.Button.HINZUFUEGEN){
					tankenDao = tankenService.addTankfuellung(t);
					setting.getAktuellAuto().setKmAktuell(kmStand);	
				}else{
					tankenDao = tankenService.addTankfuellung(t);
				}
				
			}
		});

		cbLand.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Land item = (Land) event.getItem();
					oModel.updateItems(item);
				}
			}
		});
		spPreisPLiter.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				spKosten.setValue((Double) ((Double) spPreisPLiter.getValue() * (Double) spLiter
						.getValue()));
			}
		});

		spKosten.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				spPreisPLiter.setValue((Double) ((Double) spKosten.getValue() / (Double) spLiter
						.getValue()));
			}
		});
	}

	private void SpinnerEnable(JSpinner spinner, Boolean enable) {
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField()
				.setEditable(enable);
	}

	private class LandModel extends DefaultComboBoxModel<Land> {
		LandDao lDao = new LandDao();
		Vector<Land> vLand = new Vector<Land>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vLand);

		JComboBox<Land> getCombobox() {

			JComboBox<Land> cb = new JComboBox<Land>(model);

			model.addElement(textFormAK.BITTE_AUSWAELEN);
			if (lDao.getLaender().isEmpty()) {
				return cb;
			}
			for (Land l : lDao.getLaender()) {
				model.addElement(l);
			}

			return cb;

		}

	}

	private class OrtModel extends DefaultComboBoxModel<Ort> {
		LandDao lDao = new LandDao();
		OrtDao oDao = new OrtDao();
		Vector<Ort> vOrt = new Vector<Ort>();
		JComboBox<Ort> cb;
		DefaultComboBoxModel model = new DefaultComboBoxModel(vOrt);

		public JComboBox<Ort> getCombobox() {

			cb = new JComboBox<Ort>(model);
			cb.setEditable(false);

			updateItems((Land) lModel.getSelectedItem());

			return cb;
		}

		public void updateItems(Land l) {
			model.removeAllElements();

			if (lModel.getSelectedItem() == textFormAK.BITTE_AUSWAELEN) {
				cb.setEditable(false);
			} else {
				model.addElement(textFormAK.BITTE_AUSWAELEN);
				try {
					for (Ort o : lDao.getOrteByLand(l)) {
						model.addElement(o);
					}
				} catch (NullPointerException npe) {
					model.addElement("keine Orte zu diesem Land");
				}
			}
		}
	}

	private class BenzinartModel extends DefaultComboBoxModel<Benzinart> {

		// BenzinartDAO bDao = new BenzinartDAO();
		Vector<Benzinart> vBenzinart = new Vector<Benzinart>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vBenzinart);

		JComboBox<Benzinart> getCombobox() {
			JComboBox<Benzinart> cb = new JComboBox<Benzinart>(model);
			model.addElement(textFormAK.BITTE_AUSWAELEN);
			for (Benzinart b : setting.getAktuellAuto().getBenzinarten()) {
				model.addElement(b);
			}
			return cb;

		}

	}

	private class TankModel extends DefaultComboBoxModel<Benzinart> {
		TankDAO tDao = new TankDAO();
		Vector<Tank> vTank = new Vector<Tank>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vTank);

		JComboBox<Tank> getCombobox() {

			JComboBox cb = new JComboBox<Tank>(model);

			model.addElement(Texte.Form.AndereKomponennte.BITTE_AUSWAELEN);

			for (Tank t : tDao.getTankList()) {
				model.addElement(t);
			}

			return cb;

		}
	}
}