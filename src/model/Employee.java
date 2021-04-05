package model;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private String lastName;
	private long ID;
	private boolean status;
	private int numberOfOrdersCompleted;
	private int totalPriceOfOrderCompleted;
	
	public Employee(String name, String lastName, long iD ) {
		this.name = name;
		this.lastName = lastName;
		numberOfOrdersCompleted = 0;
		totalPriceOfOrderCompleted = 0;
		ID = iD;
		status = true;
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
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return name + " " + lastName;
	}

	public int getNumberOfOrdersCompleted() {
		return numberOfOrdersCompleted;
	}

	public void numberOfOrdersCompleted() {
		numberOfOrdersCompleted++;
	}

	public int getTotalPriceOfOrderCompleted() {
		return totalPriceOfOrderCompleted;
	}

	public void totalPriceOfOrderCompleted(int a) {
		totalPriceOfOrderCompleted += a;
	}
}
