package de.gw.auto.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.michaelbaranov.microba.calendar.DatePicker;

import de.gw.auto.dao.BenzinartDAO;
import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.OrtDao;
import de.gw.auto.dao.TankDAO;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;
import de.gw.auto.gui.Button.Funktionen;
import de.gw.auto.gui.model.Spinner;

public class AddTanken extends Funktionen {
	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();
	private static final Texte.Error.Titel textError = new Texte.Error.Titel();
	JFrame frame = new JFrame();

	Settings setting;
	LandModel lModel = new LandModel();
	OrtModel oModel = new OrtModel();
	BenzinartModel bModel = new BenzinartModel();
	TankModel tModel = new TankModel();

	public AddTanken(final Settings set) {
		this.setting = set;
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

		// Eingaben
		final DatePicker datepicker = new DatePicker(new Date());
		final JSpinner spKmStand = new Spinner(20000, 0, 999999, 100)
				.getSpinner();
		final JComboBox<Land> cbLand = lModel.getCombobox();
		final JComboBox<Ort> cbOrt = oModel.getCombobox();
		final JComboBox<Benzinart> cbBenzinart = bModel.getCombobox();
		final JComboBox<Tank> cbTank = tModel.getCombobox();
		JTextField tfLiter = new JTextField();
		JTextField tfPreisPLiter = new JTextField();
		JTextField tfKosten = new JTextField();

		// Button
		JButton btnAdd = new JButton(Texte.Form.Button.HINZUFUEGEN);
		JButton btnCancel = new JButton(Texte.Form.Button.ABBRUCH);

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
		jpEingaben.add(tfLiter);
		jpEingaben.add(lPreisPLiter);
		jpEingaben.add(tfPreisPLiter);
		jpEingaben.add(lKosten);
		jpEingaben.add(tfKosten);

		JPanel jpButton = new JPanel();
		jpButton.add(btnAdd);
		jpButton.add(btnCancel);

		con.add(jpEingaben, BorderLayout.CENTER);
		con.add(jpButton, BorderLayout.SOUTH);

		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cancel(frame);

			}
		});

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cbLand.getModel().getSelectedItem() == textFormAK.BITTE_AUSWAELEN) {
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
							"Bitte wählen sie einen Ort aus");
					return;
				}

				if (Integer.parseInt(spKmStand.getValue().toString()) <= set
						.getAktuellAuto().getKmAktuell()) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}
				if (cbOrt.getSelectedIndex() == 0) {
					AllException.messageBox(textError.FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}

			}
		});

		frame.setTitle(Texte.Form.FensterTitel.ADD_TANKEN);
		frame.pack();
		frame.setVisible(true);
	}

	private class LandModel extends DefaultComboBoxModel<Land> {
		final Texte t = new Texte();
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

		public JComboBox getCombobox() {

			cb = new JComboBox(model);
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

		//BenzinartDAO bDao = new BenzinartDAO();
		Vector<Benzinart> vBenzinart = new Vector<Benzinart>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vBenzinart);

		JComboBox<Benzinart> getCombobox() {
			JComboBox cb = new JComboBox(model);
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