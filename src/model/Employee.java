package model;

public class Employee {
	private String name;
	private String lastName;
	private long ID;
	private boolean status;
	
	public Employee(String name, String lastName, long iD) {
		this.name = name;
		this.lastName = lastName;
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
}
