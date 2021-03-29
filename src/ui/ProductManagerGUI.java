package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
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
	    
	    public ProductManagerGUI() {
	    	restaurant = new Restaurant();
	    	controller = new CreateProductGUI();
	    }
	    
	    public void receiveData(Restaurant restaurant) {
	    	this.restaurant = restaurant;
	    }
	    
	    public Restaurant getRestaurant() {
	    	return restaurant;
	    }
	    
	    public void loadTableView() {
	    	ObservableList<Product> accounts = FXCollections.observableArrayList(restaurant.getProducts());
	    	
	    	tableViewProducts.setItems(accounts);
	    	tableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	    	tableType.setCellValueFactory(new PropertyValueFactory<Product, String>("typeProduct"));
	    	tableIngredients.setCellValueFactory(new PropertyValueFactory<Product, String>("ingredients"));
	    	tableSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
	    	tablePrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
	    }

	    
	    public void initialize() throws FileNotFoundException, IOException {

	    /*
	    public TableCell<Product, String> changedColorRow(TableColumn<Product, String> tableName) {
	    	tableName.setCellFactory(new Callback<TableColumn<Product, String>, TableCell<Product, String>>(){ 
	            @Override
	            public TableCell<Product, String> call(TableColumn<Product, String> param) {

	                return new TableCell<Product, String>(){
	                    @Override
	                    protected void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);

	                      if (item != null){
	                            setStyle("-fx-background-color:#"+item);
	                     }

	                    }
	                };
	            }
	        });*/
	        
	        restaurant.addIngredient("Aguacate");
			restaurant.addIngredient("Paprica");
			restaurant.addIngredient("Oro");
			
			restaurant.addProductType("familiar");
			restaurant.addProductType("para mi");
			restaurant.addProductType("para todos");
			
			restaurant.addProductSize("grande");
			restaurant.addProductSize("mediano");
			restaurant.addProductSize("pequeño");
			
			/*restaurant.addProduct("Pizza", 12000, restaurant.getProductTypes().get(0), 
					restaurant.getProductsSize().get(1),restaurant.getIngredients());*/
	    	loadTableView();
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
	    public void newProduct(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Create-product.fxml"));
	    	
	    	controller = new CreateProductGUI();
	    	controller.receiveData(restaurant); //Pasamos informacion
	    	
	    	open.setController(controller);
	    	
	    	Parent root = open.load();
	    	
	    	System.out.println(Arrays.toString(restaurant.getIngredients().toArray()));
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
					loadTableView();
				}
			});
	    	stage.showAndWait();
	    }

	    @FXML
	    public void updateProduct(ActionEvent event) {
	    	
	    }    
}
