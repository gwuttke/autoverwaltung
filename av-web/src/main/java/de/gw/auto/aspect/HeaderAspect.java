package de.gw.auto.aspect;

import java.security.Principal;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.ui.Model;

import de.gw.auto.controller.ControllerHelper;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.view.model.HeaderModel;


@Aspect
public class HeaderAspect extends ControllerHelper{
	
	@Around(value="anyControllerRequest(pricipal,modal)")
	public void doing(Principal principal,Model model){
		RegisteredUser user = giveRegisteredUser(principal);
		HeaderModel headerModel = new HeaderModel(user);
		
		model.addAttribute(headerModel);
	}
	
	
	/**
	 * all Controller Requests
	 * @param principal
	 */
	@Pointcut("execution(* de.gw.auto.contoller..*.*() && args(principal,model,..)")
	private void anyControllerRequest(Principal principal,Model model) {}
}
