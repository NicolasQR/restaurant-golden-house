package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private int code;
	private String status;
	private String employeeName;
	private Date dateAndHour;
	private String observations;
	private Client client;
	private ArrayList<Product> products;
	private ArrayList<Integer> quantityProducts;
	
	public Order(String employeeName, Date dateAndHour, Client client) {
		this.employeeName = employeeName;
		this.dateAndHour = dateAndHour;
		this.client = client;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getDateAndHour() {
		return dateAndHour;
	}

	public void setDateAndHour(Date dateAndHour) {
		this.dateAndHour = dateAndHour;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Integer> getQuantityProducts() {
		return quantityProducts;
	}

	public void setQuantityProducts(ArrayList<Integer> quantityProducts) {
		this.quantityProducts = quantityProducts;
	}
	
	
}
