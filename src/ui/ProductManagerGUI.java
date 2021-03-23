package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.Restaurant;

public class ProductManagerGUI {
	
	    @FXML
	    private TableView<Product> tableViewProducts;

	    @FXML
	    private TableColumn<Product, String> tableName;

	    @FXML
	    private TableColumn<Product, String> tableType;

	    @FXML
	    private TableColumn<Product, String> tableIngredients;

	    @FXML
	    private TableColumn<Product, String> tableSize;

	    @FXML
	    private TableColumn<Product, String> tablePrice;

	    private Restaurant restaurant;
	    
	    public ProductManagerGUI() {
	    	restaurant = new Restaurant();
	    }
	    
	    public Restaurant getRestaurant() {
			return restaurant;
		}


		public void setRestaurant(Restaurant restaurant) {
			this.restaurant = restaurant;
		}

		@FXML
	    void deactivateProduct(ActionEvent event) {
	    	
	    }
	    
	    
	    
	    @FXML
	    void deleteProduct(ActionEvent event) {

	    }

	    @FXML
	    void enableProduct(ActionEvent event) {

	    }

	    @FXML
	    void newProduct(ActionEvent event) {
	    	ObservableList<Product> accounts = FXCollections.observableArrayList(restaurant.getProducts());
	    	
	    	tableViewProducts.setItems(accounts);
	    	tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	    	tableType.setCellValueFactory(new PropertyValueFactory<Product, String>(""));
	    	tableIngredients.setCellValueFactory(new PropertyValueFactory<Product, String>(""));
	    	tableSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
	    	tablePrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
	    }

	    @FXML
	    void updateProduct(ActionEvent event) {

	    }
}
