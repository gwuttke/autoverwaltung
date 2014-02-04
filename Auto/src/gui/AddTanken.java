package gui;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ListDataListener;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import domain.Benzinart;
import domain.Land;
import domain.Ort;
import domain.Text;
import dao.BenzinartDAO;
import dao.LandDao;
import dao.OrtDao;

public class AddTanken extends JFrame {

	private static Text t = new Text();
	LandModel lModel = new LandModel();
	OrtModel oModel = new OrtModel();

	public AddTanken() {
		// Label
		// JLabel lDatum = new JLabel(t.DATUM);
		JLabel lLand = new JLabel("Land");
		JLabel lOrt = new JLabel("Ort");
		JLabel lBenzinart = new JLabel("Benzinart");
		JLabel lLiter = new JLabel("Liter");
		JLabel lPreisPLiter = new JLabel("Preis pro Liter");
		JLabel lKosten = new JLabel("Kosten");
		JLabel lVoll = new JLabel("Tank inhalt");
		JLabel lKmStand = new JLabel("Km Stand");

		// Eingaben
		// JDatePicker
		JComboBox cbLand = lModel.getCombobox();
		JComboBox cbOrt = oModel.getCb();
		JComboBox cbBenzinart;
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
		JComboBox cb = getCombobox();
		DefaultComboBoxModel model = new DefaultComboBoxModel(vOrt);

		public JComboBox getCb() {
			return cb;
		}
		public JComboBox getCombobox() {

			JComboBox cb = new JComboBox(model);
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
	
	private class BenzinartModel extends DefaultComboBoxModel<Benzinart>{
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
	private class TankModel extends DefaultComboBoxModel<Benzinart>{
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
}