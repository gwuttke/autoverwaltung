package de.gw.auto.domain;

public class GenericClass<T> {

	private final Class<T> type;

	public GenericClass(Class<T> type) {
		this.type = type;
	}

	public Class<T> getType() {
		return this.type;
	}
}
