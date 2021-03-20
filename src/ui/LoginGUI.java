package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class LoginGUI {

	@FXML
    private AnchorPane mainAnchorPane;
	
	@FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserLastName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField loginUser;

    @FXML
    private PasswordField loginPassword;
    
    private Restaurant restaurant;
    
    private MenuGUI menuController;
    
    public LoginGUI() {
    	restaurant = new Restaurant();
    	menuController = new MenuGUI();
    }
	
	public MenuGUI getMenuController() {
		return menuController;
	}



	public void setMenuController(MenuGUI menuController) {
		this.menuController = menuController;
	}
    
	public void loadLogin() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent root = fxmlLoader.load();
    	
		mainAnchorPane.getChildren().clear();
		mainAnchorPane.getChildren().setAll(root);
    }
	
	@FXML
    public void btnRegisterUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
    	
		mainAnchorPane.getChildren().clear();
		mainAnchorPane.getChildren().setAll(root);
    }
	
	@FXML
    void btnGoToLoginPane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent root = fxmlLoader.load();
    	
		mainAnchorPane.getChildren().clear();
		mainAnchorPane.getChildren().setAll(root);
    }
	
	@FXML
    public void createUser(ActionEvent event) {
		
		String userName = txtUserName.getText();
		String userLastName = txtUserLastName.getText();
		String id = txtId.getText();
		String user = txtUser.getText();
		String password = txtPassword.getText();
		
		if(!userName.equals("") && !userLastName.equals("") && !id.equals("") &&
				!user.equals("") && !password.equals("")) {
			
			restaurant.addUsers(userName, userLastName, 0, user, password);
			
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("User Creted");
        	alert.setHeaderText(null);
        	alert.setContentText("El usuario ha sido creado correctamente");
        	alert.showAndWait();
        	
		} else {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("Error");
    		alert.setContentText("Debes diligenciar toda la informaci�n requerida para crear un usuario");
    		alert.showAndWait();
		}
    }
	
	@FXML
    void logIn(ActionEvent event) throws IOException {
		boolean login = false;
		
		for(int i = 0; i < restaurant.getUsers().size() ; i++) {
    		
    		if(restaurant.getUsers().get(i).getUserName().equals(loginUser.getText()) &&
    				restaurant.getUsers().get(i).getPassword().equals(String.valueOf(loginPassword.getCharacters()))) {
    			
    			
    	    	login = true;
    	    	break; //comprobar si funciona
    	    	
    		}
		}
		if(login){
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
			fxmlLoader.setController(menuController);
			Parent root = fxmlLoader.load();
			
			mainAnchorPane.getChildren().clear();
			mainAnchorPane.getChildren().setAll(root);
			
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
   		 	alert.setTitle("Login incorrect");
   		 	alert.setContentText("El usuario o la contrase�a es incorrecta.");
   		 	alert.showAndWait();
		}
	}
    
}