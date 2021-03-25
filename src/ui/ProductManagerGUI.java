package ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
 
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

	    private ArrayList<Product> products;
	    
	    public ProductManagerGUI() {
	    	products = new ArrayList<Product>();
	    }
	    
	    public void receiveData(ArrayList<Product> products) {
	    	this.products = products;
	    }
	    
	    public void loadTableView() {
	    	ObservableList<Product> accounts = FXCollections.observableArrayList(products);
	    	
		    	tableViewProducts.setItems(accounts);
		    	tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		    	tableType.setCellValueFactory(new PropertyValueFactory<Product, String>("typeProduct"));
		    	tableIngredients.setCellValueFactory(new PropertyValueFactory<Product, String>("ingredients"));
		    	tableSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
		    	tablePrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
	    }
	    
		@FXML
	    public void deactivateProduct(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    public void deleteProduct(ActionEvent event) {

	    }

	    @FXML
	    public void enableProduct(ActionEvent event) {

	    }
	    
	    @FXML
	    public void newProduct(ActionEvent event) {
	    	
	    }

	    @FXML
	    public void updateProduct(ActionEvent event) {

	    }

		public ArrayList<Product> getProducts() {
			return products;
		}

		public void setProducts(ArrayList<Product> products) {
			this.products = products;
		}
		
		public void initialize() {
			
		}
}
