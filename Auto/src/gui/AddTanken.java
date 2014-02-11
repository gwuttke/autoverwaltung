package gui;

import Exception.AllException;
import Model.Spinner;

import com.michaelbaranov.microba.calendar.DatePicker;

import gui.Button.Funktionen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import domain.Benzinart;
import domain.Land;
import domain.Ort;
import domain.Settings;
import domain.Tank;
import domain.Text;
import dao.BenzinartDAO;
import dao.LandDao;
import dao.OrtDao;
import dao.TankDAO;

public class AddTanken extends Funktionen {

	JFrame frame = new JFrame();

	LandModel lModel = new LandModel();
	OrtModel oModel = new OrtModel();
	BenzinartModel bModel = new BenzinartModel();
	TankModel tModel = new TankModel();
	

	public AddTanken(final Settings set) {
		// Label
		JLabel lDatum = new JLabel(Text.DATUM);
		JLabel lLand = new JLabel(Text.LAND);
		JLabel lOrt = new JLabel(Text.ORT);
		JLabel lBenzinart = new JLabel("Benzinart");
		JLabel lLiter = new JLabel("Liter");
		JLabel lPreisPLiter = new JLabel("Preis pro Liter");
		JLabel lKosten = new JLabel("Kosten");
		JLabel lVoll = new JLabel("Tank inhalt");
		JLabel lKmStand = new JLabel("Km Stand");

		// Eingaben
		final DatePicker datepicker = new DatePicker(new Date());
		final JSpinner spKmStand = new Spinner(20000, 0, 999999, 100).getSpinner();
		final JComboBox cbLand = lModel.getCombobox();
		final JComboBox cbOrt = oModel.getCombobox();
		final JComboBox cbBenzinart = bModel.getCombobox();
		final JComboBox cbTank = tModel.getCombobox();
		JTextField tfLiter = new JTextField();
		JTextField tfPreisPLiter = new JTextField();
		JTextField tfKosten = new JTextField();

		// Button
		JButton btnAdd = new JButton(Text.BUTTON_HINZUFUEGEN);
		JButton btnCancel = new JButton(Text.BUTTON_ABBRUCH);

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

			@Override
			public void actionPerformed(ActionEvent e) {
				cancel(frame);

			}
		});

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbLand.getModel().getSelectedItem() == Text.BITTE_AUSWAELEN) {
					AllException.messageBox(Text.ERROR_FALSCHE_EINGABE,
							"Bitte wählen sie ein Land aus");
					return;
				}
				if (cbOrt.getSelectedIndex() == 0) {
					AllException.messageBox(Text.ERROR_FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}

				if (cbBenzinart.getSelectedIndex() == 0) {
					AllException.messageBox(Text.ERROR_FALSCHE_EINGABE,
							"Bitte wählen sie eine Benzinart aus");
					return;
				}

				if (cbTank.getSelectedIndex() == 0) {
					AllException.messageBox(Text.ERROR_FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}
				
				if ((int)spKmStand.getValue() <= set.getAuto().getKmAktuell()) {
					AllException.messageBox(Text.ERROR_FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}
				if (cbOrt.getSelectedIndex() == 0) {
					AllException.messageBox(Text.ERROR_FALSCHE_EINGABE,
							"Bitte wählen sie einen Ort aus");
					return;
				}
				

			}
		});

		frame.setTitle(Text.ADD_TANKEN);
		frame.pack();
		frame.setVisible(true);
	}

	private class LandModel extends DefaultComboBoxModel<Land> {
		final Text t = new Text();
		LandDao lDao = new LandDao();
		Vector<Land> vLand = new Vector<Land>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vLand);

		JComboBox<Land> getCombobox() {

			JComboBox cb = new JComboBox(model);

			model.addElement(t.BITTE_AUSWAELEN);

			for (Land l : lDao.getLaender()) {
				model.addElement(new Land(l));
			}

			return cb;

		}

	}

	private class OrtModel extends DefaultComboBoxModel<Ort> {
		final Text t = new Text();
		OrtDao oDao = new OrtDao();
		Vector<Ort> vOrt = new Vector<Ort>();
		JComboBox cb;
		DefaultComboBoxModel model = new DefaultComboBoxModel(vOrt);

		public JComboBox getCombobox() {

			cb = new JComboBox(model);
			cb.setEditable(false);

			updateItems((Land) lModel.getSelectedItem());

			return cb;
		}

		public void updateItems(Land l) {
			model.removeAllElements();

			if (lModel.getSelectedItem() == t.BITTE_AUSWAELEN) {
				cb.setEditable(false);
			} else {
				model.addElement(t.BITTE_AUSWAELEN);
				for (Ort o : oDao.getOrteInLand(l)) {
					model.addElement(o);
				}
			}
		}
	}

	private class BenzinartModel extends DefaultComboBoxModel<Benzinart> {
		final Text t = new Text();
		BenzinartDAO bDao = new BenzinartDAO();
		Vector<Benzinart> vBenzinart = new Vector<Benzinart>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vBenzinart);

		JComboBox<Land> getCombobox() {
			JComboBox cb = new JComboBox(model);
			model.addElement(t.BITTE_AUSWAELEN);
			for (Benzinart b : bDao.getBenzinartList()) {
				model.addElement(new Benzinart(b.getName(), b.getId()));
			}
			return cb;

		}

	}

	private class TankModel extends DefaultComboBoxModel<Benzinart> {
		final Text t = new Text();
		TankDAO tDao = new TankDAO();
		Vector<Tank> vTank = new Vector<Tank>();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vTank);

		JComboBox<Tank> getCombobox() {

			JComboBox cb = new JComboBox<Tank>(model);

			model.addElement(t.BITTE_AUSWAELEN);

			for (Tank t : tDao.getTankList()) {
				model.addElement(new Tank(t.getId(), t.getBeschreibung()));
			}

			return cb;

		}
	}
}