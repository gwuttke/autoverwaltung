package de.gw.auto.view.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;

public class TankenViewModel {
	private PagedListHolder<TankenModel> tankfuellungenView = new PagedListHolder<TankenModel>();

	/**
	 * key = Jahr, Double = Wert;
	 */
	private LinkedHashMap<Integer, Double> kosten;
	

	/**
	 * key = Jahr, Map<String ='min'||'max'||'avg',  Double = Wert>;
	 */
	private LinkedHashMap<Integer, Map<String, Double>> preisProLiter;

	/**
	 * key = Jahr, Double = Wert;
	 */
	private LinkedHashMap<Integer, Integer> kms;
	
	
	/**
	 * key = Jahr, Double = Wert;
	 */
	private LinkedHashMap<Integer, Double> liter;
	
	
	public TankenViewModel() {
	}

	public PagedListHolder<TankenModel> getTankfuellungenView() {
		return tankfuellungenView;
	}

	public void setTankfuellungenView(PagedListHolder<TankenModel> pages) {
		this.tankfuellungenView = pages;
	}

	public TankenViewModel(List<Tankfuellung> tankfuellungen) {
		super();
		List<TankenModel> list = new ArrayList<TankenModel>();
		for (Tankfuellung t : tankfuellungen) {
			list.add(new TankenModel(t));
		}
		this.tankfuellungenView.setSource(list);
	}

	
	public LinkedHashMap<Integer, Double> getKosten() {
		return kosten;
	}

	public void setKosten(LinkedHashMap<Integer, Double> kosten) {
		this.kosten = kosten;
	}

	public LinkedHashMap<Integer, Map<String, Double>> getPreisProLiter() {
		return preisProLiter;
	}

	public void setPreisProLiter(
			LinkedHashMap<Integer, Map<String, Double>> preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public LinkedHashMap<Integer, Integer> getKms() {
		return kms;
	}

	public void setKms(LinkedHashMap<Integer, Integer> kms) {
		this.kms = kms;
	}

	public LinkedHashMap<Integer, Double> getLiter() {
		return liter;
	}

	public void setLiter(LinkedHashMap<Integer, Double> liter) {
		this.liter = liter;
	}
}
