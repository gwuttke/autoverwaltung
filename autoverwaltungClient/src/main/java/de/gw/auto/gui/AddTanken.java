package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.michaelbaranov.microba.calendar.DatePicker;

import de.gw.auto.dao.Berechnung;
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
import de.gw.auto.gui.model.BenzinartModel;
import de.gw.auto.gui.model.LandModel;
import de.gw.auto.gui.model.OrtModel;
import de.gw.auto.gui.model.Spinner;
import de.gw.auto.gui.model.TankModel;
import de.gw.auto.service.TankenService;

@Controller
public class AddTanken extends Funktionen implements ComponentListener {

	@Autowired
	private ShowGui showGui;

	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();
	private static final Texte.Error.Titel textError = new Texte.Error.Titel();
	private JFrame frame = new JFrame();

	private Settings setting;
	private Tanken oldTanken;
	@Autowired
	private LandModel lModel;
	
	@Autowired
	private OrtModel oModel;
	
	@Autowired
	private BenzinartModel bModel;
	
	@Autowired
	private TankModel tModel;
	
	@Autowired
	private TankenService tankenService;

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

	// Label
	private JLabel lDatum = new JLabel(Texte.Form.Label.DATUM);
	private JLabel lLand = new JLabel(Texte.Form.Label.LAND);
	private JLabel lOrt = new JLabel(Texte.Form.Label.ORT);
	private JLabel lBenzinart = new JLabel("Benzinart:");
	private JLabel lLiter = new JLabel("Liter:");
	private JLabel lPreisPLiter = new JLabel("Preis pro Liter:");
	private JLabel lKosten = new JLabel("Kosten:");
	private JLabel lVoll = new JLabel("Tank inhalt:");
	private JLabel lKmStand = new JLabel("Km Stand:");

	// Button
	private JButton btnAdd = new JButton("Hinzufügen");
	private JButton btnCancel = new JButton(Texte.Form.Button.ABBRUCH);

	protected AddTanken(){}

	public void init(final Settings set) {
		this.setting = set;
		tankenService.init(setting);
bModel.init(setting);
		showGui();
	}
	

	public void showGui() {
		spKmStand = new Spinner(setting.getAktuellAuto().getKmAktuell() + 10,
				setting.getAktuellAuto().getKmAktuell(), 999999, 10)
				.getSpinner();
		spLiter = new Spinner(20d, 0.1d, 150d, 1.00).getSpinner();
		spPreisPLiter = new Spinner(1.509d, 0.1d, 3.0d, 0.01).getSpinner();
		spKosten = new Spinner(Double.valueOf(Berechnung.getKosten(
				(Double) spLiter.getValue(), (Double) spPreisPLiter.getValue())
				.toString()), 0.1d * 0.1d, 150d * 3d, 0.50d).getSpinner();

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

	public AddTanken(Tanken tanken, Settings setting) {
		this.init(setting);
		if (tanken == null) {
			return;
		}

		tanken = tankenService.search(tanken);
		this.oldTanken = tanken;

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
		frame.addComponentListener(this);
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				showGui.setSetting(setting);
				showGui.showGui();
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

				if (getKosten() < 0d) {
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

				if (btnAdd.getText() == Texte.Form.Button.HINZUFUEGEN) {
					if (Integer.parseInt(spKmStand.getValue().toString()) <= setting
							.getAktuellAuto().getKmAktuell()) {
						AllException.messageBox(textError.FALSCHE_EINGABE,
								"Bitte wählen sie einen KM Stand der größer als "
										+ setting.getAktuellAuto()
												.getKmAktuell() + " Km ist.");
						return;
					}
					Tanken t = new Tanken(kmStand, land, ort, tank, kosten,
							auto, datum, liter, preisProLiter, benzinArt);

					tankenService.addTankfuellung(t);
					setting.getAktuellAuto().setKmAktuell(kmStand);
				} else {
					oldTanken.setKmStand(kmStand);
					oldTanken.setLand(land);
					oldTanken.setOrt(ort);
					oldTanken.setTank(tank);
					oldTanken.setKosten(kosten);
					oldTanken.setDatum(datum);
					oldTanken.setPreisProLiter(preisProLiter);
					oldTanken.setBenzinArt(benzinArt);
					tankenService.updateTankfuellung(oldTanken);
				}
				showGui.setSetting(setting);
				showGui.showGui();
				;
				frame.dispose();

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

				spKosten.setValue(Berechnung.getKosten(getLiter(),
						getPreisProLiter()));
			}
		});

		spKosten.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				spPreisPLiter.setValue(Berechnung.getPreisProLiter(getLiter(),
						getKosten()));
			}
		});

		spLiter.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				spKosten.setValue(Berechnung.getKosten(getLiter(),
						getPreisProLiter()));

			}
		});
	}

	private void SpinnerEnable(JSpinner spinner, Boolean enable) {
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField()
				.setEditable(enable);
	}

	private Double getPreisProLiter() {
		return Double.valueOf(spPreisPLiter.getValue().toString());
	}

	private Double getLiter() {
		return Double.valueOf(spLiter.getValue().toString());
	}

	private Double getKosten() {
		return Double.valueOf(spKosten.getValue().toString());
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent cEvent) {
		lDatum.setFont(Berechnung.updateFont(lDatum, lDatum.getText(),
				Font.BOLD));
		lLand.setFont(Berechnung.updateFont(lLand, lLand.getText(), Font.BOLD));
		lOrt.setFont(Berechnung.updateFont(lOrt, lOrt.getText(), Font.BOLD));
		lBenzinart.setFont(Berechnung.updateFont(lBenzinart,
				lBenzinart.getText(), Font.BOLD));
		lVoll.setFont(Berechnung.updateFont(lVoll, lVoll.getText(), Font.BOLD));
		lKmStand.setFont(Berechnung.updateFont(lKmStand, lKmStand.getText(),
				Font.BOLD));
		lLiter.setFont(Berechnung.updateFont(lLiter, lLiter.getText(),
				Font.BOLD));
		lPreisPLiter.setFont(Berechnung.updateFont(lPreisPLiter,
				lPreisPLiter.getText(), Font.BOLD));
		lKosten.setFont(Berechnung.updateFont(lKosten, lKosten.getText(),
				Font.BOLD));
		datepicker.setFont(Berechnung.updateFont(datepicker, datepicker
				.getDate().toString(), Font.BOLD));

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}
}