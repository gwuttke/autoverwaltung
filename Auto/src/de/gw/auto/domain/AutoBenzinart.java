package de.gw.auto.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AutoBenzinart", uniqueConstraints = @UniqueConstraint(columnNames={"auto", "benzinarten"}))
		
		
public class AutoBenzinart implements Serializable {
	@Id
	@OneToMany(mappedBy="autoBBenzinart")
	Set<Auto> auto;
	@Id
	@OneToMany(mappedBy="benzinart")
	Set<Benzinart> benzinarten;

}
