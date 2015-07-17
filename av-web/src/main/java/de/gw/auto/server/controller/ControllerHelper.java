package de.gw.auto.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.gw.auto.Constans;
import de.gw.auto.service.RegisteredUser;

public class ControllerHelper {
	
	public static RegisteredUser giveRegisteredUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		RegisteredUser user = null;
		if (session.getAttribute(Constans.SESSION_BENUTZER) instanceof RegisteredUser) {
			user = (RegisteredUser) session
					.getAttribute(Constans.SESSION_BENUTZER);
		}
		return user;
	}
}