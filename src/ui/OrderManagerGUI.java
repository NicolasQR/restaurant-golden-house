package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Order;
import model.Product;
import model.Restaurant;

public class OrderManagerGUI {
	
	@FXML
	private AnchorPane container;
	
	@FXML
    private TableView<Order> tableViewOrder;
	
	@FXML
    private TableColumn<Order,String> columClient;

    @FXML
    private TableColumn<Order, String> columEmployee;

    @FXML
    private TableColumn<Order, String> columDateAndHour;

    @FXML
    private TableColumn<Order, String> columStatus;
	
    @FXML
    private TextArea txtAreaObservations;

    @FXML
    private TableView<Product> tableAddedProducts;
    
    @FXML
    private TableColumn<Product, String> columProductName;

    @FXML
    private TableColumn<Product, String> columProductPrice;

    @FXML
    private TableColumn<Product, String> columProductQuantity;

    @FXML
    private MenuButton menuButtonStatus;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private TextField txtFileSeparator;
    
    private Restaurant restaurant;
    
    private CreateOrderGUI createOrderController;
    
    public OrderManagerGUI() {
    	restaurant = new Restaurant();
    	createOrderController = new CreateOrderGUI();
    }
    
    public void receiveData(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public Restaurant getRestaurant() {
    	return restaurant;
    }
    
    public void initialize() {
    	loadTableView();
    	menuButtonStatus.setDisable(true);
    	deleteButton.setDisable(true);
    }
    
    public int numberItem(String status) {
    	int select = 0;
    	
    	if(status.equals("SOLICITADO")) {
    		select = 1;
    	} else if(status.equals("EN PROCESO")) {
    		select = 2;
    	} else if(status.equals("ENVIADO")) {
    		select = 3;
    	} else if(status.equals("ENTREGADO")){
    		select = 4;
    	}
    	
    	return select;
    }
    
    public void loadTableView() {
    	ObservableList<Order> orders = FXCollections.observableArrayList(restaurant.getOrders());
    	
    	tableViewOrder.setItems(orders);
    	columClient.setCellValueFactory(new PropertyValueFactory<Order, String>("clientName"));
    	columEmployee.setCellValueFactory(new PropertyValueFactory<Order, String>("employeeName"));
    	columDateAndHour.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
    	columStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
    }
    
    public void loadAddedTable(ArrayList<Product> products) {
    	ObservableList<Product> productList = FXCollections.observableArrayList(products);
    	
    	tableAddedProducts.setItems(productList);
		columProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		columProductPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		columProductQuantity.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
    }
   
    @FXML
    public void selectOrder(MouseEvent event) {
    	Order order = tableViewOrder.getSelectionModel().getSelectedItem();
    	
    	if(order != null) {
    		txtAreaObservations.setText(order.getObservations());
    		loadAddedTable(order.getProducts());
    		menuButtonStatus.setDisable(false);
    		deleteButton.setDisable(false);
    	}
    }
    
    @FXML
    public void showCreateOrder(ActionEvent event) throws IOException {
    	FXMLLoader open = new FXMLLoader(getClass().getResource("Create-order.fxml"));
    	
    	createOrderController.receiveData(restaurant);
    	open.setController(createOrderController);
    	
    	Parent root = open.load();
    	
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.setTitle("Crear order");
    	stage.setResizable(false);
    	stage.setOnHidden(new EventHandler<javafx.stage.WindowEvent>() {
			
			@Override
			public void handle(javafx.stage.WindowEvent event) {
				restaurant = createOrderController.getRestaurant();
				loadTableView();
			}
		});
    	stage.showAndWait();
    }
	
	@FXML
    public void showScreenOfExport(ActionEvent event) throws IOException {
		
		FXMLLoader open = new FXMLLoader(getClass().getResource("ChoseFileSeparator.fxml"));
    	open.setController(this);
    	Parent root = open.load();
    	
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.setTitle("Gestionar export");
    	stage.showAndWait();
    	
		
    }
	
	@FXML
    public void selectDelivered(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
		int select = 4;
    	String status = "ENTREGADO";
    	
    	Order order = tableViewOrder.getSelectionModel().getSelectedItem();
    	int index = tableViewOrder.getSelectionModel().getSelectedIndex();
		if (order != null) {
			if (select > numberItem(order.getStatus())) {
				restaurant.updateStatusOrder(index, status);
				restaurant.loadDataofOrders();
				loadTableView();
				tableViewOrder.getSelectionModel().select(index);
			}
		}
    }

    @FXML
    public void selectInProcess(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
    	int select = 2;
    	String status = "EN PROCESO";
    	
    	Order order = tableViewOrder.getSelectionModel().getSelectedItem();
    	int index = tableViewOrder.getSelectionModel().getSelectedIndex();
		if (order != null) {
			if (select > numberItem(order.getStatus())) {
				restaurant.updateStatusOrder(index, status);
				restaurant.loadDataofOrders();
				loadTableView();
				tableViewOrder.getSelectionModel().select(index);
			} 
		}
    }

    @FXML
    public void selectRequested(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
    	int select = 1;
    	String status = "SOLICITADO";
    	
    	Order order = tableViewOrder.getSelectionModel().getSelectedItem();
    	int index = tableViewOrder.getSelectionModel().getSelectedIndex();
		if (order != null) {
			if (select > numberItem(order.getStatus())) {
				restaurant.updateStatusOrder(index, status);
				restaurant.loadDataofOrders();
				loadTableView();
				tableViewOrder.getSelectionModel().select(index);
			} 
		}
    }

    @FXML
    public void selectSend(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
    	int select = 3;
    	String status = "ENVIADO";
    	
    	Order order = tableViewOrder.getSelectionModel().getSelectedItem();
    	int index = tableViewOrder.getSelectionModel().getSelectedIndex();
		if (order != null) {
			if (select > numberItem(order.getStatus())) {
				restaurant.updateStatusOrder(index, status);
				restaurant.loadDataofOrders();
				loadTableView();
				tableViewOrder.getSelectionModel().select(index);
			} 
		}
    }
    
	@FXML
    public void exportDataofOrder(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Export File");
		filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File f = filechooser.showSaveDialog(container.getScene().getWindow());
	   
		if(f != null) {
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.setTitle("Export orders");
		   try {
			   	restaurant.exportDataofOrder(f.getAbsolutePath(), txtFileSeparator.getText());
				alert.setContentText("Las ordenes se han exportado correctamente");
				alert.showAndWait();
		  } catch (Exception e) {
				alert.setContentText("La orden no se pudo exportar");
				alert.showAndWait();
		  }
		   
	   }
    }
	
	@FXML
    public void cancelExport(ActionEvent event) {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
	
	@FXML
    public void deleteOrder(ActionEvent event) throws FileNotFoundException, IOException {
		int index = tableViewOrder.getSelectionModel().getSelectedIndex();
		restaurant.deleteOrder(index);
		loadTableView();
    }
}
