package de.gw.auto.hibernate.example;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
			createAuto();
			createRelation();
			delete();
			update();
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

	private static Auto createAuto() {
		Set<Benzinart> bArt = new HashSet<Benzinart>();

		bArt.add(new Benzinart("Super"));
		bArt.add(new Benzinart("Super E10"));
		Calendar kauf = new GregorianCalendar(2010, 10, 8);
		Calendar erstZul = new GregorianCalendar(2000, 10, 8);

		Auto oldCar = new Auto("OLD - TE 1234", 1000, new Date(new Datum(kauf).getDate().getTimeInMillis()),
				new Date(new Datum(erstZul).getDate().getTimeInMillis()), bArt, 2350);

		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(oldCar);
		tx.commit();
		return oldCar;
	}

	private static void update() {
		Auto car = createAuto();
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		car.setKmAktuell(2500);
		session.update(car);
		tx.commit();
	}

	private static void delete() {
		Auto car = createAuto();
		Transaction tx = null;
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		session.delete(car);
		tx.commit();
	}

	private static void clean() {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.createQuery("delete from Benzinart").executeUpdate();
		session.createQuery("delete from Auto").executeUpdate();
		session.flush();
		session.clear();
		tx.commit();
	}

	private static void createRelation() {
		benArt.add(new Benzinart("Diesel"));
		benArt.add(new Benzinart("Elektro"));
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Calendar kauf = new GregorianCalendar(2012, 10, 9);
		Calendar erstZul = new GregorianCalendar(2010, 8, 16);
		
		Auto honey = new Auto("NEW - TE 1235", 6000, new Date(new Datum(kauf).getDate().getTimeInMillis()),
				new Date(new Datum(erstZul).getDate().getTimeInMillis()), benArt, 10000);
		session.save(honey);
		Benzinart benzinart = new Benzinart("Diesel");
		Benzinart benzinart2 = new Benzinart("Elektro");
		session.save(benzinart);
		session.save(benzinart2);
		/* Wir setzen die Beziehung auf BEIDEN Seiten */
		benzinart.setAuto(honey);
		honey.getBenzinArten().add(benzinart);
		honey.getBenzinArten().add(benzinart2);
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

}
