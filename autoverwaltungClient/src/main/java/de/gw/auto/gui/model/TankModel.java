package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.gw.auto.dao.TankDAO;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Texte;
import de.gw.auto.service.TankenService;

@Component
public class TankModel {
	@Autowired
	TankDAO tankDAO;

	private static final Texte.Form.AndereKomponennte textFormAK = new Texte.Form.AndereKomponennte();

	Vector<Tank> vTank = new Vector<Tank>();
	DefaultComboBoxModel model = new DefaultComboBoxModel(vTank);

	protected TankModel() {
	}

	public JComboBox<Tank> getCombobox() {

		JComboBox cb = new JComboBox<Tank>(model);

		model.addElement(textFormAK.BITTE_AUSWAELEN);

		for (Tank t : tankDAO.getTankList()) {
			model.addElement(t);
		}

		return cb;

	}
}