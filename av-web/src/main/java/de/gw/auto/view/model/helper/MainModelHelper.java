package de.gw.auto.view.model.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.model.MainViewModel;
import de.gw.auto.view.model.SonstigeAusgabenView;
import de.gw.auto.view.model.TankenViewModel;

@Component
public class MainModelHelper {

	@Autowired
	private TankenModelHelper tankenHelper;

	@Autowired
	private SonstigeAusgabenModelHelper sonstigeAusgabenModelHelper;

	public MainViewModel prepare(RegisteredUser user, int page) {
		TankenViewModel tankenView = new TankenViewModel();
		SonstigeAusgabenView sonstigeAusgabenView = new SonstigeAusgabenView();
		tankenView = this.tankenHelper.prepareTankViewModel(tankenView, user.getCurrentAuto(), page);

		sonstigeAusgabenView = this.sonstigeAusgabenModelHelper.prepare(sonstigeAusgabenView, user, page);

		return new MainViewModel(tankenView, sonstigeAusgabenView);
	}

}
