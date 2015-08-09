package de.gw.auto.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.gw.auto.Constans;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.repository.KraftstoffsorteRepository;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.service.implementation.StammdatenService;
import de.gw.auto.service.implementation.TankenService;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.NewTanken;
import de.gw.auto.view.model.TankenViewModel;
import de.gw.auto.view.model.helper.NewTankenModelHelper;
import de.gw.auto.view.model.helper.TankenModelHelper;

@Controller
@RequestMapping("user/tanken")
public class TankenController extends ControllerHelper {
	@Autowired
	private TankenModelHelper tankenHelper;

	@Autowired
	private TankenService tankenService;

	@Autowired
	private StammdatenService stammdatenService;

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
		tankenView = this.tankenHelper.prepareTankViewModel(tankenView,
				user.getCurrentAuto());
		model.addAttribute("tankenView", tankenView);
		return "userMainPage";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareNew(NewTanken newTanken, Model model,
			HttpServletRequest request) {
		RegisteredUser user = giveRegisteredUser(request);
		if (user == null) {
			return ViewName.REDIRECT_LOGIN;
		}
		newTanken = newTankenModelHelper.prepareNewTankenModel(newTanken,
				user.getCurrentAuto());
		model.addAttribute(newTanken);
		return "tanken/new";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTankfuellung(@Valid NewTanken newTanken,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "redirect:/user/tanken/new";
		}
		RegisteredUser user = giveRegisteredUser(request);
		if (user == null) {
			return ViewName.REDIRECT_LOGIN;
		}
		Kraftstoffsorte kraftstoffsorte = stammdatenService
				.getKraftstoffsorte(newTanken.getUserKraftstoffsorte());
		Land l = stammdatenService.getLand(newTanken.getLandId());
		Tank t = stammdatenService.getTankstand(newTanken.getTankId());
		Ort o = l.getOrt(newTanken.getOrtId());
		Tanken tanken = new Tanken(newTanken.getKmStand(), l, o, t,
				newTanken.getKosten(), user.getCurrentAuto(),
				newTanken.getDatum(), newTanken.getLiter(),
				newTanken.getPreisProLiter(), kraftstoffsorte);
		tankenService.save(tanken, user);
		return ViewName.REDIRECT_USER_MAIN_PAGE;
	}
}
