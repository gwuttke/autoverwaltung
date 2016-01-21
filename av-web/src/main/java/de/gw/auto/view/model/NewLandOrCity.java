package de.gw.auto.view.model;

public class NewLandOrCity {
	private long parent;

	private long id;

	private String text;

	public NewLandOrCity() {
		super();
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public NewLandOrCity(long parent, long id, String text) {
		super();
		this.parent = parent;
		this.id = id;
		this.text = text;
	}
}
