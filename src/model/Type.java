package model;

public class Type implements Comparable<Type>{
	private String name;

	public Type(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Type o) {
		return name.compareTo(o.getName());
	}
}
