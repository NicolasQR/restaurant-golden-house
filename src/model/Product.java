package model;

import java.util.ArrayList;

public class Product implements Comparable<Product>{
	private String name;
	private String size;
	private long price;
	private boolean status;
	private ArrayList<Ingredient> ingredients;
	private Type typeProduct;
	
	public Product(String name, String size, long price, String typeProduct) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.typeProduct = new Type(typeProduct); 
		ingredients = new ArrayList<Ingredient>();
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
	
	public void addIngredient(String name) {
		Ingredient ingredient = new Ingredient(name);
		ingredients.add(ingredient); 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean getStatus() {
		return status;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Type getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(Type typeProduct) {
		this.typeProduct = typeProduct;
	}

	@Override
	public int compareTo(Product o) {
		
		return (int) (price - o.price);
	}
	
}
