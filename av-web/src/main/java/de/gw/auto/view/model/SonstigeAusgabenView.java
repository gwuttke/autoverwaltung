package de.gw.auto.view.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import de.gw.auto.domain.SonstigeAusgaben;

public class SonstigeAusgabenView {
	private PagedListHolder<SonstigeAusgabenModel> pages = new PagedListHolder<SonstigeAusgabenModel>();

	public SonstigeAusgabenView() {
		super();
	}

	public SonstigeAusgabenView(List<SonstigeAusgaben> sonstigeAusgaben) {
		this();
		List<SonstigeAusgabenModel> sonstigeAusgabenModel = new ArrayList<SonstigeAusgabenModel>();
		for (SonstigeAusgaben sa : sonstigeAusgaben) {
			sonstigeAusgabenModel.add(new SonstigeAusgabenModel(sa));
		}
		this.pages.setSource(sonstigeAusgabenModel);
	}

	public PagedListHolder<SonstigeAusgabenModel> getPages() {
		return pages;
	}

	public void setPages(PagedListHolder<SonstigeAusgabenModel> pages) {
		this.pages = pages;
	}

}
