package model;

public class Size extends Complement {
	private static final long serialVersionUID = 1L;
	
	private final static String txtCode = "IG";
	private static long numberCode;
	
	public Size(String name) {
		super(name);
		numberCode++;
		super.setCode(txtCode + numberCode);
	}
}