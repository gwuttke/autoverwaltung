package domain;

public class Benzinart {

	private String name;
	private Integer id;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Benzinart(){
		
	}


	public Benzinart(String name, Integer id) {
		super();
		if (name.equals(null)){
			throw new IllegalArgumentException("Der Name ist nicht gueltig");
		}
		this.name = name;
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
