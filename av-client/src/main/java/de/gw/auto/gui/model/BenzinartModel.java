package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.springframework.stereotype.Component;


import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Texte;

/**
 * Methode @see init() muss aufgerufen werden
 * @author Georg
 *
 */

@Component
public class BenzinartModel {
	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();

	

	Vector<Kraftstoffsorte> vBenzinart = new Vector<Kraftstoffsorte>();
	DefaultComboBoxModel model = new DefaultComboBoxModel(vBenzinart);

	public BenzinartModel() {
	}

	public JComboBox<Kraftstoffsorte> getCombobox() {
		JComboBox<Kraftstoffsorte> cb = new JComboBox<Kraftstoffsorte>(model);
		model.addElement(textFormAK.BITTE_AUSWAELEN);
		for (Kraftstoffsorte b : setting.getAktuellAuto().getBenzinarten()) {
			model.addElement(b);
		}
		return cb;

	}

}