package de.gw.auto.view;

public class ViewName {

	private static final String REDIRECT = "redirect:";
	private static final String ERROR = "error?";
	
	private static final String ROOT = "/";

	public static final String GREETING = "greeting";
	public static final String HELLO = "hello";
	public static final String LOGIN = "login";
	public static final String REGISTER = "register";
	public static final String REGISTRATION = "registration";
	
	public static final String DEFAULT_ROOT = ROOT;

	public static final String REDIRECT_LOGIN = REDIRECT + LOGIN;
	public static final String REDIRECT_ROOT = REDIRECT + ROOT;
	public static final String REDIRECT_REGISTER = REDIRECT + REGISTER;
	
	public static final String ERROR_GREETING = ERROR + GREETING;
	public static final String ERROR_LOGIN = ERROR + LOGIN;
}
