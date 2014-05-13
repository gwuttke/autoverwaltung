package de.gw.auto.hibernate.example;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Datum;
import de.gw.auto.hibernate.InitSessionFactory;

public abstract class HibernateExample {
	
	public HibernateExample(String[] cleanStatement, Object createObject, ){
		try {
			clean("delete from Benzinart");
			createObject(new Auto());
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
	
	private static void clean(String[] querrys) {
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		for (String s : querrys){
			session.createQuery(s).executeUpdate();
			//session.createQuery("delete from Benzinart").executeUpdate();
		}
		session.flush();
		session.clear();
		tx.commit();
	}
	
	private Object createObject(Object obj) {
		return obj;
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
}
