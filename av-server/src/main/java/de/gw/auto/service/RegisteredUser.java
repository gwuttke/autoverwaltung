package de.gw.auto.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Role;

public class RegisteredUser extends Benutzer implements UserDetails, Principal {
	public RegisteredUser(final Benutzer user) {
		if (user != null) {
			this.setId(user.getId());
			this.setName(user.getName());
			this.setVorname(user.getVorname());
			this.seteMail(user.geteMail());
			this.setPasswort(user.getPasswort());
			this.setAutos(user.getAutos());
			this.setCurrentAuto(user.getCurrentAuto());
			this.setRoles(user.getRoles());
		}
	}

	public Benutzer toBenutzer() {
		return new Benutzer(this.getId(), getName(), getVorname(),
				getBenutzername(), getPassword(), geteMail(), getAutos(),
				getCurrentAuto(), getRoles());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> userRoles = this.getRoles();
		if (userRoles != null) {
			for (Role role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						role.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPasswort();
	}

	@Override
	public String getUsername() {
		return super.getBenutzername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
