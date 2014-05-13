package de.gw.auto.hibernate;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Datum;

public class Operationen {
	
	
	private static <T> T create(Object[][] addObj) {
		Set<Benzinart> bArt = new HashSet<Benzinart>();

		bArt.add(new Benzinart("Super"));
		bArt.add(new Benzinart("Super E10"));
		Calendar kauf = new GregorianCalendar(2010, 10, 8);
		Calendar erstZul = new GregorianCalendar(2000, 10, 8);
		
	addObj.getClass().

		Auto oldCar = new Auto("OLD - TE 1234", 1000, new Date(new Datum(kauf).getDate().getTimeInMillis()),
				new Date(new Datum(erstZul).getDate().getTimeInMillis()), bArt, 2350);

		Session session = InitSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(oldCar);
		tx.commit();
		return oldCar;
	}

}
