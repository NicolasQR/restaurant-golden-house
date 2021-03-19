package model;

import java.util.ArrayList;

public class Restaurant   {
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	
	public Restaurant() {
		
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
	
	
	
}
