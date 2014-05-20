package de.gw.auto.hibernate.example;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.jboss.logging.Logger;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.GenericClass;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.hibernate.DatenbankZugriff;
import de.gw.auto.hibernate.InitSessionFactory;

public class Example {
	private static Set<Benzinart> benArt = new HashSet<Benzinart>();
	private static Logger log = Logger.getLogger(Example.class);
	private Session session;
	private Transaction tx;

	public static void main(String[] args) {

		new Example();

	}

	public Example() {
		ExampleBenzinart exampBenzinart = new ExampleBenzinart();
		ExampleAuto exampAuto = new ExampleAuto();
		ExampleTanken exampTanken = new ExampleTanken();
		DatenbankZugriff dbZugriff = new DatenbankZugriff();

		try {

			dbZugriff.clean();
			exampBenzinart.create();
			exampAuto.create();
			exampAuto.createRelation();
			exampTanken.create();
			// delete();
			// update();
			exampAuto.query();
			exampTanken.querry();
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

	

	private class ExampleBenzinart extends DatenbankZugriff {

		private Set<Benzinart> create() {
			Set<Benzinart> bArt = new HashSet<Benzinart>();

			this.startSession();
			this.startTransaction();
			bArt.add(new Benzinart("Super"));
			bArt.add(new Benzinart("Super E10"));

			for (Benzinart b : bArt) {
				speichern(b);
			}
			return bArt;

		}

	}

	private class ExampleAuto extends DatenbankZugriff {
		private GenericClass<?> instanceOfBenzinart = new GenericClass<Benzinart>(Benzinart.class);
		private GenericClass<?> instanceOfAuto = new GenericClass<Auto>(Auto.class);

		private List<Benzinart> benzinarten = (List<Benzinart>) this.selectTable(instanceOfBenzinart);

		
		private Auto create() {
			Set<Benzinart> bArt = new HashSet<Benzinart>(benzinarten);

			Calendar kauf = new GregorianCalendar(2010, 10, 8);
			Calendar erstZul = new GregorianCalendar(2000, 10, 8);

			Auto oldCar = new Auto("OLD - TE 1234", 1000, new Date(new Datum(
					kauf).getDate().getTimeInMillis()), new Date(new Datum(
					erstZul).getDate().getTimeInMillis()), bArt, 2350);

			return (Auto) this.speichern(oldCar);
		}

		private void update() {
			Auto car = this.create();
			car.setKmAktuell(2500);
			this.update(car);
		}

		private void delete() {
			Auto car = this.create();
			this.delete(car);
		}

		private void createRelation() {
			
			
			Benzinart benzinart = new Benzinart("Diesel");
			Benzinart benzinart2 = new Benzinart("Elektro");
			benArt.add(benzinart);
			benArt.add(benzinart2);
			//benArt.add(benzinarten.get(1));
			
			Calendar kauf = new GregorianCalendar(2012, 10, 9);
			Calendar erstZul = new GregorianCalendar(2010, 8, 16);

			Auto honey = new Auto("NEW - TE 1235", 6000, new Date(new Datum(
					kauf).getDate().getTimeInMillis()), new Date(new Datum(
					erstZul).getDate().getTimeInMillis()), benArt, 10000);
			
			
			speichern(benzinart);
			speichern(benzinart2);
			
			honey.setBenzinarten(benArt);
		speichern(honey);
		}

		private void query() {
			
			List<Auto> autos = (List<Auto>) this.selectTable(instanceOfAuto);

			for (Iterator iter = autos.iterator(); iter.hasNext();) {
				Auto element = (Auto) iter.next();
				log.debug(element);
				System.out.println(element);
			}
		}
	}

	private static class ExampleTanken extends DatenbankZugriff{
		private GenericClass<?> instanceOfAuto = new GenericClass<Auto>(Auto.class);
		private GenericClass<?> instanceOfTanken = new GenericClass<Tanken>(Tanken.class);

		private Tanken create() {

			Set<Ort> orte = new HashSet<Ort>();
			Ort o = new Ort("München");
			Land d = new Land("Deutschland", orte);

			List<Auto> autos = (List<Auto>) selectTable(instanceOfAuto);
			orte.add(o);
			Tanken t = null;
			
			for (Ort ort : orte) {
				for (Benzinart b : autos.get(0).getBenzinarten()) {
					t = new Tanken(50000, d, ort, new Tank("voll"),
							new BigDecimal(50.00), autos.get(0), new Date(
									new GregorianCalendar().getTimeInMillis()),
							new BigDecimal(10.00), new BigDecimal(1.00), b);
					speichern(t);		
				}
			
			}

			return t;
		}

		private void querry() {
			List<Tanken> tankungen = (List<Tanken>) selectTable(instanceOfTanken);

			System.out.println("Tankungen:");
			for (Iterator iter = tankungen.iterator(); iter.hasNext();) {
				Tanken element = (Tanken) iter.next();
				log.debug(element);
				System.out.println(element);
			}

		}
	}

}
