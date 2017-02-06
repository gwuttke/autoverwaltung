package de.gw.auto.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.gw.auto.dao.AutoDAO;
import de.gw.auto.dao.BenutzerDAO;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.AutoModel;
import de.gw.auto.view.model.helper.NewAutoModelHelper;

@Controller
@RequestMapping("user/auto")
public class AutoController extends ControllerHelper {
	@Autowired
	NewAutoModelHelper autoHelper;
	
	@Autowired
	BenutzerDAO benutzerDAO;

	@Autowired
	private AutoDAO autoDAO;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareNewAuto(Principal principal, AutoModel autoModel,
			Model model) {
		RegisteredUser user = giveRegisteredUser(principal);
		autoModel = autoHelper.prepareAutoModel(autoModel);
		model.addAttribute("autoModel", autoModel);
		return "auto/new";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAuto(Principal principal, @Valid AutoModel autoModel,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "auto/new";
		}
		RegisteredUser user = giveRegisteredUser(principal);
		List<Benutzer> users = new ArrayList<Benutzer>();
		users.add(user);
		Auto auto = new Auto(autoModel.getKfz(), autoModel.getKmKauf(),
				autoModel.getKmStart(), autoModel.getKauf(),
				autoModel.getErstZulassung(), autoModel.getUserKraftstoffart(),
				autoModel.getKmAktuell(), users);
		autoDAO.save(user, auto);
		return ViewName.REDIRECT_USER_MAIN_PAGE;
	}

	@RequestMapping(value = "/updateCurrent",params={"autoId"}, method = RequestMethod.GET)
	public String updateCurrentAuto(@RequestParam("autoId") int autoId,
			HttpServletRequest request, Principal principal) {

			RegisteredUser user = giveRegisteredUser(principal);
			Auto a = autoDAO.findById(autoId);
			if (user.getAutos().contains(a)) {
				user.setCurrentAuto(a);
				user = benutzerDAO.save(user);
				return "redirect:/user/show";
			}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
