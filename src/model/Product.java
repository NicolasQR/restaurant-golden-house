package model;

import java.util.ArrayList;

public class Product implements Comparable<Product>{
	private String name;
	private String size;
	private long price;
	private String ingredients;
	private String typeProduct; 
	private boolean status;
	
	private ArrayList<Ingredient> ingredientsList;
	private Type typeProductOb;
	private Size sizeProduct;
	
	public Product(String name, String size, long price, String typeProduct, String sizeProduct) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.typeProductOb = new Type(typeProduct);
		this.sizeProduct = new Size(sizeProduct);
		ingredientsList = new ArrayList<Ingredient>();
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void addIngredient(String name) {
		Ingredient ingredient = new Ingredient(name);
		ingredientsList.add(ingredient); 
	}
	
	public ArrayList<Ingredient> getIngredientsList(){
		return ingredientsList;
	}
	
	public void updateType(String name) {
		Type typeProduct = new Type(name);
		this.typeProductOb = typeProduct;
	}
	
	public Type getTypeProductOb() {
		return typeProductOb;
	}
	
	public void updateSize(String name) {
		Size sizeProduct = new Size(name);
		this.sizeProduct = sizeProduct;
	}
	
	public Size getProductsSize() {
		return sizeProduct;
	}
	
	public void complementsToString() {
		
		if(typeProductOb != null) {
			typeProduct = typeProductOb.getName().toString();
		}
		
		if(sizeProduct != null) {
			size = sizeProduct.getName().toString();
		}
		
		for(int i = 0; i < ingredientsList.size(); i++) {
			ingredients += ingredientsList.get(i).getName() + ", ";
		}
		
		ingredients = ingredients.substring(0, ingredients.length()-2);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getSize() {
		return size;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

	public String getTypeProduct() {
		return typeProduct;
	}
	
	@Override
	public int compareTo(Product o) {
		
		return (int) (price - o.price);
	}
	
}
