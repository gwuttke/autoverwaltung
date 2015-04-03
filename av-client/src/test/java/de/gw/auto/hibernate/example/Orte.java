package de.gw.auto.hibernate.example;

import java.util.HashSet;
import java.util.Set;

import de.gw.auto.domain.Ort;


public class Orte {

	public Orte() {
		this.load();
	}
	Set<Ort> orte = new HashSet<Ort>();
	
	public void load(){
	Ort m = new Ort("München");
	Ort neuried = new Ort("Neuried");
	
	orte.add(m);
	orte.add(neuried);
	}
	
	public Set<Ort> getOrte() {
		return orte;
	}
	
	public Ort getOrt(int id){
		for (Ort o : orte){
			if (o.getId() == id){
				return o;
			}
		}
		return null;
	}
	
	public Ort getOrt(String name){
		for (Ort o : orte){
			if (o.getOrt() == name){
				return o;
			}
		}
		return null;	
	}
}
