package model;

public class Ingredient implements Comparable<Ingredient>{
	private final static String txtCode = "IG";
	private static long numberCode;
	
	private String name;
	private String code;
	private boolean status;
	
	public Ingredient(String name) {
		this.name = name;
		status = false;
		code = txtCode + generateCode();
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long generateCode() {
		return numberCode++;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(Ingredient o) {	
		return name.compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return name;
	} 
}
