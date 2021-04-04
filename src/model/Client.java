package model;

import java.io.Serializable;

public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String lastName;
	private long ID;
	private String address;
	private long phone;
	private String observations;
	private boolean status;
	
	public Client(String name, String lastName, long ID, String address, long phone, String observations) {
		this.name = name;
		this.lastName = lastName;
		this.ID = ID;
		this.address = address;
		this.phone = phone;
		this.observations = observations;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getObservations() {
		return observations;
	}

	public boolean getStatus() {
		return status;
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
	
	public void addObservations(String observations) {
		this.observations = observations;
	}
	
	@Override
	public String toString() {
		return name + " " + lastName;
	}
}
