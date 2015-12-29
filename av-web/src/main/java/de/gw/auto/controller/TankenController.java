package de.gw.auto.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.repository.UserRepository;
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
@Secured("ROLE_USER")
public class TankenController extends ControllerHelper {
	@Autowired
	private TankenModelHelper tankenHelper;

	@Autowired
	private TankenService tankenService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StammdatenService stammdatenService;

	@Autowired
	private NewTankenModelHelper newTankenModelHelper;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(
			@ModelAttribute("tankenView") TankenViewModel tankenView, Principal principal,
			Model model, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		RegisteredUser user = giveRegisteredUser(principal);
				
		if (user.getCurrentAuto() == null) {
			return "redirect:/user/auto/new";
		}
		if (user.getCurrentAuto().getTankfuellungen().isEmpty()) {
			return "redirect:/user/tanken/new";
		}
		tankenView = this.tankenHelper.prepareTankViewModel(tankenView,
				user.getCurrentAuto(), page);
		model.addAttribute("tankenView", tankenView);
		model.addAttribute("newTanken", new NewTanken());
		return "userMainPage";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String prepareNew(
			@ModelAttribute("tankenView") TankenViewModel tankenView,
			Model model, Principal principal) {
		RegisteredUser user = giveRegisteredUser(principal);
		if (user == null) {
			return ViewName.REDIRECT_LOGIN;
		}
		newTankenModelHelper.prepareNewTankenModel(tankenView,
				user.getCurrentAuto());
		model.addAttribute(tankenView);
		return "tanken/new :: newTankenModal";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTankfuellung(
			@ModelAttribute("tankenView") TankenViewModel tankenView,
			BindingResult bindingResult, Model model, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "redirect:/user/tanken/new";
		}
		RegisteredUser user = giveRegisteredUser(principal);
		if (user == null) {
			return ViewName.REDIRECT_LOGIN;
		}
		NewTanken newTanken = tankenView.getNewTanken();
		Kraftstoffsorte kraftstoffsorte = stammdatenService
				.getKraftstoffsorte(newTanken.getUserKraftstoffsorte());
		Land l = stammdatenService.getLand(newTanken.getLandId());
		Tank t = stammdatenService.getTankstand(newTanken.getTankId());
		Ort o = null;
		if(newTanken.getOrtId() > 0){
			o = l.getOrt(newTanken.getOrtId());	
		}
		
		Tanken tanken = new Tanken(newTanken.getKmStand(), l, o, t,
				newTanken.getKosten(), user.getCurrentAuto(),
				newTanken.getDatum(), newTanken.getLiter(),
				newTanken.getPreisProLiter(), kraftstoffsorte);
		tankenService.save(tanken, user);
		return ViewName.REDIRECT_USER_MAIN_PAGE;
	}
	
	@RequestMapping(value="downloadEmptyTanken",method=RequestMethod.GET, produces="application/pdf")
	public ResponseEntity<byte[]> downloadEmptyTanken(Model model,Principal principal) throws DocumentException{
		RegisteredUser user = giveRegisteredUser(principal);
		if (user == null) {
			return null;
		}
		return new ResponseEntity<byte[]>(tankenHelper.createEmptyPDF(user
				.getCurrentAuto()), HttpStatus.CREATED);
	}

	@RequestMapping(value="/downloadTanken" , method=RequestMethod.GET,produces="application/pdf")
	public ResponseEntity<byte[]> download(
			@ModelAttribute("tankenView") TankenViewModel tankenView,
			BindingResult bindingResult, Model model, Principal principal)
			throws DocumentException {
		if (bindingResult.hasErrors()) {
			return null;
		}
		RegisteredUser user = giveRegisteredUser(principal);
		if (user == null) {
			return null;
		}
		return new ResponseEntity<byte[]>(tankenHelper.createPDF(user
				.getCurrentAuto()), HttpStatus.CREATED);
	}
}
