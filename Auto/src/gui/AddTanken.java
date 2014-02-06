package gui;

import Model.Spinner;

import com.michaelbaranov.microba.calendar.DatePicker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import domain.Benzinart;
import domain.Land;
import domain.Ort;
import domain.Tank;
import domain.Text;
import dao.BenzinartDAO;
import dao.LandDao;
import dao.OrtDao;
import dao.TankDAO;

public class AddTanken extends JFrame {

	LandModel lModel = new LandModel();
	OrtModel oModel = new OrtModel();
	BenzinartModel bModel = new BenzinartModel();
	TankModel tModel = new TankModel();

	public AddTanken() {
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
		DatePicker datepicker = new DatePicker(new Date());
		JSpinner spKmStand = new Spinner(20000, 0, 999999, 100).getSpinner();
		JComboBox cbLand = lModel.getCombobox();
		JComboBox cbOrt = oModel.getCombobox();
		JComboBox cbBenzinart = bModel.getCombobox();
		JComboBox cbTank = tModel.getCombobox();
		JTextField tfLiter = new JTextField();
		JTextField tfPreisPLiter = new JTextField();
		JTextField tfKosten = new JTextField();
		
		//Button
		JButton Add = new JButton()

		Container con = new Container();
		con = getContentPane();
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

		con.add(jpEingaben, BorderLayout.CENTER);
		
		setTitle(Text.ADD_TANKEN);
		pack();
		setVisible(true);

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
		DefaultComboBoxModel model = new DefaultComboBoxModel (vOrt);

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
				model.addElement(new Tank(t.getId(), t.getBeschreibung()) );
			}

			return cb;

		}
	}
}