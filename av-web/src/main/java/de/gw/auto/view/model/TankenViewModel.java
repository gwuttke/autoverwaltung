package de.gw.auto.view.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;

public class TankenViewModel {
	private PagedListHolder<TankenModel> tankfuellungenView = new PagedListHolder<TankenModel>();

	/**
	 * index 0 = eingestelltes Jahr <br>
	 * index 1 = eingestelltes Jahr -1 <br>
	 * index 2 = gesamt
	 */
	private double[] kosten = new double[3];

	/**
	 * index 0 = eingestelltes Jahr <br>
	 * index 1 = eingestelltes Jahr -1 <br>
	 * index 2 = gesamt
	 */
	private double[] maxPreisProLiter = new double[3];

	/**
	 * index 0 = eingestelltes Jahr <br>
	 * index 1 = eingestelltes Jahr -1 <br>
	 * index 2 = gesamt
	 */
	private double[] minPreisProLiter = new double[3];

	/**
	 * index 0 = eingestelltes Jahr <br>
	 * index 1 = eingestelltes Jahr -1 <br>
	 * index 2 = gesamt
	 */
	private double[] avgPreisProLiter = new double[3];

	/**
	 * index 0 = eingestelltes Jahr <br>
	 * index 1 = eingestelltes Jahr -1 <br>
	 * index 2 = gesamt
	 */
	private int[] km = new int[3];

	/**
	 * index 0 = eingestelltes Jahr <br>
	 * index 1 = eingestelltes Jahr -1 <br>
	 * index 2 = gesamt
	 */
	private double[] liter = new double[3];
	
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

	public double[] getKosten() {
		return kosten;
	}

	public void setKosten(double[] kosten) {
		this.kosten = kosten;
	}

	public double[] getMaxPreisProLiter() {
		return maxPreisProLiter;
	}

	public void setMaxPreisProLiter(double[] maxPreisProLiter) {
		this.maxPreisProLiter = maxPreisProLiter;
	}

	public double[] getMinPreisProLiter() {
		return minPreisProLiter;
	}

	public void setMinPreisProLiter(double[] minPreisProLiter) {
		this.minPreisProLiter = minPreisProLiter;
	}

	public double[] getAvgPreisProLiter() {
		return avgPreisProLiter;
	}

	public void setAvgPreisProLiter(double[] avgPreisProLiter) {
		this.avgPreisProLiter = avgPreisProLiter;
	}

	public int[] getKm() {
		return km;
	}

	public void setKm(int[] km) {
		this.km = km;
	}

	public double[] getLiter() {
		return liter;
	}

	public void setLiter(double[] liter) {
		this.liter = liter;
	}
}
