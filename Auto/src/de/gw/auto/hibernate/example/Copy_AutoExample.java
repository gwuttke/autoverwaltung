package de.gw.auto.hibernate.example;

import java.math.BigDecimal;
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
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.hibernate.InitSessionFactory;

public class Copy_AutoExample {
	static Set<Benzinart> bArt = new HashSet<Benzinart>();
	static Auto oldCar;
	private static Logger log = Logger.getLogger(Copy_AutoExample.class);

	public static void main(String[] args) {
		new Copy_AutoExample();
	}

	public Copy_AutoExample() {
		try {
			clean();
			createAuto();
			createBenzinart();
			createTanken();
			createRelation();
			//delete();
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
		
		Calendar kauf = new GregorianCalendar(2010, 10, 8);
		Calendar erstZul = new GregorianCalendar(2000, 10, 8);

		Auto oldCar = new Auto("OLD - TE 1234", 1000, new Date(new Datum(kauf)
				.getDate().getTimeInMillis()), new Date(new Datum(erstZul)
				.getDate().getTimeInMillis()), bArt, 2350);

		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(oldCar);
		tx.commit();
		return oldCar;
	}
	
	private Set<Benzinart> createBenzinart(){
		Benzinart bSuper = new Benzinart("Super");		
		Benzinart bSuperE10 = new Benzinart("Super E10");
		bSuper.setAuto(oldCar);
		
		bArt.add(bSuper);
		bArt.add(bSuperE10);
		
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(bSuper);
		session.save(bSuperE10);
		tx.commit();
		return bArt;
		
	}

	private static Tanken createTanken() {
		Set<Ort> orte = new HashSet<Ort>();
		Ort o = new Ort("München");
		Land d = new Land("Deutschland", orte);
		o.setLand(d);

		orte.add(o);
		Tanken t = null;
		for (Ort ort : orte) {
			for (Benzinart b : bArt) {
				t = new Tanken(50000, d, ort, new Tank("voll"), new BigDecimal(
						50.00), oldCar, new Date(
						new GregorianCalendar().getTimeInMillis()),
						new BigDecimal(10.00), new BigDecimal(1.00), b);
			}

		}
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
		return t;
	}
	
	

	private static void update() {
		// Auto car = createAuto();
		Tanken tanken = createTanken();
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		// car.setKmAktuell(2500);
		tanken.setOrt(new Ort("München"));
		// session.update(car);
		session.update(tanken);
		tx.commit();
	}

	private static void delete() {
		Auto car = createAuto();
		Transaction tx = null;
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		session.delete(car);
		//session.delete(bArt.remove());
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
		Benzinart benzinart = new Benzinart("Diesel");
		Benzinart benzinart2 = new Benzinart("Elektro");
		bArt.add(benzinart);
		bArt.add(benzinart2);
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Calendar kauf = new GregorianCalendar(2012, 10, 9);
		Calendar erstZul = new GregorianCalendar(2010, 8, 16);

		Auto car = new Auto("NEW - TE 1235", 6000, new Date(new Datum(kauf)
				.getDate().getTimeInMillis()), new Date(new Datum(erstZul)
				.getDate().getTimeInMillis()), bArt, 10000);
		session.save(car);
		session.save(benzinart);
		session.save(benzinart2);
		/* Wir setzen die Beziehung auf BEIDEN Seiten */
		benzinart.setAuto(car);
		car.getBenzinArten().add(benzinart);
		car.getBenzinArten().add(benzinart2);
		tx.commit();
	}

	private static void query() {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List autos = session.createQuery("select a from Auto a").list();
		List tankungen = session.createQuery("select t from Tanken t").list();
		for (Iterator iter = autos.iterator(); iter.hasNext();) {
			Auto element = (Auto) iter.next();
			log.debug(element);
			System.out.println(element);
		}
		System.out.println("Tankungen:");
		for (Iterator iter = tankungen.iterator(); iter.hasNext();) {
			Tanken elementT = (Tanken) iter.next();
			log.debug(elementT);
			System.out.println(elementT);
		}
		tx.commit();
	}

}
