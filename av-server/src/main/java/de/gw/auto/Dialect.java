package de.gw.auto;

public enum Dialect {
	/**
	 * Holds the classnames of hibernate dialects for easy reference.
	 */

	ORACLE("org.hibernate.dialect.Oracle10gDialect"), 
	MYSQL("org.hibernate.dialect.MySQLDialect"), 
	HSQL("org.hibernate.dialect.HSQLDialect"),
	POSGRESQL("org.hibernate.dialect.PostgreSQLDialect");

	private String dialectClass;

	private Dialect(String dialectClass) {
		this.dialectClass = dialectClass;
	}

	public String getDialectClass() {
		return dialectClass;
	}
}
