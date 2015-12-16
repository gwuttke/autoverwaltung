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
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import de.gw.auto.dao.AutoDAO;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.AutoModel;
import de.gw.auto.view.model.AutoModelShow;
import de.gw.auto.view.model.helper.NewAutoModelHelper;

@Controller
@RequestMapping("user/auto")
public class AutoController extends ControllerHelper {
	@Autowired
	NewAutoModelHelper autoHelper;

	@Autowired
	private AutoDAO autoDAO;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareNewAuto(Principal principal, AutoModel autoModel,
			Model model) {
		RegisteredUser user = giveRegisteredUser(principal);
		autoModel = autoHelper.prepareAutoModel(autoModel);
		model.addAttribute("autoModel", autoModel);
		return "auto/new :: newAutoModal";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAuto(Principal principal, @Valid AutoModel autoModel,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "auto/new :: newAutoModal";
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

	@RequestMapping(value = "/updateCurrent", method = RequestMethod.POST)
	public String updateCurrentAuto(Principal principal,
			AutoModelShow autoModel, HttpServletRequest request, BindingResult bindingResult, Model model) {
		RegisteredUser user = giveRegisteredUser(principal);
		Auto a = autoDAO.findById(autoModel.getId());
		if(user.getAutos().contains(a)){
			user.setCurrentAuto(a);
			return "redirect:/user/tanken/show";
		}
		bindingResult.reject("no.Car", String.format(
				"Es gibt kein Auto mit dem Kennzeichen: %s .",
				autoModel.getKfz()));
		 String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
	}
}
