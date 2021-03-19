package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant   {
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	
	private List<User> users;
	private List<Employee> employee;
	
	public Restaurant() {
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		
		users = new ArrayList<User>();
		employee = new ArrayList<Employee>();
	}
	
	public boolean addProduct(String name, String size, long price, String typeProduct) {
		
		boolean added = false;
		
			if(name != null && size != null && price > 0 && typeProduct != null) {
				
				Product product = new Product(name, size, price, typeProduct);
				
				for(int i = 0; i < products.size(); i++) {
					int mini = 0;
					if(product.compareTo(products.get(i)) < 0) {
						mini = i;
					}
					Product aux = products.get(i);
					products.set(i, products.get(mini));
					products.set(mini, aux);
				}
				added = true;
			}
		
		return added;
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