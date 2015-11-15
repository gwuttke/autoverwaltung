package de.gw.auto.view.model.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lowagie.text.DocumentException;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.service.implementation.TankenService;
import de.gw.auto.service.implementation.TankfuellungenPdf;
import de.gw.auto.view.model.NewTanken;
import de.gw.auto.view.model.TankenModel;
import de.gw.auto.view.model.TankenViewModel;

@Component
public class TankenModelHelper {
	@Autowired
	private TankenService tankenService;

	@Autowired
	private TankfuellungenPdf tankfuellungenPdf;

	public TankenViewModel prepareTankViewModel(
			TankenViewModel tankenViewModel, Auto auto, int page) {
		if (auto == null) {
			return tankenViewModel;
		}
		DateTime kaufDatum = new DateTime(auto.getKauf().getTime());
		int currentYear = DateTime.now().getYear();
		LinkedHashMap<Integer, Double> kosten;
		LinkedHashMap<Integer, Double> liter;
		LinkedHashMap<Integer, Map<String, Double>> preisProLiter;
		LinkedHashMap<Integer, Integer> kms;
		kosten = getKosten(currentYear - 1, currentYear, auto);
		liter = getLiter(currentYear - 1, currentYear, auto);
		preisProLiter = getPreisProLiter(currentYear - 1, currentYear, auto);
		kms = getKm(currentYear - 1, currentYear, auto);
		setPagination(tankenViewModel, auto, page);
		tankenViewModel.setKosten(kosten);
		tankenViewModel.setLiter(liter);
		tankenViewModel.setKms(kms);
		tankenViewModel.setPreisProLiter(preisProLiter);
		return tankenViewModel;
	}

	/**
	 * 
	 * @param year
	 * @param toYear
	 * @param auto
	 * @return a LinkedHashMap<Integer,Integer> of Km between year and toYear
	 *         and with key -2 all Kms
	 * @throws AssertionError
	 *             - if year > toYear
	 */
	private LinkedHashMap<Integer, Integer> getKm(int year, int toYear,
			Auto auto) throws AssertionError {
		Assert.isTrue((year <= toYear),
				"Year darf nicht kleiner sein als toYear");
		Assert.isTrue((year > 0 || toYear > 0),
				"Year oder toYear darf nicht negativ oder 0 sein");
		LinkedHashMap<Integer, Integer> kms = new LinkedHashMap<Integer, Integer>();
		int km = 0;
		for (int i = year; i <= toYear; i++) {
			int kmOfYear = tankenService.getKmOfYear(i, auto);
			kms.put(i, kmOfYear);
			km += kmOfYear;
		}
		kms.put(-2, km);
		return kms;
	}

	/**
	 * 
	 * @param year
	 * @param toYear
	 * @param auto
	 * @return a LinkedHashMap<Integer,Double> of costs between year and toYear
	 *         and with key -2 all costs
	 * @throws AssertionError
	 *             - if year > toYear
	 */
	private LinkedHashMap<Integer, Double> getKosten(int year, int toYear,
			Auto auto) throws AssertionError {
		Assert.isTrue(
				(year <= toYear),"Year darf nicht kleiner sein als toYear");
		Assert.isTrue(
				(year > 0 || toYear > 0),"Year oder toYear darf nicht negativ oder 0 sein");
		LinkedHashMap<Integer, Double> kosten = new LinkedHashMap<Integer, Double>();
		double costs = 0d;
		for (int i = year; i <= toYear; i++) {
			double kostenOfYear = tankenService.getKostenOfYear(i, auto);
			kosten.put(i, kostenOfYear);
			costs += kostenOfYear;
		}
		kosten.put(-2, costs);
		return kosten;
	}

	/**
	 * 
	 * @param year
	 * @param toYear
	 * @param auto
	 * @return a LinkedHashMap<Integer,Double> of liters between year and toYear
	 *         and with key -2 all Liter
	 * @throws AssertionError
	 *             - if year > toYear
	 */
	private LinkedHashMap<Integer, Double> getLiter(int year, int toYear,
			Auto auto) throws AssertionError {
		Assert.isTrue((year <= toYear),
				"Year darf nicht kleiner sein als toYear");
		Assert.isTrue((year > 0 || toYear > 0),
				"Year oder toYear darf nicht negativ oder 0 sein");
		LinkedHashMap<Integer, Double> liter = new LinkedHashMap<Integer, Double>();
		double liters = 0d;
		for (int i = year; i <= toYear; i++) {
			double litersOfYear = tankenService.getLiterOfYear(i, auto);
			liter.put(i, litersOfYear);
			liters += litersOfYear;
		}
		liter.put(-2, liters);
		return liter;
	}

	/**
	 * 
	 * @param year
	 * @param toYear
	 * @param auto
	 * @return a LinkedHashMap<Integer,Double> of costs per liter between year
	 *         and toYear
	 * @throws AssertionError
	 *             - if year > toYear
	 */
	private LinkedHashMap<Integer, Map<String, Double>> getPreisProLiter(
			int year, int toYear, Auto auto) throws AssertionError {
		Assert.isTrue((year <= toYear),
				"Year darf nicht kleiner sein als toYear");
		Assert.isTrue((year > 0 || toYear > 0),
				"Year oder toYear darf nicht negativ oder 0 sein");
		LinkedHashMap<Integer, Map<String, Double>> preisProLiter = new LinkedHashMap<Integer, Map<String, Double>>();
		double liters = 0d;
		for (int i = year; i <= toYear; i++) {
			Map<String, Double> litersOfYear = new HashMap<String, Double>();
			double maxLiterOfYear = tankenService.getMaxPreisProLiter(i, auto);
			double minLiterOfYear = tankenService.getMinPreisProLiter(i, auto);
			double avgLiterOfYear = tankenService.getAVGPreisLiter(i, auto);
			litersOfYear.put("max", maxLiterOfYear);
			litersOfYear.put("min", minLiterOfYear);
			litersOfYear.put("avg", avgLiterOfYear);
			preisProLiter.put(i, litersOfYear);
		}
		return preisProLiter;
	}

	private void setPagination(TankenViewModel tankenViewModel, Auto auto,
			int page) {
		List<Tankfuellung> tankfuellungen = tankenService
				.getTankfuellungen(auto);
		List<TankenModel> tankenModels = new ArrayList<TankenModel>();
		for (Tankfuellung tfuellung : tankfuellungen) {
			tankenModels.add(new TankenModel(tfuellung));
		}
		tankenModels.sort(tankenModels.get(0));
		PagedListHolder<TankenModel> pages = new PagedListHolder<TankenModel>(
				tankenModels, new MutableSortDefinition("getKmStand()", true,
						false));
		pages.setPage(page);
		tankenViewModel.setTankfuellungenView(pages);
	}


	public byte[] createEmptyPDF(final Auto auto) throws DocumentException{
		tankfuellungenPdf.setTankfuellungen(null);
		return tankfuellungenPdf.createAndGetBytes().toByteArray();
	}
	
	public byte[] createPDF(final Auto auto) throws DocumentException{
		List<Tankfuellung> tankfuellungen = tankenService
				.getTankfuellungen(auto);
		tankfuellungenPdf.setTankfuellungen(tankfuellungen);
		return tankfuellungenPdf.createAndGetBytes().toByteArray();
	}
}
