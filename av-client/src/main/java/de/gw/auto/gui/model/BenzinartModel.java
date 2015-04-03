package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.springframework.stereotype.Component;

import de.gw.auto.dao.Settings;
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

	private Settings setting;

	Vector<Benzinart> vBenzinart = new Vector<Benzinart>();
	DefaultComboBoxModel model = new DefaultComboBoxModel(vBenzinart);

	public BenzinartModel() {
	}

	public void init(Settings setting) {
		this.setting = setting;
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