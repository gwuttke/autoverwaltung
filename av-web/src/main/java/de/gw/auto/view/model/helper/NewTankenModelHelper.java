package de.gw.auto.view.model.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.gw.auto.domain.Auto;
import de.gw.auto.service.implementation.StammdatenService;
import de.gw.auto.view.model.NewTanken;
import de.gw.auto.view.model.TankenViewModel;

@Component
public class NewTankenModelHelper {

	@Autowired
	private StammdatenService stammdatenService;

	public void prepareNewTankenModel(NewTanken newTanken, Auto auto) {
		if(newTanken == null){
			 newTanken = new NewTanken();
		}
		
		newTanken.setAuto(auto);
		newTanken.setLaender(stammdatenService.getLaender());
		newTanken.setKraftstoffsorten(stammdatenService.getKraftstoffsorten(auto.getKraftstoff()));
		newTanken.setFuellstaende(stammdatenService.getTankstaende());
		newTanken.setLaender(stammdatenService.getLaender());
	}
}
