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
	
	
}
