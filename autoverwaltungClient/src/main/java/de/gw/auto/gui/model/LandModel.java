package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.OrtDao;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Texte;

@Component
public class LandModel {
	
	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();

	
	@Autowired
	LandDao lDao;
	
	Vector<Land> vLand = new Vector<Land>();
	DefaultComboBoxModel model = new DefaultComboBoxModel(vLand);

	protected LandModel(){}
	
	public JComboBox<Land> getCombobox() {

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
	public DefaultComboBoxModel getModel() {
		return model;
	}

}
