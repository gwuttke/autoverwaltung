package de.gw.auto.view.model;

import de.gw.auto.domain.Auto;

public class AutoModelShow extends AutoModel {
	private int id;
	
	

	public AutoModelShow() {
		super();
	}

	public AutoModelShow(Auto auto) {
		super(auto);
		if (auto.getId() > 0) {
			this.id = auto.getId();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
