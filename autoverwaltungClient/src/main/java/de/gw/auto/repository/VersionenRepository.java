package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Version;

public interface VersionenRepository extends JpaRepository<Version, Integer>{
	public Version findByPlattform(String plattform);
}
