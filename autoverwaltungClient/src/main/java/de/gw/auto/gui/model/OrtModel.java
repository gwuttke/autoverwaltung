package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.OrtDao;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Texte;

@Service
public class OrtModel {
	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();

	@Autowired
	LandModel lModel;

	@Autowired
	LandDao lDao;
	@Autowired
	OrtDao oDao;
	Vector<Ort> vOrt = new Vector<Ort>();
	JComboBox<Ort> cb;
	DefaultComboBoxModel model = new DefaultComboBoxModel(vOrt);

	protected OrtModel() {
	}

	public JComboBox<Ort> getCombobox() {

		cb = new JComboBox<Ort>(model);
		cb.setEditable(false);

		if (!(lModel.getModel().getSelectedItem() == textFormAK.BITTE_AUSWAELEN)) {
			updateItems((Land) lModel.getModel().getSelectedItem());
		} else {
			cb.setEditable(false);
		}
		return cb;
	}

	public void updateItems(Land l) {
		model.removeAllElements();

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
