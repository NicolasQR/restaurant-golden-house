package model;

public class Size extends Complement{
	
	private final static String txtCode = "IG";
	private static long numberCode;
	
	public Size(String name) {
		super(name);
		super.setCode(txtCode + generateCode());
	}
	
	public long generateCode() {
		return numberCode++;
	}
}
