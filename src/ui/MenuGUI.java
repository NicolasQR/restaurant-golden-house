package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Restaurant;

public class MenuGUI {
	
	@FXML
    private Label amountOfUsers;
    
	@FXML
    private MenuGUI menuGUI;
    
    @FXML
    private Label amountOfProducts;
    
    @FXML
    private Label amountOfEmployee;

    @FXML
    private Label amountOfClients;

    @FXML
    private Label amountOfProductsSize;

    @FXML
    private Label amountOfProductType;

	
    private Restaurant restaurant;
    
	private UsersGUI controllerUsers;
	private EmployeeGUI controllerEmployee;
	private ProductManagerGUI controllerProducts;
	private IngredientManagerGUI controllerIngredients;
	private TypeManagerGUI controllerTypes;
	private ClientManagerGUI controllerClient;
	private SizeManagerGUI controllerSizes;
	private OrderManagerGUI controllerOrder;

		public MenuGUI() {
			controllerUsers = new UsersGUI();
			controllerProducts = new ProductManagerGUI();
			controllerIngredients = new IngredientManagerGUI();
			controllerTypes = new TypeManagerGUI();
			controllerClient = new ClientManagerGUI();
			controllerSizes = new SizeManagerGUI();
			restaurant = new Restaurant();
			controllerEmployee = new EmployeeGUI();
			controllerOrder = new OrderManagerGUI();
			
			try {
				restaurant.loadDatafEmployee();
				restaurant.loadDataofProducts();
				restaurant.loadDataofIngredients();
				restaurant.loadDataofProductsSize();
				restaurant.loadDataofUsers();
				restaurant.loadDataofProductType();
				restaurant.loadDataofClients();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
		@FXML
	    public void showClientList(ActionEvent event) throws IOException {
			FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-client.fxml"));
	    	open.setController(controllerClient);
	    	controllerClient.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar clientes");
	    	stage.setOnHidden(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					restaurant = controllerClient.getRestaurant();
				}
			});
	    	stage.showAndWait();
	    	stage.showAndWait();
		}
		
	    public void showOrderList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-order.fxml"));
	    	controllerOrder.receiveData(restaurant);
	    	open.setController(controllerOrder);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar ordenes");
	    	stage.showAndWait();
	    }
		
	    @FXML
	    public void showEmployeeList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-employee.fxml"));
	    	controllerEmployee.receiveData(restaurant);
	    	open.setController(controllerEmployee);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar empleados");
	    	stage.setOnHidden(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					restaurant = controllerEmployee.getRestaurant();
				}
			});
	    	stage.showAndWait();
	    }

	    @FXML
	    public void showIngredientList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-ingredient.fxml"));
	    	open.setController(controllerIngredients);
	    	controllerIngredients.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar ingredientes");
	    	stage.setResizable(false);
	    	stage.setOnHidden(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					restaurant = controllerIngredients.getRestaurant();
				}
			});
	    	stage.showAndWait();
	    }

	    @FXML
	    public void showProductsType(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-type.fxml"));
	    	open.setController(controllerTypes);
	    	controllerTypes.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar tipos");
	    	stage.setResizable(false);
	    	stage.setOnHidden(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					restaurant = controllerTypes.getRestaurant();
				}
			});
	    	stage.showAndWait();
	    }

	    @FXML
	    public void showSizeList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-size.fxml"));
	    	open.setController(controllerSizes);
	    	controllerSizes.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar tamaños");
	    	stage.setResizable(false);
	    	stage.setOnHidden(new EventHandler<WindowEvent>() {
			
				@Override
				public void handle(WindowEvent event) {
					restaurant = controllerSizes.getRestaurant();
				}
			});
	    	stage.showAndWait();
	    }
	    
	    @FXML
	    public void showProductsList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-product.fxml"));
	    	open.setController(controllerProducts);
	    	controllerProducts.receiveData(restaurant);
	    	controllerProducts.passController(controllerIngredients);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();    	
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar productos");
	    	stage.setResizable(false);
	    	stage.setOnHidden(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					restaurant = controllerProducts.getRestaurant();
					controllerIngredients = controllerProducts.getPassController();
				}
			});
	    	stage.showAndWait();
	    }
	    
	    @FXML
	    public void showUserList(ActionEvent event) throws IOException {
	    	
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("GestionUser.fxml"));
	    	open.setController(controllerUsers);
	    	controllerUsers.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar usuarios");
	    	stage.setResizable(false);
	    	stage.showAndWait();
	    }
	    
	    public void showLabelsInformation() {
	    	amountOfUsers.setText(String.valueOf(restaurant.getUsers().size()));
	    	amountOfProducts.setText(String.valueOf(restaurant.getProducts().size()));
	    	amountOfEmployee.setText(String.valueOf(restaurant.getEmployee().size()));
	    	amountOfClients.setText(String.valueOf(restaurant.getClients().size()));
	    	amountOfProductsSize.setText(String.valueOf(restaurant.getProductsSize().size()));
	    	amountOfProductType.setText(String.valueOf(restaurant.getProductTypes().size()));
	    }
	    
	    @FXML
	    public void updateLabelsInfo(ActionEvent event) {
	    	showLabelsInformation();
	    }
	    
	    
	    public void initialize(){
	    	showLabelsInformation();
	    }
	    
}
