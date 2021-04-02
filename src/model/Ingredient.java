package model;

public class Ingredient extends Complement {
	private static final long serialVersionUID = 1L;
	
	private final static String txtCode = "IG";
	private static long numberCode;
	
	public Ingredient(String name) {
		super(name);
		numberCode++;
		super.setCode(txtCode + numberCode);
	}
}