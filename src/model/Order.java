package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String status;
	private String employeeName;
	private String clientName;
	private String date;
	private String observations;
	
	private Date dateAndHour;
	private Client client;	
	private Employee employee;
	private ArrayList<Product> products;
	
	public Order(Employee employee, Date dateAndHour, Client client, ArrayList<Product> products,String status, String observations) {
		this.products = products;
		this.employee = employee;
		this.client = client;
		this.dateAndHour = dateAndHour;
		this.observations = observations;
		this.status = status;
		objectToString();
	}
	
	public void objectToString() {
		employeeName = employee.getName() + " " + employee.getLastName();
		clientName = client.getName() + " " + client.getLastName();
		DateFormat dateHour = new SimpleDateFormat("yyyy-MM-dd / HH:mm");
		date = dateHour.format(dateAndHour);
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
