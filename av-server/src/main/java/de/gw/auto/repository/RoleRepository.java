package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Double> {
	public Role findByName(String name);
	
}
