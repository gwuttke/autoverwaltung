package de.gw.auto.view;

public class ViewName {
	private static final String REDIRECT = "redirect:";

	private static final String ERROR = "error?";

	private static final String ROOT = "/";

	private static final String USER = "user";

	private static final String TANKEN = "tanken";

	private static final String ROOT_USER = ROOT + USER;

	public static final String ROOT_USER_SHOW = ROOT_USER +ROOT+ "show"; 

	public static final String LOGIN = "login";

	public static final String LOGOUT = "logout";

	public static final String REGISTER = "register";

	public static final String REGISTRATION = "registration";

	public static final String USER_MAIN_PAGE = ROOT_USER + "/show";

	public static final String DEFAULT_ROOT = ROOT;

	public static final String REDIRECT_LOGIN = REDIRECT + LOGIN;

	public static final String REDIRECT_ROOT = REDIRECT + ROOT;

	public static final String REDIRECT_USER_MAIN_PAGE = REDIRECT + USER_MAIN_PAGE;

	public static final String REDIRECT_ROOT_USER_SHOW = REDIRECT + ROOT_USER_SHOW;

	public static final String REDIRECT_REGISTER = REDIRECT + REGISTER;

	public static final String ERROR_LOGIN = ERROR + LOGIN;
}
