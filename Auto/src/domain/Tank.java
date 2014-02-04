package domain;

public class Tank {

	private int id;
	private String beschreibung;

	
	public Tank(){
		
	}
	public Tank(String beschreibung, int id){
		if(beschreibung == null || beschreibung == ""){
		
		}
		
		
		this.beschreibung = beschreibung;
		this.id = id;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public int getId() {
		return this.id;
	}

}
