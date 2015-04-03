package de.gw.auto.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.gw.auto.dao.Settings;

@RestController
public class LoginController {
	
	@RequestMapping("/LogIn")
	public Settings logIn(){
		return null;
	}

}
