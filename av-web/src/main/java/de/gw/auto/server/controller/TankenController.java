package de.gw.auto.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.gw.auto.Constans;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.NewTanken;
import de.gw.auto.view.model.TankenViewModel;
import de.gw.auto.view.model.helper.NewTankenModelHelper;
import de.gw.auto.view.model.helper.TankenModelHelper;

@Controller
@RequestMapping("user/tanken")
public class TankenController extends ControllerHelper{
	@Autowired
	private TankenModelHelper tankenHelper;

	@Autowired
	private NewTankenModelHelper newTankenModelHelper;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(TankenViewModel tankenView, Model model,
			HttpServletRequest request) {
		RegisteredUser user = giveRegisteredUser(request);
		if (user == null) {
			return ViewName.REDIRECT_LOGIN;
		}
		if (user.getCurrentAuto() == null) {
			return "redirect:/user/auto/new";
		}
		if (user.getCurrentAuto().getTankfuellungen().isEmpty()) {
			return "redirect:/user/tanken/new";
		}
		this.tankenHelper.prepareTankViewModel(tankenView, user.getCurrentAuto());
		model.addAttribute(tankenView);
		return "userMainPage";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareNew(NewTanken newTanken, Model model,
			HttpServletRequest request) {
		RegisteredUser user = giveRegisteredUser(request);
		if (user == null) {
			return ViewName.REDIRECT_LOGIN;
		}
		newTankenModelHelper.prepareNewTankenModel(newTanken);
		model.addAttribute(newTanken);
		return "tanken/new";
	}
}
