package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Tanken;

@Service
public class TankenInfo {
	@Autowired
	TankenDao tankenDao;

	protected TankenInfo() {
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

	/**
	 * @see TankenDao#getAvgPreisProLiter(int, Auto)
	 * @param year
	 * @param auto
	 * @return
	 */
	public Double getAvgPreisProLiter(int year, Auto auto) {
		return tankenDao.getAvgPreisProLiter(year, auto);
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
		List<Tanken> tankfuellungen = getTankfuellungSorted(year, auto);
		int result = 0;
		if(tankfuellungen != null && !tankfuellungen.isEmpty()){
			result = tankfuellungen.get(tankfuellungen.size()-1).getKmStand() - tankfuellungen.get(0).getKmStand();
		}
		return result;
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
	
	private List<Tanken> getTankfuellungSorted(int year, Auto auto) {
		return tankenDao.getTankungen(auto, year);
	}
}
