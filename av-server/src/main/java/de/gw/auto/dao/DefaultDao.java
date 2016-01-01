package de.gw.auto.dao;

import org.springframework.data.domain.Sort;

public class DefaultDao {
	protected static Sort sortByNameAsc() {
	    return new Sort(Sort.Direction.ASC, "name");
	}
	
	protected static Sort sortByNameDesc() {
	    return new Sort(Sort.Direction.DESC, "name");
	}
	
	protected static Sort sortByBeschreibungAsc() {
	    return new Sort(Sort.Direction.ASC, "beschreibung");
	}
	
	protected static Sort sortByBeschreibungDesc() {
	    return new Sort(Sort.Direction.DESC, "beschreibung");
	}
	
	

	public DefaultDao() {
		super();
	}
}