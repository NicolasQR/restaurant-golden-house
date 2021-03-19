package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

	private List<User> users;
	private List<Employee> employee;
	
	public Restaurant() {
		users = new ArrayList<User>();
		employee = new ArrayList<Employee>();
	}
	
	public void addUsers(String name, String lastName, long iD, String userName, String password) {
		users.add(new User(name, lastName, iD, userName, password));
	}
	
	public void addEmployee(String name, String lastName, long iD) {
		employee.add(new Employee(name, lastName, iD));
	}
	
	public List<User> getUsers(){
		return users; 
	}
	
	public List<Employee> getEmployee(){
		return employee;
	}
}

