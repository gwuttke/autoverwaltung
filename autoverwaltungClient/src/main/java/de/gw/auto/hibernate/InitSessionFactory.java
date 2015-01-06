package de.gw.auto.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class InitSessionFactory {
	/** The single instance of hibernate SessionFactory */
	private static org.hibernate.SessionFactory sessionFactory;

	private InitSessionFactory() {
	}

	static {

		Configuration configuration = new Configuration();
		ServiceRegistry serviceRegistry;
		configuration.configure();

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();

		sessionFactory = new Configuration().configure().buildSessionFactory(
				serviceRegistry);
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}
}
