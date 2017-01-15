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

	private List<AuswertungProJahr> auswertungProJahre = new ArrayList<AuswertungProJahr>();;
	
	
	public TankenViewModel() {
		super();
	}
	
	public TankenViewModel(List<Tankfuellung> tankfuellungen) {
		this();
		List<TankenModel> list = new ArrayList<TankenModel>();
		for (Tankfuellung t : tankfuellungen) {
			list.add(new TankenModel(t));
		}
		this.tankfuellungenView.setSource(list);
	}

	public PagedListHolder<TankenModel> getTankfuellungenView() {
		return tankfuellungenView;
	}

	public void setTankfuellungenView(PagedListHolder<TankenModel> pages) {
		this.tankfuellungenView = pages;
	}

	public List<AuswertungProJahr> getAuswertungProJahre() {
		return auswertungProJahre;
	}

	public void setAuswertungProJahre(List<AuswertungProJahr> auswertungProJahre) {
		this.auswertungProJahre = auswertungProJahre;
	}

	
}
