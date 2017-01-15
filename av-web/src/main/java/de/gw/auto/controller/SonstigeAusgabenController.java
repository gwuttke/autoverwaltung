package de.gw.auto.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.service.implementation.SonstigeAusgabenService;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.HeaderModel;
import de.gw.auto.view.model.NewSonstigeAusgaben;
import de.gw.auto.view.model.NewTanken;
import de.gw.auto.view.model.SonstigeAusgabenView;
import de.gw.auto.view.model.helper.SonstigeAusgabenModelHelper;

@Controller
@RequestMapping("user/sonstigeAusgaben")
public class SonstigeAusgabenController extends ControllerHelper {

	@Autowired
	private SonstigeAusgabenModelHelper sonstigeAusgabenModelHelper;

	@Autowired
	private SonstigeAusgabenService sonstigeAusgabenService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid NewSonstigeAusgaben newSonstigeAusgaben, BindingResult bindingResult, Model model,
			Principal principal, RedirectAttributes redirectAttributes) {
		RegisteredUser user = giveRegisteredUser(principal);
		if (bindingResult.hasErrors()) {
			sonstigeAusgabenModelHelper.prepare(newSonstigeAusgaben, user.getCurrentAuto());
			model.addAttribute(newSonstigeAusgaben);
			return "sonstigeAusgaben/new";
		}

		SonstigeAusgaben sa = new SonstigeAusgaben(newSonstigeAusgaben.getDatum(), newSonstigeAusgaben.getKmStand(),
				newSonstigeAusgaben.getKommentar(), newSonstigeAusgaben.getKosten(), user.getCurrentAuto());

		sonstigeAusgabenService.save(sa, user);
		return ViewName.REDIRECT_ROOT_USER_SHOW;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(@ModelAttribute("SonstigeAusgabenView") SonstigeAusgabenView sonstigeAusgabenView,
			Principal principal, Model model, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		RegisteredUser user = giveRegisteredUser(principal);
		if (user.getCurrentAuto() == null) {
			return "redirect:/user/auto/new";
		}
		if (user.getCurrentAuto().getSonstigeAusgaben().isEmpty()) {
			return "redirect:/user/sonstigeAusgaben/new";
		}
		HeaderModel headerModel = new HeaderModel(user);
		sonstigeAusgabenView = this.sonstigeAusgabenModelHelper.prepare(sonstigeAusgabenView, user, page);

		model.addAttribute("headerModel", headerModel);
		model.addAttribute("sonstigeAusgabenView", sonstigeAusgabenView);
		model.addAttribute("newTanken", new NewTanken());
		return "userMainPage";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareNew(NewSonstigeAusgaben newSonstigeAusgaben, BindingResult bindingResult, Model model,
			Principal principal) {
		RegisteredUser user = giveRegisteredUser(principal);
		sonstigeAusgabenModelHelper.prepare(newSonstigeAusgaben, user.getCurrentAuto());
		model.addAttribute(newSonstigeAusgaben);
		return "sonstigeAusgaben/new";
	}

}
