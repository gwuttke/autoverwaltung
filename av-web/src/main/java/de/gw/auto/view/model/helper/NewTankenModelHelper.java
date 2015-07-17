package de.gw.auto.view.model.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.gw.auto.service.implementation.StammdatenService;
import de.gw.auto.view.model.NewTanken;

@Component
public class NewTankenModelHelper {

	@Autowired
	private StammdatenService stammdatenService;

	public NewTanken prepareNewTankenModel(final NewTanken newTanken) {
		newTanken.setLaender(stammdatenService.getLaender());
		newTanken.setKraftstoffarten(stammdatenService.getKrftstoffarten());
		newTanken.setFuellstaende(stammdatenService.getTankstaende());
		return newTanken;
	}
}
