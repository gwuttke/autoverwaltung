package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Benzinart;
import de.gw.auto.hibernate.DatenAbrufen;

public class BenzinartDAO {

	private List<Benzinart> benzinartList = new ArrayList<Benzinart>();

	public BenzinartDAO() {
		setBenzinList();
	}
	
	public List<Benzinart> getBenzinartList() {
		return benzinartList;
	}
	
	public  Benzinart searchBenzinartById(int id){
		for(Benzinart ba : benzinartList){
			if(ba.getId().equals(id)){
				return ba;
			}
		}
		return null;
	}

	public void setBenzinList(){
		benzinartList =  new DatenAbrufen().getBenzinarten();
	}
	

}
