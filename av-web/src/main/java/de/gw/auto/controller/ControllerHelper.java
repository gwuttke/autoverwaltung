package de.gw.auto.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.gw.auto.Constans;
import de.gw.auto.repository.UserRepository;
import de.gw.auto.service.RegisteredUser;

@Component
public class ControllerHelper {
	@Autowired
	private UserRepository userRepository;

	public RegisteredUser giveRegisteredUser(Principal principal) {
		RegisteredUser user = null;
		user = new RegisteredUser(userRepository.findByEMail(principal
				.getName()));
		return user;
	}
}