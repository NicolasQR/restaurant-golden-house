package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Restaurant;

public class MenuGUI {
	
	@FXML
    private Label amountOfUsers;
    
	@FXML
    private MenuGUI menuGUI;
    
    @FXML
    private Label amountOfProducts;
	
    private Restaurant restaurant;
	private UsersGUI controllerUsers;
	private ProductManagerGUI controllerProducts;
	
		public MenuGUI() {
			controllerUsers = new UsersGUI();
			controllerProducts = new ProductManagerGUI();
			restaurant = new Restaurant();
		}
		
	    @FXML
	    public void showEmployeeList(ActionEvent event) {
	    	
	    }

	    @FXML
	    public void showIngredientList(ActionEvent event) {

	    }

	    @FXML
	    public void showPorductsType(ActionEvent event) throws IOException {
	    	
	    }

	    @FXML
	    public void showProductsList(ActionEvent event) throws IOException {
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-product.fxml"));
	    	open.setController(controllerProducts);
	    	controllerProducts.receiveData(restaurant);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();    	
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar productos");
	    	stage.setResizable(false);
	    	stage.showAndWait();
	    }

	    @FXML
	    public void showUserList(ActionEvent event) throws IOException {
	    	
	    	FXMLLoader open = new FXMLLoader(getClass().getResource("GestionUser.fxml"));
	    	open.setController(controllerUsers);
	    	Parent root = open.load();
	    	
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setScene(scene);
	    	stage.setTitle("Gestionar usuarios");
	    	stage.setResizable(false);
	    	stage.showAndWait();
	    }
	    
	    /*
	    public void initialize(URL url, ResourceBundle rb) {
	    	menuGUI = this;
	    }*/
	   
	    public void showLabelsInformation() {
	    	amountOfUsers.setText(Integer.toString(restaurant.getUsers().size()));
	    	amountOfProducts.setText(Integer.toString(restaurant.getProduct().size()));
	    }
	    
	    public void initialize() {
	    	showLabelsInformation();
	    }
	    
}
