package de.gw.auto.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Synchronization;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.spi.LocalStatus;

import de.gw.auto.domain.GenericClass;

public class DatenbankZugriff {
	private Session session;
	private Transaction tx;

	protected Session startSession() {
		try {
			session = InitSessionFactory.getInstance().getCurrentSession();
			tx = session.getTransaction();

		} catch (HibernateException ex) {
			throw ex;
		}
		return session;
	}

	protected Transaction startTransaction() {
		if (tx.isActive() == false) {
			tx = session.beginTransaction();
		}
		return tx;
	}

	protected List<?> select(String hqsQuerry) {
		List<?> obj = new ArrayList<Object>();
		try {
			startSession();
			startTransaction();
			List entrys = session.createQuery(hqsQuerry).list();
			obj.addAll(entrys);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		}

		return obj;
	}

	protected List<?> selectTable(GenericClass tablename) {
		startSession();
		startTransaction();
		String classSimpleName = tablename.getType().getSimpleName().toString()
				.trim();
		List<?> obj = new ArrayList<>();
		obj = select("FROM " + classSimpleName);
		return obj;
	}

	public void clean() {
		String[] tablenames = new String[] { /* "auto_benzinart", */"Benzinart",
				"Ort", "Land", "Tank", "Tanken", "Auto", "SonstigeAusgaben" };

		clean(tablenames);

	}

	protected Object speichern(Object saveObject) {
		try {
			startSession();
			startTransaction();

			session.save(saveObject);
			tx.commit();
		} catch (HibernateException ex) {
			tx.rollback();
			throw ex;
		}
		return saveObject;
	}

	protected Object update(Object updateObject) {
		try {
			startSession();
			startTransaction();
			session.update(updateObject);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		}
		return updateObject;
	}

	protected Object delete(Object deleteObject) {
		try {
			startSession();
			startTransaction();
			session.delete(deleteObject);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		}
		return deleteObject;
	}

	private static String[] clean(List tablenames) {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		tablenames.iterator();
		String[] names = new String[] {};
		for (Iterator<String> iterator = tablenames.iterator(); iterator
				.hasNext();) {
			String name = iterator.next();
			session.createQuery("delete from " + name).executeUpdate();
			names[names.length + 1] = name;

		}
		session.flush();
		session.clear();
		tx.commit();
		return names;
	}

	private String[] clean(String[] tabenames) {
		this.startSession();
		this.startTransaction();
		for (String table : tabenames)
			session.createQuery("delete from " + table).executeUpdate();
		session.flush();
		session.clear();
		tx.commit();
		return tabenames;
	}

}
