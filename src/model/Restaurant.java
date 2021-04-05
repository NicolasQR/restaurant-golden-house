package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Restaurant   {
	
	public final static String SAVE_PATH_FILE_OF_USERS = "data/users.ap2";
	public final static String SAVE_PATH_FILE_OF_EMPLOYEES = "data/employees.ap2";
	public final static String SAVE_PATH_FILE_OF_PRODUCTS = "data/products.ap2";
	public final static String SAVE_PATH_FILE_OF_INGREDIENTS = "data/ingredients.ap2";
	public final static String SAVE_PATH_FILE_OF_PRODUCT_SIZE = "data/productsSize.ap2";
	public final static String SAVE_PATH_FILE_OF_PRODUCT_TYPE = "data/productsType.ap2";
	public final static String SAVE_PATH_FILE_OF_CLIENTS = "data/clients.ap2";
	public final static String SAVE_PATH_FILE_OF_ORDERS = "data/orders.ap2";
	public final static String FILESEPARATOR = ";";
	
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Type> productsType;
	private ArrayList<Size> productsSize;
	
	private ArrayList<Order> orders;
	
	private List<User> users;
	private List<Employee> employee;
	private List<Client> clients;
	
	public Restaurant() {
		
		
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		productsSize = new ArrayList<>();
		productsType = new ArrayList<>();
		orders = new ArrayList<Order>();
		
		users = new ArrayList<User>();
		employee = new ArrayList<Employee>();
		clients = new ArrayList<Client>();
		
		
	}
	
	public void exportDataofOrder(String fileName) {
		/**PrintWriter pw = new PrintWriter(fileName);
		
		for(int i = 0; i < orders.size(); i++) {
			 pw.println(orders.get(i).getName() + FILESEPARATOR + orders.get(i).getEmail());
		}
		pw.close();*/
	}
	
	public void exportDataofEmployee(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
		pw.println("NOMBRE" + FILESEPARATOR + "NUMERO DE PEDIDOS A CRGO" + FILESEPARATOR + "PRECIO TOTAL DE LOS PEDIDOS" );
		for(int i = 0; i < employee.size(); i++) {
			 pw.println(employee.get(i).getName() + " " + employee.get(i).getLastName() + FILESEPARATOR + employee.get(i).getNumberOfOrdersCompleted() +
					 FILESEPARATOR +employee.get(i).getTotalPriceOfOrderCompleted());
		}
		pw.close();
	}
	
	public boolean addOrder(Employee employee, Date dateAndHour, Client client, ArrayList<Product> products, String status, String observations) {
		boolean added = false;
		
		if(employee != null && client != null && products.size() > 0 && !status.isEmpty() && dateAndHour != null) {
			Order order = new Order(employee, dateAndHour, client, products, status, observations); 
			orders.add(order);
			added = true;
		}
		return added;
	}
	
	public void saveDataofOrder() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_ORDERS));
	    oos.writeObject(orders);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_ORDERS);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      orders = (ArrayList<Order>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public boolean addProduct(String name, long price,Type typeProduct, Size sizeProduct, ArrayList<Ingredient> ingredients) throws FileNotFoundException, IOException {
		
		boolean added = false;
		
			if(!name.isEmpty() && price >= 0 && typeProduct != null && sizeProduct != null && ingredients.size() != 0 && !ingredients.isEmpty()) {
				
				Product product = new Product(name, price, typeProduct, sizeProduct, ingredients);
				
				if(products.size() > 0) {
					int mini = -1;
					for(int i = 0; i < products.size(); i++) {
						
						if(product.compareTo(products.get(i)) < 0) {
							mini = i;
							i = products.size();
						} else if(product.compareTo(products.get(i)) == 0){
							mini = -2;
							i = ingredients.size();
						}
					}
					if(mini != -1 && mini != -2) {
						products.add(mini,product);
						added = true;
						System.out.println("si");
					} else if(mini != -2){
						products.add(product);
						added = true;
					} else {
						added = false;
					}
				}else {
					products.add(product);
					added = true;
				}
			}
			
		saveDataofProducts();
		return added;
	}
	
	public boolean updateProduct(int idx, String name, long price,Type typeProduct, Size sizeProduct, ArrayList<Ingredient> ingredients) throws FileNotFoundException, IOException {
		
		boolean updated = true;
			
		for (int i = 0; i < products.size(); i++) {
			
			if(ingredients.containsAll(products.get(i).getIngredientsList()) 
					&& products.get(i).getIngredientsList().containsAll(ingredients) && name.equals(products.get(i).getName())) {
				i = products.size();
				updated = false;
			}
		}
		
		if(updated) {
			products.get(idx).setName(name);
			products.get(idx).setPrice(price);
			products.get(idx).setTypeProductOb(typeProduct);
			products.get(idx).setSizeProduct(sizeProduct);
			products.get(idx).setIngredientsList(ingredients);
			products.get(idx).componentsToString();
		}
		saveDataofProducts();
		return updated;
	}
	
	public void deleteProduct(int idx) throws FileNotFoundException, IOException {
		products.remove(idx);
		saveDataofProducts();
	}
	
	public void saveDataofProducts() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_PRODUCTS));
	    oos.writeObject(products);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofProducts() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_PRODUCTS);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      products = (ArrayList<Product>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public boolean addIngredient(String name) throws FileNotFoundException, IOException {
		
		boolean added = false;
		
		if(!name.isEmpty()) {
			
			Ingredient ingredient = new Ingredient(name);
			if(ingredients.size() > 0) {
				int mini = -1;
				for(int i = 0; i < ingredients.size(); i++) {
					
					if(ingredient.compareTo(ingredients.get(i)) < 0) {
						mini = i;
						i = ingredients.size();
					} else if(ingredient.compareTo(ingredients.get(i)) == 0){
						mini = -2;
						i = ingredients.size();
					}
				}
				if(mini != -1 && mini != -2) {
					ingredients.add(mini,ingredient);
					added = true;
				} else if(mini != -2){
					ingredients.add(ingredient);
					added = true;
				} else {
					added = false;
				}
			}else {
				ingredients.add(ingredient);
				added = true;
			}
		}
		saveDataofIngredient();
		return added;
	}
	
	public boolean updateIngredient(int idx, String name) {
		boolean updated = false;
		Ingredient temp = new Ingredient(name);
		
		if(ingredients.size() > idx) {
			
			int pos = -1;
			int i = 0;
			int j = ingredients.size() - 1;
			
			while (i <= j && pos < 0) {
				int m = (i+j)/2;
				if(temp.compareTo(ingredients.get(i)) == 0) {
					pos = m;
					updated = false;
				} else if(temp.compareTo(ingredients.get(i)) > 0) {
					i = m+1;
				} else {
					j = m-1;	
			
				}
			}
			
			if(updated) {
				ingredients.get(idx).setName(name);
			}
		}
		return updated;
	}
		
	public void saveDataofIngredient() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_INGREDIENTS));
	    oos.writeObject(ingredients);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofIngredients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_INGREDIENTS);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      ingredients = (ArrayList<Ingredient>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public boolean addProductType(String name) throws FileNotFoundException, IOException {
		boolean added = false;
				
			if(!name.isEmpty()) {
			
				Type productType = new Type(name);
				if(productsType.size() > 0) {
					int mini = -1;
					for(int i = 0; i < productsType.size(); i++) {
						
						if(productType.compareTo(productsType.get(i)) < 0) {
							mini = i;
							i = productsType.size();
						} else if(productType.compareTo(productsType.get(i)) == 0) {
							mini = -2;
							i = productsType.size();
						}
					}
					if(mini != -1 && mini != -2){
						productsType.add(mini, productType);
						added = true;
					} else if(mini != -2){
						productsType.add(productType);
						added = true;
					} else {
						added = false;
					}
				}else {
					productsType.add(productType);
					added = true;
				}
		}
		saveDataofProductType();
		return added;
	}
	

	public void saveDataofProductType() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_PRODUCT_TYPE));
	    oos.writeObject(productsType);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofProductType() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_PRODUCT_TYPE);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      productsType = (ArrayList<Type>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public boolean updateType(int idx, String name) {
		boolean updated = true;
		Type temp = new Type(name);
		
		if(productsType.size() > idx) {
			
			int pos = -1;
			int i = 0;
			int j = productsType.size() - 1;
			
			while (i <= j && pos < 0) {
				int m = (i+j)/2;
				if(temp.compareTo(productsType.get(i)) == 0) {
					pos = m;
					updated = false;
				} else if(temp.compareTo(productsType.get(i)) > 0) {
					i = m+1;
				} else {
					j = m-1;	
			
				}
			}
			
			if(updated) {
				productsType.get(idx).setName(name);
			}
		}
		return updated;

	}
	
	public boolean addProductSize(String name) throws FileNotFoundException, IOException {
			boolean added = false;
			
			if(!name.isEmpty()) {
			
				Size productSize = new Size(name);
				if(productsSize.size() > 0) {
					int mini = -1;
					for(int i = 0; i < productsSize.size(); i++) {
						
						if(productSize.compareTo(productsSize.get(i)) < 0) {
							mini = i;
							i = productsSize.size();
						} else if(productSize.compareTo(productsSize.get(i)) == 0) {
							mini = -2;
							i = productsSize.size();
						}
					}
					if(mini != -1 && mini != -2){
						productsSize.add(mini, productSize);
						added = true;
					} else if(mini != -2){
						productsSize.add(productSize);
						added = true;
					} else {
						added = false;
					}
				}else {
					productsSize.add(productSize);
					added = true;
				}
		}
		saveDataofProductSize();
		return added;
	}
	
	public boolean updateSize(int idx, String name) {
		boolean updated = true;
		Size temp = new Size(name);
		
		if(productsSize.size() > idx) {
			
			int pos = -1;
			int i = 0;
			int j = productsSize.size() - 1;
			
			while (i <= j && pos < 0) {
				int m = (i+j)/2;
				if(temp.compareTo(productsSize.get(i)) == 0) {
					pos = m;
					updated = false;
				} else if(temp.compareTo(productsSize.get(i)) > 0) {
					i = m+1;
				} else {
					j = m-1;
			
				}
			}
			
			if(updated) {
				productsSize.get(idx).setName(name);
			}
		}
		return updated;
	}

	public void saveDataofProductSize() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_PRODUCT_SIZE));
	    oos.writeObject(productsSize);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofProductsSize() throws IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_PRODUCT_SIZE);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      productsSize = (ArrayList<Size>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public void addUsers(String name, String lastName, long iD, String userName, String password) throws IOException {
		users.add(new User(name, lastName, iD, userName, password, 0));
		saveDataofUsers();
	}
	
	public void saveDataofUsers() throws IOException{
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_USERS));
	    oos.writeObject(users);
	    oos.close();
	 }
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofUsers() throws IOException, ClassNotFoundException{
	    File f = new File(SAVE_PATH_FILE_OF_USERS);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      users = (List<User>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	  }
	
	
	public void addEmployee(String name, String lastName, long iD) throws FileNotFoundException, IOException {
		employee.add(new Employee(name, lastName, iD));
		ComparatorEmployee();
		saveDataOfEmployees();
	}
	
	public void updateEmployee(int index, String name, String lastName, long ID) throws FileNotFoundException, IOException {
		employee.get(index).setName(name);
		employee.get(index).setLastName(lastName);
		employee.get(index).setID(ID);
    	saveDataOfEmployees();
	}
	
	public void saveDataOfEmployees() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_EMPLOYEES));
	    oos.writeObject(employee);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDatafEmployee() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_EMPLOYEES);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      employee = (List<Employee>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public boolean addClient(String name, String lastName, long ID, String address, long phone, String observations) throws FileNotFoundException, IOException {
		
		boolean added = false;
		
		if(!name.isEmpty()) {
			
			Client client = new Client(name, observations, phone, observations, phone, observations);
			if(clients.size() > 0) {
				int mini = -1;
				for(int i = 0; i < clients.size(); i++) {
					
					if(client.compareTo(clients.get(i)) < 0) {
						mini = i;
						i = clients.size();
					} else if(client.compareTo(clients.get(i)) == 0){
						mini = -2;
						i = clients.size();
					}
				}
				if(mini != -1 && mini != -2) {
					clients.add(mini,client);
					added = true;
				} else if(mini != -2){
					clients.add(client);
					added = true;
				} else {
					added = false;
				}
			}else {
				clients.add(client);
				added = true;
			}
		}
		saveDataofClient();
		return added;
		
		//clients.add(new Client(name, lastName, ID, address, phone, observations));
		
	}
	
	public void updateClient(int index, String name, String lastName, long ID, String address, long phone) throws FileNotFoundException, IOException {
		clients.get(index).setName(name);
		clients.get(index).setLastName(lastName);
		clients.get(index).setID(ID);
		clients.get(index).setAddress(address);
		clients.get(index).setPhone(phone);
    	saveDataofClient();
	}
	
	public void saveDataofClient() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_OF_CLIENTS));
	    oos.writeObject(clients);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofClients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH_FILE_OF_CLIENTS);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      clients = (List<Client>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	}
	
	public List<Client> getClients(){
		return clients;
	}
	
	public List<User> getUsers(){
		return users; 
	}
	
	public List<Employee> getEmployee(){
		return employee;
	}
	

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<Type> getProductTypes() {
		return productsType;
	}

	public void setProductTypes(ArrayList<Type> productTypes) {
		this.productsType = productTypes;
	}
	
	public ArrayList<Size> getProductsSize() {
		return productsSize;
	}

	public void setProductsSize(ArrayList<Size> productsSize) {
		this.productsSize = productsSize;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public void ComparatorEmployee() {
		ComparatorEmployee ce = new ComparatorEmployee();
		Collections.sort(employee,ce);
	}
	
}