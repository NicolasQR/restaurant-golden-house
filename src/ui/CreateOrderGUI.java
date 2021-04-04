package ui;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
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
	    private TableColumn<Product,String> tableType;

	    @FXML
	    private TableColumn<Product, String> tableIngredients;

	    @FXML
	    private TableColumn<Product, String> tableSize;

	    @FXML
	    private TableColumn<Product, String> tablePrice;

	    @FXML
	    private Button addProductButton;

	    @FXML
	    private TableView<Product> tableAddedProducts;

	    @FXML
	    private TableColumn<Product, String> columName;

	    @FXML
	    private TableColumn<Product, String> columPrice;

	    @FXML
	    private TableColumn<Product, String> columQuantity;

	    @FXML
	    private Spinner<Integer> productQuantity;

	    @FXML
	    private TextField txtSelectedProduct;

	    @FXML
	    private ComboBox<Client> comboClient;

	    @FXML
	    private ComboBox<Employee> comboEmployee;

	    @FXML
	    private DatePicker datePicker;

	    @FXML
	    private TextField txtHour;

	    @FXML
	    private TextField txtMinutes;

	    @FXML
	    private TextArea txtAreaObservations;

	    @FXML
	    private ComboBox<String> comboStatus;
	    
	    private Restaurant restaurant;
	    private int statusIdex;
	    
	    public CreateOrderGUI() {
	    	restaurant = new Restaurant();
	    }
	    
	    public Restaurant getRestaurant() {
			return restaurant;
		}

		public void receiveData(Restaurant restaurant) {
	    	this.restaurant = restaurant;
	    }
	    
	    public void loadTableViewProducts() {
	    	ObservableList<Product> products = FXCollections.observableArrayList(restaurant.getProducts());
	    	
	    	tableViewProducts.setItems(products);
	    	tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	    	tableType.setCellValueFactory(new PropertyValueFactory<Product, String>("typeProduct"));
	    	tableIngredients.setCellValueFactory(new PropertyValueFactory<Product, String>("ingredients"));
	    	tableSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
	    	tablePrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
	    }
	    
	    public void loadAddedTableProduct() {
	    	ObservableList<Product> productList = tableAddedProducts.getItems();
	    	tableAddedProducts.setItems(productList);
			columName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
			columPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
			columQuantity.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
	    }
	    
	    public void putClients() {
	    	ObservableList<Client> products = FXCollections.observableList(restaurant.getClients());
	    	comboClient.setItems(products);
	    }
	    
	    public void putEmployee() {
	    	ObservableList<Employee> employees = FXCollections.observableArrayList(restaurant.getEmployee());
	    	comboEmployee.setItems(employees); 
	    }
	    
	    public void putStatus() {
	    	comboStatus.getItems().add("SOLICITADO");
	    	comboStatus.getItems().add("EN PROCESO");
	    	comboStatus.getItems().add("ENVIADO");
	    	comboStatus.getItems().add("ENTREGADO");
	    	comboStatus.setOnAction(e-> statusIdex = comboStatus.getSelectionModel().getSelectedIndex());
	    }	
	    
	    public void initialize() {
	    	loadTableViewProducts();
	    	putClients();
	    	putEmployee();
	    	putStatus();
	    	productQuantity.setValueFactory(new IntegerSpinnerValueFactory(0, 999, 0));
	    	addProductButton.setDisable(true);
	    }
	    
	    @FXML
	    public void addProduct(ActionEvent event) {
	    	
	    	if(!txtSelectedProduct.getText().isEmpty() &&  productQuantity.getValue() > 0) {
	    		Product product = tableViewProducts.getSelectionModel().getSelectedItem();
	    		product.setQuantity(productQuantity.getValue());
	    		
	    		if (!tableAddedProducts.getItems().contains(product)) {
					tableAddedProducts.getItems().add(product);
	    		} else {
					tableAddedProducts.getItems().remove(product);
					tableAddedProducts.getItems().add(product);
				}
	    		loadAddedTableProduct();
	    	}	
	    }
	    
	    @FXML
	    public void createOrder(ActionEvent event) {
	    	Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	int hour = Integer.parseInt(txtHour.getText());
	    	date.setHours(hour);
	    	//added = restaurant.addOrder(comboEmployee.getValue(), , null, null, null, null)
	    }
	    
	    @FXML
	    public void deleteProduct(MouseEvent event) {
	    	Product p = tableAddedProducts.getSelectionModel().getSelectedItem();
	    	
	    	if(p != null) {
	    		tableAddedProducts.getItems().remove(p);
	    		loadAddedTableProduct();
	    	}
	    }
	    
	    @FXML
	    public void selectItem(MouseEvent event) {
	    	Product p = tableViewProducts.getSelectionModel().getSelectedItem();
	    	
	    	if(p != null) {
	    		txtSelectedProduct.setText(p.getName());
	    		addProductButton.setDisable(false);
	    	}
	    }
}
