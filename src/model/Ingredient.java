package model;

public class Ingredient implements Comparable<Ingredient>{
	private String name;
	
	public Ingredient(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Ingredient o) {	
		return name.compareTo(o.getName());
	}
}
