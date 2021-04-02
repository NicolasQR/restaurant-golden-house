package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Ingredient;
import model.Product;
import model.Restaurant;
import model.Size;
import model.Type;

public class UpdateProductGUI {
	@FXML
    private TextField txtName;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ComboBox<Type> comboType;

    @FXML
    private ComboBox<Size> comboSyze;

    @FXML
    private ListView<Ingredient> listIngredients;

    @FXML
    private ListView<Ingredient> listAddedIngredients;
    
    private Restaurant restaurant;
    private Product product;
    private int idx;
    
    public UpdateProductGUI() {
		restaurant = new Restaurant();
    }
    
    public void receiveData(int idx, Product product, Restaurant restaurant) {
    	this.restaurant = restaurant;
    	this.product = product;
    	this.idx = idx;
    }
    
    public void initialize() {
    	
    	txtName.setText(product.getName());
    	txtPrecio.setText(String.valueOf(product.getPrice()));
    	
    	ObservableList<Type> types = FXCollections.observableArrayList(restaurant.getProductTypes());
    	comboType.setItems(types);
    	comboType.getSelectionModel().select(product.getTypeProductOb());
    	
    	ObservableList<Size> sizes = FXCollections.observableArrayList(restaurant.getProductsSize());
    	comboSyze.setItems(sizes);
    	comboSyze.getSelectionModel().select(product.getSizeProduct());
    	
    	ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(restaurant.getIngredients());
    	listIngredients.setItems(ingredients);
    	
    	ObservableList<Ingredient> addedIngredients = FXCollections.observableArrayList(product.getIngredientsList());
    	listAddedIngredients.setItems(addedIngredients);
    }
    
    public Restaurant getRestaurant() {
    	return restaurant;
    }
    
    @FXML
    public void addIngredient(ActionEvent event) {
    	if(listIngredients.getSelectionModel() != null) {
    		Ingredient ingredient = listIngredients.getFocusModel().getFocusedItem();		
			if(listAddedIngredients.getItems().size() > 0 && ingredient != null) {
				int mini = -1;
				for(int i = 0; i < listAddedIngredients.getItems().size(); i++) {
					
					if(ingredient.compareTo(listAddedIngredients.getItems().get(i)) < 0) {
						mini = i;
						i = listAddedIngredients.getItems().size();
					} else if(ingredient.compareTo(listAddedIngredients.getItems().get(i)) == 0){
						mini = -2;
						i = listAddedIngredients.getItems().size();
					}
				}
				if(mini != -1 && mini != -2) {
					listAddedIngredients.getItems().add(mini,ingredient);
				} else if(mini != -2){
					listAddedIngredients.getItems().add(ingredient);
				}
			}else if(ingredient != null){
				listAddedIngredients.getItems().add(ingredient);
			}
		}
    }

    @FXML
    public void removeIngredient(ActionEvent event) {
    	if(!listAddedIngredients.getSelectionModel().isEmpty()) {
    		int idx = listAddedIngredients.getSelectionModel().getSelectedIndex();
    		listAddedIngredients.getItems().remove(idx);
    	}
    }

	@FXML
    public void updateProduct(ActionEvent event) throws FileNotFoundException, IOException {
     	
    	boolean update = false;
    	
    	try {
			if(!txtName.getText().isEmpty() && !txtPrecio.getText().isEmpty() && comboType.getValue() != null
					&& comboSyze.getValue() != null && !listAddedIngredients.getItems().isEmpty()) {
				
				ArrayList<Ingredient> ingredients = new ArrayList<>(listAddedIngredients.getItems());
				update = restaurant.updateProduct(idx, txtName.getText(), Long.parseLong(txtPrecio.getText()), comboType.getValue(),
						comboSyze.getValue(),ingredients);
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Empty fields");
				alert.setHeaderText(null); 
				alert.setContentText("Rellene todos los campos.");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Number format");
    		alert.setHeaderText(null);
    		alert.setContentText("El formato del precio es erroneo.");
    		alert.showAndWait();
		}
    	
    	if(update) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Successfully update");
    		alert.setHeaderText(null);
    		alert.setContentText("Producto actualizado con éxito.");
    		alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Type existing");
    		alert.setHeaderText(null);
    		alert.setContentText("Producto en existencia.");
    		alert.showAndWait();
		}
    }
}
