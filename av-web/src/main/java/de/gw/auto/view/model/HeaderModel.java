package de.gw.auto.view.model;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Auto;
import de.gw.auto.service.RegisteredUser;

public class HeaderModel {
	
	AutoModelShow currentAutoModel;
	
	List<AutoModelShow> autoModels = new ArrayList<AutoModelShow>();
	
	
	
	public HeaderModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public HeaderModel(RegisteredUser user) {
		addAll(user);
		currentAutoModel = new AutoModelShow(user.getCurrentAuto());
	}
	

	public AutoModelShow getCurrentAutoModel() {
		return currentAutoModel;
	}


	public void setCurrentAutoModel(AutoModelShow currentAutoModel) {
		this.currentAutoModel = currentAutoModel;
	}


	public List<AutoModelShow> getAutoModels() {
		return autoModels;
	}


	public void setAutoModels(List<AutoModelShow> autoModels) {
		this.autoModels = autoModels;
	}


	public void addAll(RegisteredUser user){
		for(Auto a : user.getAutos()){
			add(a);
		}
		autoModels.add(0, new AutoModelShow(user.getCurrentAuto()));
	}
	
	public void add(Auto auto){
		autoModels.add(new AutoModelShow(auto));
	}
}
