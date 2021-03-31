package model;

import java.io.Serializable;

public class Complement implements Comparable<Complement>, Serializable{
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private boolean status;
	
	public Complement(String name) {
		status = true;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getStatus() {
		return status;
	}

	public void updateStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int compareTo(Complement o) {
		return name.compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return name;
	}
}
