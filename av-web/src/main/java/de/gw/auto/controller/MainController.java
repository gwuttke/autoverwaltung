package de.gw.auto.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.model.HeaderModel;
import de.gw.auto.view.model.MainViewModel;
import de.gw.auto.view.model.TankenViewModel;
import de.gw.auto.view.model.helper.MainModelHelper;

@Controller
@RequestMapping("user")
@Secured("ROLE_USER")
public class MainController extends ControllerHelper{
	
@Autowired
private MainModelHelper mainModelHelper;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(
			@ModelAttribute("tankenView") TankenViewModel tankenView,
			Principal principal, Model model, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		RegisteredUser user = giveRegisteredUser(principal);
		if (user.getCurrentAuto() == null) {
			return "redirect:/user/auto/new";
		}
		MainViewModel mainViewModel = mainModelHelper.prepare(user, page);
		HeaderModel headerModel = new HeaderModel(user);
		
		model.addAttribute("headerModel", headerModel);
		model.addAttribute("tankenView", mainViewModel.getTankenViewModel());
		model.addAttribute("sonstigeAusgabenView", mainViewModel.getSonstigeAusgabenView());
		return "userMainPage";
	}
	
}
