package de.gw.auto.dao;

import org.springframework.data.domain.Sort;

public class DefaultDao {
	protected static Sort sortByNameAsc() {
	    return new Sort(Sort.Direction.ASC, "name");
	}
	
	protected static Sort sortByBeschreibungAsc() {
	    return new Sort(Sort.Direction.ASC, "beschreibung");
	}
	
	

	public DefaultDao() {
		super();
	}
}