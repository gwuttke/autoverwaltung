package de.gw.auto.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.gw.auto.Constans;
import de.gw.auto.service.I_BenutzerService;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.LoginModel;
import de.gw.auto.view.model.RegistrationModel;

@Controller
public class LoginController {
	@Autowired
	I_BenutzerService benutzerService;

	@RequestMapping(value = ViewName.DEFAULT_ROOT)
	public String prepareLogin(LoginModel loginModel) {
		return ViewName.LOGIN;
	}

	@RequestMapping(value = ViewName.LOGOUT)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(Constans.SESSION_BENUTZER, null);
		return ViewName.DEFAULT_ROOT;
	}

	@RequestMapping(value = ViewName.LOGIN, method = RequestMethod.POST)
	public String login(@Validated LoginModel loginModel,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ViewName.REDIRECT_LOGIN;
		}
		RegisteredUser registedUser = null;
		try {
			registedUser = benutzerService.login(loginModel.getUsername(),
					loginModel.getEmail(), loginModel.getPasswort());
			if (registedUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute(Constans.SESSION_BENUTZER, registedUser);
			}
		} catch (UsernameNotFoundException e) {
			loginModel.setStatus(e.getMessage());
			return ViewName.REDIRECT_ROOT;
		}
		return ViewName.REDIRECT_USER_MAIN_PAGE;
	}

	@RequestMapping(value = "/" + ViewName.REGISTER, method = RequestMethod.GET)
	public String register(RegistrationModel registrationModel) {
		return ViewName.REGISTER;
	}

	@RequestMapping(value = ViewName.REGISTRATION, method = RequestMethod.POST)
	public String registration(@Valid RegistrationModel registrationModel,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ViewName.REGISTER;
		}
		if (!registrationModel.getPasswort().equals(
				registrationModel.getWiederholungPasswort())) {
			bindingResult.rejectValue("wiederholungPasswort",
					"Die Passwörter stimmen nicht überein");
		}
		try {
			benutzerService.registry(registrationModel.getName(),
					registrationModel.getVorname(),
					registrationModel.getBenutzername(),
					registrationModel.getPasswort(),
					registrationModel.geteMail());
			return ViewName.REDIRECT_ROOT;
		} catch (Exception e) {
			return ViewName.REGISTER;
		}
	}

	@RequestMapping(value = ViewName.ERROR_LOGIN, method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return ViewName.LOGIN;
	}
}
