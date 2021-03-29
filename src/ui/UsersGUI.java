package ui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Restaurant;
import model.User;

public class UsersGUI {
	
	
	@FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> columnNameUser;

    @FXML
    private TableColumn<User, String> columnLastNameUser;

    @FXML
    private TableColumn<User, String> columnIdUser;

    @FXML
    private TableColumn<User, String> columnUser;
    
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtUserAccount;
    
	
	private Restaurant restaurant;
    MenuGUI menuGuiController;
    
    public UsersGUI() {
    	restaurant = new Restaurant();
    }

    @FXML
    public void eliminateUserAccount(ActionEvent event) throws IOException {
    	User p = tableUser.getSelectionModel().getSelectedItem();
    	restaurant.getUsers().remove(p);
    	restaurant.saveDataofUsers();
    	tableViewUser();
    	
    }
    

    @FXML
    public void upgradeUserAccount(ActionEvent event) throws IOException {
    	int index = tableUser.getSelectionModel().getFocusedIndex();
 
    	restaurant.getUsers().get(index).setName(txtName.getText());
    	restaurant.getUsers().get(index).setLastName(txtLastName.getText());
    	restaurant.getUsers().get(index).setID(Long.parseLong(txtId.getText()));
    	restaurant.getUsers().get(index).setUserName(txtUserAccount.getText());
    	
    	restaurant.saveDataofUsers();
    	try {
			restaurant.loadDataofUsers();
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
    	
    	tableViewUser();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("User Upgrade");
    	alert.setHeaderText(null);
    	alert.setContentText("El usuario ha sido modificado correctamente");
    	alert.showAndWait();
    }
    
    
    public void tableViewUser() {
    	ObservableList<User> datos;
    	datos = FXCollections.observableArrayList(restaurant.getUsers());
    	
    	tableUser.setItems(datos);
    	this.columnNameUser.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
    	this.columnLastNameUser.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
    	this.columnIdUser.setCellValueFactory(new PropertyValueFactory<User, String>("iD"));
    	this.columnUser.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
    }
    
    @FXML
    public void showTxtInformation(MouseEvent event) {
    	User p = tableUser.getSelectionModel().getSelectedItem();
    	
    	if(p != null) {
    		txtName.setText(p.getName());
        	txtLastName.setText(p.getLastName());
        	txtId.setText(String.valueOf(p.getID()));
        	txtUserAccount.setText(p.getUserName());
    	}
    }
    
    public void initialize() {
    	tableViewUser();
    }
    
}
