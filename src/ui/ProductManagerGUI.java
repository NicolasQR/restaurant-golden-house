package ui;

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
	    
		private IngredientManagerGUI passController;
	    
	    public ProductManagerGUI() {
	    	restaurant = new Restaurant();
	    	controller = new CreateProductGUI();
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
	    }
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
	        });
	    }
	    */
	    public void initialize() {
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
	    	controller.passController(passController);
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
					passController = controller.getPassController();
					loadTableView();
				}
			});
	    	stage.showAndWait();
	    }

	    @FXML
	    public void updateProduct(ActionEvent event) {
	    	
	    }    
}
