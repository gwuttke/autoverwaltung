package de.gw.auto.view.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.usertype.UserVersionType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.Errors;

import de.gw.auto.view.ValidPattern;

public class LoginModel {

	private String username;
	@Email
	private String email;

	private String passwort;
	
	private String status = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		Pattern pattern = Pattern.compile(ValidPattern.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(username);
		if (matcher.matches()) {
			this.email = username;
		}else{
			this.username = username; 
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
