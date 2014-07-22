package de.gw.auto.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatenbankZugriff {
	private Session session;
	private Transaction tx;

	protected Session startSession() {
			session = InitSessionFactory.getInstance().getCurrentSession();
			tx = session.getTransaction();

		
		return session;
	}

	protected Transaction startTransaction() {
		if (tx.isActive() == false) {
			tx = session.beginTransaction();
		}
		return tx;
	}

	protected List<?> select(String hqsQuerry) throws Exception {
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

	protected List<?> selectTable(Class<?> tablename) throws Exception {
		startSession();
		startTransaction();
		String classSimpleName = tablename.getSimpleName().toString().trim();
		List<?> obj = new ArrayList<Object>();
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

	protected Object update(Object updateObject) throws Exception {
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

	protected Object delete(Object deleteObject) throws Exception {
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
	
	private String[] clean(String[] tabenames) {
		this.startSession();
		this.startTransaction();
		for (String table : tabenames){
			session.createQuery("delete from " + table).executeUpdate();
		}
		session.flush();
		session.clear();
		tx.commit();
		return tabenames;
	}

}
