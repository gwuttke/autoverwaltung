package de.gw.auto.view.model;

import java.util.Comparator;

import de.gw.auto.domain.SonstigeAusgaben;

public class SonstigeAusgabenModel extends SonstigeAusgaben implements Comparator<SonstigeAusgaben>{
	
	public SonstigeAusgabenModel(SonstigeAusgaben sonstigeAusgaben){
		super(sonstigeAusgaben);
	}

	@Override
	public int compare(SonstigeAusgaben sonA1, SonstigeAusgaben sonA2) {
		return sonA2.getKmStand() - sonA1.getKmStand();
	}

}
