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

public class GoldenHouseGUI {

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
    public GoldenHouseGUI() {
    	restaurant = new Restaurant();
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
    public void btnGoToLoginPane(ActionEvent event) throws IOException {
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
			
			txtUserName.setText("");
			txtUserLastName.setText("");
			txtId.setText("");
			txtUser.setText("");
			txtPassword.setText("");
			
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("User Creted");
        	alert.setHeaderText(null);
        	alert.setContentText("El usuario ha sido creado correctamente");
        	alert.showAndWait();
        	
		} else {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("Error");
    		alert.setContentText("Debes diligenciar toda la información requerida para crear un usuario");
    		alert.showAndWait();
		}
    }
	
	@FXML
    public void logIn(ActionEvent event) throws IOException {
		for(int i = 0; i < restaurant.getUsers().size() ; i++) {
    		
    		if(restaurant.getUsers().get(i).getUserName().equals(loginUser.getText()) &&
    				restaurant.getUsers().get(i).getPassword().equals(String.valueOf(loginPassword.getCharacters()))) {
    			
    			
    			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
    			fxmlLoader.setController(this);    	
    			Parent root = fxmlLoader.load();
    	    	
    			mainAnchorPane.getChildren().clear();
    			mainAnchorPane.getChildren().setAll(root);
    			
    	    	System.out.println("Yeaaaah");
    	    	break;
    	    	
    			
    		} else if( i == (restaurant.getUsers().size() -1) ){
    			
    			Alert alert = new Alert(AlertType.ERROR);
       		 	alert.setTitle("Log in incorrect");
       		 	alert.setContentText("El usuario o la contraseña es incorrecta");

       		 	alert.showAndWait();
    		}
		}
	}
    
}
