package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Restaurant   {
	
	public final static String SAVE_PATH_FILE = "data/users.ap2";
	
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Type> productsType;
	private ArrayList<Size> productsSize;
	
	
	private static List<User> users;
	private List<Employee> employee;
	
	public Restaurant() {
		
		
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		productsSize = new ArrayList<>();
		productsType = new ArrayList<>();
		
		users = new ArrayList<User>();
		employee = new ArrayList<Employee>();
		
		
	}
	
	public boolean addProduct(String name, long price,Type typeProduct, Size sizeProduct, ArrayList<Ingredient> ingredients) {
		
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
		
		return added;
	}
	
	public List<Product> getProduct(){
		return products;
	}
	
	public boolean addIngredient(String name) {
		
		boolean added = false;
		
		if(name != null) {
			
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
		
		return added;
	}
	
	public boolean addProductType(String name) {
		boolean added = false;
				
			if(name != null) {
			
				Type productType = new Type(name);
				if(productsType.size() > 0) {
					int mini = -1;
					for(int i = 0; i < productsType.size(); i++) {
						
						if(productType.compareTo(productsType.get(i)) < 0) {
							mini = i;
							i = productsType.size();
						} else if(productType.compareTo(productsType.get(i)) == 0) {
							mini = -2;
							i = products.size();
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
				
		return added;
	}
	
	public boolean addProductSize(String name) {
		boolean added = false;
				
			if(name != null) {
			
			Size size = new Size(name);
				if(productsSize.size() > 0) {
					int mini = -1;
					for(int i = 0; i < productsSize.size(); i++) {
						
						if(size.compareTo(productsSize.get(i)) < 0) {
							mini = i;
							i = productsSize.size();
						}else if(size.compareTo(productsSize.get(i)) == 0) {
							mini = -2;
							i = productsSize.size();
						}
						
						if(mini != -1 && mini != -2) {
							productsSize.add(mini, size);
							added = true;
						} else if(mini != -2){
							productsSize.add(size);
							added = true;
						} else {
							added = false;
						}
					}
				} else {
					productsSize.add(size);
					added = true;
				}
			}
		return added;
	}
	
	public void addUsers(String name, String lastName, long iD, String userName, String password) throws IOException {
		users.add(new User(name, lastName, iD, userName, password));
		saveDataofUsers();
	}
	
	public void saveDataofUsers() throws IOException{
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE));
	    oos.writeObject(users);
	    oos.close();
	 }
	
	@SuppressWarnings("unchecked")
	public boolean loadDataofUsers() throws IOException, ClassNotFoundException{
	    File f = new File(SAVE_PATH_FILE);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      users = (List<User>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
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
	
	public void brayan() {
		System.out.println("Que ondaaaasasnasajsbahjsbajbajab");
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

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
}