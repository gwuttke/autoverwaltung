package de.gw.auto.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.Constans;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.domain.Vergleich;
import de.gw.auto.service.RegisteredUser;

@Service
public class TankenInfo {
	@Autowired
	TankenDao tankenDao;

	@Deprecated
	private List<Info> tankenInfos = new ArrayList<Info>();

	protected TankenInfo() {
	}

	@Deprecated
	public void init(TankenDao tDao, RegisteredUser registedUser) {
		load(tDao, registedUser);
	}

	/**
	 * @deprecated
	 * @since 1.1
	 * @return
	 */
	public List<Info> getTankenInfos() {
		return tankenInfos;
	}

	public double getMaxPreisProLiter(int year, Auto auto) {
		Tanken maxPreisProLiter = tankenDao.getMaxPreisProLiter(year, auto);
		if(maxPreisProLiter == null){
			return 0d;
		}
		return maxPreisProLiter.getPreisProLiter()
				.doubleValue();
	}

	public double getMaxPreisProLiter(Auto auto) {
		Tanken maxPreisProLiter = tankenDao.getMaxPreisProLiter(auto);
		if(maxPreisProLiter == null){
			return 0d;
		}
		return maxPreisProLiter.getPreisProLiter()
				.doubleValue();
	}

	public double getMinPreisProLiter(int year, Auto auto) {
		Tanken minPreisProLiter = tankenDao.getMinPreisProLiter(year, auto);
		if(minPreisProLiter==null){
			return 0d;
		}
		return minPreisProLiter.getPreisProLiter()
				.doubleValue();
	}

	public double getMinPreisProLiter(Auto auto) {
		Tanken minPreisProLiter = tankenDao.getMinPreisProLiter(auto);
		if(minPreisProLiter==null){
			return 0d;
		}
		return minPreisProLiter.getPreisProLiter()
				.doubleValue();
	}

	public double getAVGPreisLiter(int year, Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(year, auto);
		double sumPreisProLiter = 0d;
		int count = tankfuellungen.size();
		for (Tanken t : tankfuellungen) {
			sumPreisProLiter += t.getPreisProLiter().doubleValue();
		}
		return count == 0 ? 0 : sumPreisProLiter / count;
	}
	
	public double getAVGPreisLiter(Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(auto);
		double sumPreisProLiter = 0d;
		int count = tankfuellungen.size();
		for (Tanken t : tankfuellungen) {
			sumPreisProLiter += t.getPreisProLiter().doubleValue();
		}
		return count == 0 ? 0 : sumPreisProLiter / count;
	}

	/**
	 * @since 1.1
	 * @param year
	 * @param auto
	 * @return gesamt Liter eines Jahres als Double
	 */
	public double getAllLiter(Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(auto);
		double sumLiter = 0d;
		for (Tanken t : tankfuellungen) {
			sumLiter += t.getLiter().doubleValue();
		}
		return sumLiter;
	}

	/**
	 * @since 1.1
	 * @param year
	 * @param auto
	 * @return gesamt Liter eines Jahres als Double
	 */
	public double getLiterOfYear(int year, Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(year, auto);
		double sumLiter = 0d;
		for (Tanken t : tankfuellungen) {
			sumLiter += t.getLiter().doubleValue();
		}
		return sumLiter;
	}

	public int getKmOfYear(int year, Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(year, auto);
		int sumKm = 0;
		for (Tanken t : tankfuellungen) {
			sumKm += t.getKmStand();
		}
		return sumKm;
	}

	public int getAllKm(Auto auto) {
		List<Tanken> tankfuellungen = getTankfuellungSorted(auto);
		if (tankfuellungen==null){
			return 0;
		}
		int gesKm = tankfuellungen.get(tankfuellungen.size()-1).getKmStand()
				- auto.getKmStart();
		return gesKm;
	}

	/**
	 * @since 1.1
	 * @param year
	 * @param auto
	 * @return gesamt Kosten als Double
	 */
	public double getAllKosten(Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(auto);
		double sumKosten = 0d;
		for (Tanken t : tankfuellungen) {
			sumKosten += t.getKosten().doubleValue();
		}
		return sumKosten;
	}

	/**
	 * @since 1.1
	 * @param year
	 * @param auto
	 * @return gesamt Kosten eines Jahres als Double
	 */
	public double getKostenOfYear(int year, Auto auto) {
		Set<Tanken> tankfuellungen = getTankfuellung(year, auto);
		double sumKosten = 0d;
		for (Tanken t : tankfuellungen) {
			sumKosten += t.getKosten().doubleValue();
		}
		return sumKosten;
	}

	private Set<Tanken> getTankfuellung(int year, Auto auto) {
		Set<Tanken> tankfuellungen = new HashSet<Tanken>(
				tankenDao.getTankungen(auto, year));
		return tankfuellungen;
	}

	private Set<Tanken> getTankfuellung(Auto auto) {
		Set<Tanken> tankfuellungen = new HashSet<Tanken>(
				tankenDao.getTankungen(auto));
		return tankfuellungen;
	}

	private List<Tanken> getTankfuellungSorted(Auto auto) {
		return tankenDao.getTankungen(auto);
	}

	/**
	 * @deprecated use {@link #getKostenOfYear(int, Auto)},
	 *             {@link #getAVGPreisLiter(int, Auto)}, <br>
	 *             {@link #getKmOfYear(int, Auto)},
	 *             {@link #getLiterOfYear(int, Auto)}, <br>
	 *             {@link #getMaxPreisProLiter(int, Auto)},
	 *             {@link #getMinPreisProLiter(int, Auto)}
	 * @since 1.1
	 * @param tDao
	 * @param registedUser
	 */
	@Deprecated
	private void load(TankenDao tDao, RegisteredUser registedUser) {
		List<Tankfuellung> tankungen = tDao.getTankenList();
		Info tiKosten = new Info(Constans.TANKEN_KOSTEN);
		Info tiMaxPreisProLiter = new Info(Constans.MAX_PREIS);
		Info tiMinPreisProLiter = new Info(Constans.MIN_PREIS);
		Info tiAnzahlLiter = new Info(Constans.ANZAHL_LITER);
		Info tiSummeKm = new Info(Constans.GEFAHRENE_KM);
		Info tiAvgPreisProLiter = new Info(Constans.AVG_Preis);
		Set<BigDecimal> preiseGesamt = new HashSet<BigDecimal>();
		Set<BigDecimal> preiseDiesesJahr = new HashSet<BigDecimal>();
		Set<BigDecimal> preiseLetztesJahr = new HashSet<BigDecimal>();
		Datum datum = new Datum();
		Vergleich v = new Vergleich();
		int index = 0;
		if (tankungen != null) {
			Tanken tVorher = null;
			for (Tanken t : tankungen) {
				if (index > 0) {
					v.setZahl(t.getPreisProLiter());
				} else {
					v = new Vergleich(t.getPreisProLiter());
				}
				datum.setDate(t.getDatum());
				// Jahr, wo getankt wurde
				int jahr = datum.getDate().get(Calendar.YEAR);
				// aktuelles Jahr
				int nowJahr = datum.getNow().get(Calendar.YEAR);
				// Gesammt berechnung
				tiKosten.setGesammt(tiKosten.getGesammt().add(t.getKosten()));
				tiMaxPreisProLiter.setGesammt(v.max());
				tiMinPreisProLiter.setGesammt(v.min());
				tiAnzahlLiter.setGesammt(tiAnzahlLiter.getGesammt().add(
						t.getLiter()));
				// tiSummeKm.setGesammt(new BigDecimal(Berechnung
				// .getInsgGefahreneKm(registedUser.getCurrentAuto())));
				preiseGesamt.add(t.getPreisProLiter());
				// Jahr und Vorjahr
				if (nowJahr == jahr) {
					tiKosten.setDiesesJahr(tiKosten.getDiesesJahr().add(
							t.getKosten()));
					tiMaxPreisProLiter.setDiesesJahr(v.max());
					tiMinPreisProLiter.setDiesesJahr(v.min());
					tiAnzahlLiter.setDiesesJahr(tiAnzahlLiter.getDiesesJahr()
							.add(t.getLiter()));
					if (index > 0) {
						tiSummeKm.setDiesesJahr(tiSummeKm.getDiesesJahr().add(
								new BigDecimal(Berechnung
										.getGefahreneKilometer(tVorher, t))));
					} else {
						// tiSummeKm.setDiesesJahr(tiSummeKm.getDiesesJahr().add(
						// new BigDecimal(t.getKmStand()
						// - registedUser.getCurrentAuto()
						// .getKmKauf())));
					}
					preiseDiesesJahr.add(t.getPreisProLiter());
				} else if (nowJahr - 1 == jahr) {
					tiKosten.setVorjahr(tiKosten.getVorjahr()
							.add(t.getKosten()));
					tiMaxPreisProLiter.setVorjahr(v.max());
					tiMinPreisProLiter.setVorjahr(v.min());
					tiAnzahlLiter.setVorjahr(tiAnzahlLiter.getVorjahr().add(
							t.getLiter()));
					if (index > 0) {
						tiSummeKm.setVorjahr(tiSummeKm.getVorjahr().add(
								new BigDecimal(t.getKmStand())));
					} else {
						// tiSummeKm.setVorjahr(tiSummeKm.getVorjahr().add(
						// new BigDecimal(t.getKmStand()
						// - registedUser.getCurrentAuto()
						// .getKmAktuell())));
					}
					tiSummeKm.setVorjahr(tiSummeKm.getVorjahr().add(
							new BigDecimal(t.getKmStand())));
					preiseLetztesJahr.add(t.getPreisProLiter());
				}
				tiAvgPreisProLiter.setGesammt(Berechnung
						.findAverage(preiseGesamt));
				tiAvgPreisProLiter.setDiesesJahr(Berechnung
						.findAverage(preiseDiesesJahr));
				tiAvgPreisProLiter.setVorjahr(Berechnung
						.findAverage(preiseLetztesJahr));
				tVorher = t;
				index++;
			}
		}
		tankenInfos.add(tiMinPreisProLiter);
		tankenInfos.add(tiMaxPreisProLiter);
		// tankenInfos.add(tiAvgPreisProLiter);
		tankenInfos.add(tiAnzahlLiter);
		tankenInfos.add(tiSummeKm);
		tankenInfos.add(Berechnung.getAVGVerbrauchPro100(tiSummeKm,
				tiAnzahlLiter));
		tankenInfos.add(tiKosten);
	}

	/**
	 * @deprecated
	 * @since 1.1
	 * @param name
	 * @return
	 */
	@Deprecated
	public Info searchInfo(String name) {
		for (Info info : tankenInfos) {
			if (name == info.getName()) {
				return info;
			}
		}
		return null;
	}
}
