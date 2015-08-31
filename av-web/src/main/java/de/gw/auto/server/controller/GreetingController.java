  package de.gw.auto.server.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.extras.springsecurity4.auth.Authorization;

import de.gw.auto.service.implementation.BenutzerService;
import de.gw.auto.view.ViewName;
import de.gw.auto.view.model.LoginModel;

@Controller
public class GreetingController {
	
	/*
	@Autowired
	BenutzerService userService;

	@RequestMapping(value="/",method=RequestMethod.GET)
    public String greeting(@RequestParam(value="name",required=false, defaultValue="World") String name, Model model,  Principal principal) {
		System.out.println("test");       
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
		  
		//model.addAttribute("name", name);
        //return new ViewName().GREETING;
    }
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute LoginModel signupForm,
	        Errors errors, RedirectAttributes ra) {
	     
	    if (errors.hasErrors()) {
	        return ViewName.REDIRECT_LOGIN;
	    }
	  
	    try {
			userService.login(signupForm.getUsername(), signupForm.getPasswort());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	     
	    return ViewName.REDIRECT_ROOT;
	     
	}
	*/
}
