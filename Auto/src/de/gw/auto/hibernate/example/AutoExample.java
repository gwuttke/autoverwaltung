package de.gw.auto.hibernate.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.jboss.logging.Logger;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Datum;
import de.gw.auto.hibernate.InitSessionFactory;

public class AutoExample {
	private static Set<Benzinart> benArt = new HashSet<Benzinart>();
	private static Logger log = Logger.getLogger(AutoExample.class);

	public static void main(String[] args) {
		try {
			clean();
			ExampleBenzinart.create();
			createAuto();
			createRelation();
			//delete();
			//update();
			query();
		} catch (RuntimeException e) {
			try {
				Session session = InitSessionFactory.getInstance()
						.getCurrentSession();
				if (session.getTransaction().isActive())
					session.getTransaction().rollback();
			} catch (HibernateException e1) {
				log.error("Error rolling back transaction");
			}
			// throw the exception again
			throw e;
		}
	}

	private static List<Object> select(String hqsQuerry) {
		List<Object> obj = new ArrayList<Object>();
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List entrys = session.createQuery(hqsQuerry).list();
		for (Iterator iter = entrys.iterator(); iter.hasNext();) {
			obj.add(iter.next());
		}
		tx.commit();
		return obj;
	}

	private static List<?> selectTable(String tablename) {
		List<?> obj = new ArrayList<>();
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List entrys = session.createQuery("FROM " + tablename).list();
		obj.addAll(entrys);
		tx.commit();
		return obj;
	}

	private static void clean() {
		String[] tablenames = new String[] { /* "auto_benzinart", */"Benzinart",
				"Ort", "Land", "Tank", "Tanken", "Auto", "SonstigeAusgaben" };

		clean(tablenames);

	}

	private static Auto createAuto() {
		
		List<Benzinart> benzinarten = (List<Benzinart>) selectTable("Benzinart");
		
		Set<Benzinart> bArt = new HashSet<Benzinart>(benzinarten);
		
		Calendar kauf = new GregorianCalendar(2010, 10, 8);
		Calendar erstZul = new GregorianCalendar(2000, 10, 8);

		Auto oldCar = new Auto("OLD - TE 1234", 1000, new Date(new Datum(kauf)
				.getDate().getTimeInMillis()), new Date(new Datum(erstZul)
				.getDate().getTimeInMillis()), bArt, 2350);
		
		return (Auto) speichern(oldCar);
	}

	private static Object speichern(Object saveObject) {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(saveObject);
		tx.commit();
		return saveObject;
	}

	private static Object update(Object updateObject) {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(updateObject);
		tx.commit();
		session.close();
		return updateObject;
	}

	private static void update() {
		Auto car = createAuto();
		car.setKmAktuell(2500);
		update(car);
	}

	private static Object delete(Object deleteObject) {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(deleteObject);
		tx.commit();
		return deleteObject;
	}

	private static void delete() {
		Auto car = createAuto();
		delete(car);
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

	private static String[] clean(String[] tabenames) {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		for (String table : tabenames)
			session.createQuery("delete from " + table).executeUpdate();
		session.flush();
		session.clear();
		tx.commit();
		return tabenames;
	}

	private static void createRelation() {
		List<Benzinart> benzinarten = (List<Benzinart>) selectTable("Benzinart");
		Benzinart benzinart = new Benzinart("Diesel");
		Benzinart benzinart2 = new Benzinart("Elektro");
		benArt.add(benzinart);
		benArt.add(benzinart2);
		benArt.add(benzinarten.get(1));
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Calendar kauf = new GregorianCalendar(2012, 10, 9);
		Calendar erstZul = new GregorianCalendar(2010, 8, 16);

		Auto honey = new Auto("NEW - TE 1235", 6000, new Date(new Datum(kauf)
				.getDate().getTimeInMillis()), new Date(new Datum(erstZul)
				.getDate().getTimeInMillis()), benArt, 10000);

		session.save(benzinart);
		session.save(benzinart2);
		//session.save(benArt);
		/* Wir setzen die Beziehung auf BEIDEN Seiten */
		honey.setBenzinarten(benArt);
		session.save(honey);
		tx.commit();
	}

	private static void query() {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List autos = session.createQuery("select a from Auto a").list();
		for (Iterator iter = autos.iterator(); iter.hasNext();) {
			Auto element = (Auto) iter.next();
			log.debug(element);
			System.out.println(element);
		}
		tx.commit();
	}

	private static class ExampleBenzinart {

		private static Set<Benzinart> create() {
			Set<Benzinart> bArt = new HashSet<Benzinart>();

			bArt.add(new Benzinart("Super"));
			bArt.add(new Benzinart("Super E10"));

			for (Benzinart b : bArt) {
				speichern(b);
			}
			return bArt;

		}

	}

}
