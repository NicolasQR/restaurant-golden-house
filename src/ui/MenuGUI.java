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

		public MenuGUI() {
			controllerUsers = new UsersGUI();
			controllerProducts = new ProductManagerGUI();
			controllerIngredients = new IngredientManagerGUI();
			controllerTypes = new TypeManagerGUI();
			controllerClient = new ClientManagerGUI();
			controllerSizes = new SizeManagerGUI();
			restaurant = new Restaurant();
			controllerEmployee = new EmployeeGUI();
			
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
	    	stage.showAndWait();
		}

	    public void showOrderList(ActionEvent event) {

	    }
		
	    @FXML
	    public void showEmployeeList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-employee.fxml"));
	    	open.setController(controllerEmployee);
	    	controllerEmployee.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar empleados");
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
					restaurant = controllerIngredients.getRestaurant();
				}
			});
	    	stage.showAndWait();
	    }

	    @FXML
	    public void showSizeList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-size.fxml"));
	    	open.setController(controllerSizes);
	    	controllerTypes.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar tama�os");
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
	    	amountOfUsers.setText(Integer.toString(restaurant.getUsers().size()));
	    	amountOfProducts.setText(Integer.toString(restaurant.getProduct().size()));
	    	amountOfEmployee.setText(Integer.toString(restaurant.getEmployee().size()));
	    	amountOfClients.setText(Integer.toString(restaurant.getClients().size()));
	    	amountOfProductsSize.setText(Integer.toString(restaurant.getProductsSize().size()));
	    	amountOfProductType.setText(Integer.toString(restaurant.getProductsSize().size()));
	    }
	    
	    @FXML
	    public void updateLabelsInfo(ActionEvent event) {
	    	showLabelsInformation();
	    }
	    
	    
	    public void initialize(){
	    	showLabelsInformation();
	    }
	    
}
