package model;

import java.io.Serializable;

public class Ingredient extends Complement implements Serializable{

	private static final long serialVersionUID = 1L;
	private final static String txtCode = "IG";
	
	private static long numberCode;
	
	public Ingredient(String name) {
		super(name);
		numberCode = generateCode();
		super.setCode(txtCode + numberCode);
	}
	public long generateCode() {
		return numberCode++;
	}
}