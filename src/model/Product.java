package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Comparable<Product>, Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private long price;
	private String typeProduct;
	private String ingredients;
	private String size;
	private int quantity;
	private boolean status;
	
	private ArrayList<Ingredient> ingredientsList;
	private Type typeProductOb;
	private Size sizeProduct;
	
	public Product(String name, long price,Type typeProduct, Size sizeProduct, ArrayList<Ingredient> ingredients) {
		this.name = name;
		this.sizeProduct = sizeProduct;
		this.price = price;
		this.typeProductOb = typeProduct;
		this.sizeProduct = sizeProduct;
		ingredientsList = ingredients;
		componentsToString();
	}
	
	public void updateStatus(boolean status) {
		this.status = status;
	}
	
	public void componentsToString() {
		typeProduct = typeProductOb.getName();
		
		ingredients = "";
		for(int i = 0; i < ingredientsList.size(); i++) {
			ingredients += ingredientsList.get(i).getName() + ", ";
		}
		ingredients = ingredients.substring(0, ingredients.length() - 2);
		
		size = sizeProduct.getName();
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

	public boolean getStatus() {
		return status;
	}

	public ArrayList<Ingredient> getIngredientsList() {
		return ingredientsList;
	}

	public void setIngredientsList(ArrayList<Ingredient> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	public Type getTypeProductOb() {
		return typeProductOb;
	}

	public void setTypeProductOb(Type typeProductOb) {
		this.typeProductOb = typeProductOb;
	}

	public Size getSizeProduct() {
		return sizeProduct;
	}

	public void setSizeProduct(Size sizeProduct) {
		this.sizeProduct = sizeProduct;
	}
	
	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
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

	public void setSize(String size) {
		this.size = size;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Product o) {
		
		return (int) (price - o.price);
	}
	
}
