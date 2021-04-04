package ui;

import java.io.File;
import java.io.IOException;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
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
    private TableView<Product> listAddedProducts;

    @FXML
    private ComboBox<String> comboStatus;

    private Restaurant restaurant;
    
    private CreateOrderGUI createOrderController;
    
    public OrderManagerGUI() {
    	restaurant = new Restaurant();
    	createOrderController = new CreateOrderGUI();
    }
    
    public void receiveData(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public void initialize() {
    	loadTableView();
    }
    
    public void loadTableView() {
    	ObservableList<Order> orders = FXCollections.observableArrayList(restaurant.getOrders());
    	
    	tableViewOrder.setItems(orders);
    	columClient.setCellValueFactory(new PropertyValueFactory<Order, String>("clientName"));
    	columEmployee.setCellValueFactory(new PropertyValueFactory<Order, String>("employeeName"));
    	columDateAndHour.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
    	columStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
    	tableViewOrder.setRowFactory( tv -> {
			TableRow<Order> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Order rowData = row.getItem();
				}
			});
			return row;
		});
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
    public void exportDataofOrder(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		   filechooser.setTitle("Open Import File");
		   File f = filechooser.showSaveDialog(container.getScene().getWindow());
		   
		   if(f != null) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Export orders");
			   try {
				   	restaurant.exportDataofOrder(f.getAbsolutePath());
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
}
