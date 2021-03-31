package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Client;
import model.Employee;
import model.Product;
import model.Restaurant;

public class CreateOrderGUI {
		
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

	    @FXML
	    private TableView<Product> tableAddedProducts;

	    @FXML
	    private TableColumn<Product, String> columName;

	    @FXML
	    private TableColumn<Product, String> columPrice;

	    @FXML
	    private TableColumn<Product, String> columQuantity;

	    @FXML
	    private Spinner<Integer> ingredientQuantity;

	    @FXML
	    private TextField txtSelectedIngredient;

	    @FXML
	    private ComboBox<Client> comboClient;

	    @FXML
	    private ComboBox<Employee> ComboEmployee;

	    @FXML
	    private ComboBox<String> comboStatus;
	    
	    @FXML
	    private DatePicker datePicker;

	    @FXML
	    private TextField txtHour;

	    @FXML
	    private TextField txtMinutes;

	    @FXML
	    private TextArea txtAreaObservations;
	    
	    private Restaurant restaurant;
	    
	    public CreateOrderGUI() {
			restaurant = new Restaurant();
		}
	    
	    public void loadTableProducts() {
	    	ObservableList<Product> products = FXCollections.observableArrayList(restaurant.getProducts());
	    	
	    	tableViewProducts.setItems(products);
	    	tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	    	tableType.setCellValueFactory(new PropertyValueFactory<Product, String>("typeProduct"));
	    	tableIngredients.setCellValueFactory(new PropertyValueFactory<Product, String>("ingredients"));
	    	tableSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
	    	tablePrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
	    }
	    
	    public void initialize() {
	    	
	    }
	    
	    @FXML
	    public void addProduct(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    public void createOrder(ActionEvent event) {

	    }
	    
	    @FXML
	    public void selectItem(MouseEvent event) {
	    	Product p = tableViewProducts.getSelectionModel().getSelectedItem();
	    	
	    	if(p != null) {
	    		
	    	}
	    }
}
