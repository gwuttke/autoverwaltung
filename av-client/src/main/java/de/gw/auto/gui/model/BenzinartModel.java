package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.springframework.stereotype.Component;


import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Texte;

/**
 * Methode @see init() muss aufgerufen werden
 * @author Georg
 *
 */

@Component
public class BenzinartModel {
	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();

	

	Vector<Benzinart> vBenzinart = new Vector<Benzinart>();
	DefaultComboBoxModel model = new DefaultComboBoxModel(vBenzinart);

	public BenzinartModel() {
	}

	public JComboBox<Benzinart> getCombobox() {
		JComboBox<Benzinart> cb = new JComboBox<Benzinart>(model);
		model.addElement(textFormAK.BITTE_AUSWAELEN);
		for (Benzinart b : setting.getAktuellAuto().getBenzinarten()) {
			model.addElement(b);
		}
		return cb;

	}

}