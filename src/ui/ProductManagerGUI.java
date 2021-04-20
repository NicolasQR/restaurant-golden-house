package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	  
	    private CreateProductGUI controller;  
	    private UpdateProductGUI updateController;
		private IngredientManagerGUI passController;  
		
	    public ProductManagerGUI() {
	    	restaurant = new Restaurant();
	    	controller = new CreateProductGUI();
	    	updateController = new UpdateProductGUI();
	    	passController = new IngredientManagerGUI();
	    }
	    
	    public void receiveData(Restaurant restaurant) {
	    	this.restaurant = restaurant;
	    }
	    
	    public void passController(IngredientManagerGUI controller) {
	    	this.passController = controller;
	    }
	    
	    public Restaurant getRestaurant() {
	    	return restaurant;
	    }
	    
	    public IngredientManagerGUI getPassController() {
			return passController;
		}
	    
		public void loadTableView() {
	    	ObservableList<Product> products = FXCollections.observableArrayList(restaurant.getProducts());
	    	
	    	tableViewProducts.setItems(products);
	    	tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	    	tableType.setCellValueFactory(new PropertyValueFactory<Product, String>("typeProduct"));
	    	tableIngredients.setCellValueFactory(new PropertyValueFactory<Product, String>("ingredients"));
	    	tableSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
	    	tablePrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
	    	
	    	tableViewProducts.setRowFactory(tv -> new TableRow<Product>() {
	    	    @Override
	    	    protected void updateItem(Product item, boolean empty) {
	    	        super.updateItem(item, empty);

		    	    	if (item == null || empty) {
		    	    		setStyle("");
						} else {
							if (item.getStatus()) {
								setStyle("-fx-background-color: #7FFF00;");
							} else {
								setStyle("-fx-background-color: #FF6347;");
							}
						}
	    	    }
	    	});
	    	
	    	tableViewProducts.setOnMousePressed(new EventHandler<MouseEvent>() {
	    		@Override
	    		public void handle(MouseEvent event) {
		    		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		    			Product product = tableViewProducts.getSelectionModel().getSelectedItem();
		    			int index = tableViewProducts.getSelectionModel().getSelectedIndex();
		    			try {
							updateProduct(index, product);
						} catch (IOException e) {
							
						}
		    		}
	    		}
	    	});
	    	
		}

	    public void initialize() {
	    	loadTableView();
	    }
	    
	    
		@FXML
	    public void deactivateProduct(ActionEvent event) throws FileNotFoundException, IOException {
	    	Product p = tableViewProducts.getSelectionModel().getSelectedItem();
	    	int index = tableViewProducts.getSelectionModel().getSelectedIndex();
	    	
	    	if(p != null) {
	    		restaurant.getProducts().get(index).updateStatus(false);
	    		restaurant.saveDataofProducts();
	    	}
	    	loadTableView();
	    }
	    
		@FXML
	    public void enableProduct(ActionEvent event) throws FileNotFoundException, IOException {
			Product p = tableViewProducts.getSelectionModel().getSelectedItem();
	    	int index = tableViewProducts.getSelectionModel().getSelectedIndex();
	    	
	    	if(p != null) {
	    		restaurant.getProducts().get(index).updateStatus(true);
	    		restaurant.saveDataofProducts();
	    	}
	    	loadTableView();
	    }
		
	    @FXML
	    public void deleteProduct(ActionEvent event) throws FileNotFoundException, IOException {
	    	if (!tableViewProducts.getSelectionModel().isEmpty()) {
				restaurant.deleteProduct(tableViewProducts.getSelectionModel().getSelectedIndex());
	    		loadTableView();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Unselected");
	    		alert.setHeaderText(null);
	    		alert.setContentText("No se ha seleccionado un producto.");
	    		alert.showAndWait();
			}
	    }
	    
	    @FXML
	    public void newProduct(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Create-product.fxml"));
	    	
	    	controller.receiveData(restaurant); //Pasamos informacion
	    	controller.passController(passController);
	    	open.setController(controller);
	    	
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Crear produtos");
	    	stage.setResizable(false);
	    	stage.setOnHidden(new EventHandler<javafx.stage.WindowEvent>() { //Evento - se cierra la ventana
				
				@Override
				public void handle(javafx.stage.WindowEvent event) { //Recibimos informacion
					restaurant = controller.getRestaurant();
					passController = controller.getPassController();
					loadTableView();
				}
			});
	    	stage.showAndWait();
	    }
	    
	    public void updateProduct(int idx, Product product) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Update-product.fxml"));
	    	
	    	updateController.receiveData(idx, product, restaurant);
	    	open.setController(updateController); 	
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Actulizar produtos");
	    	stage.setResizable(false);
	    	stage.setOnHidden(new EventHandler<javafx.stage.WindowEvent>() { //Evento - se cierra la ventana
				
				@Override
				public void handle(javafx.stage.WindowEvent event) { //Recibimos informacion
					restaurant = updateController.getRestaurant();
					loadTableView();
				}
			});
	    	stage.showAndWait();
	    }  
}