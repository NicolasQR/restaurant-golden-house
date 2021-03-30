package model;

public class Ingredient extends Complement{
	private final static String txtCode = "IG";
	private static long numberCode;
	
	public Ingredient(String name) {
		super(name);
		super.setCode(txtCode + generateCode());
	}
	public long generateCode() {
		return numberCode++;
	}
}
