package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class GoldenHouseGUI {

	@FXML
    private AnchorPane mainAnchorPane;
	
	
	
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
    
}
