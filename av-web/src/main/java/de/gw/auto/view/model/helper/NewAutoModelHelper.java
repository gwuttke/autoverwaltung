package de.gw.auto.view.model.helper;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.service.implementation.StammdatenService;
import de.gw.auto.view.model.AutoModel;

@Component
public class NewAutoModelHelper {
	@Autowired
	private StammdatenService stammdatenService;

	public AutoModel prepareAutoModel(AutoModel autoModel) {
		autoModel.setKraftstoffarten(stammdatenService.getKrftstoffe());
		return autoModel;
	}
}
