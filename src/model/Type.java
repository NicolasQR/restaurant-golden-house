package model;

public class Type extends Complement{

	
	private static final long serialVersionUID = 1L;
	private final static String txtCode = "TY";
	private static long numberCode;
	
	
	public Type(String name) {
		super(name);
		super.setCode(txtCode + generateCode());
	}

	public long generateCode() {
		return numberCode++;
	}
}
