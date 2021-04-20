package model;

import java.io.Serializable;

public class User extends Employee implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	
	public User(String name, String lastName, long iD,String userName, String password, int numberOfOrdersCompleted) {
		super(name, lastName, iD);
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
