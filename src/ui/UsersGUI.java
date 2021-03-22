package ui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	private Restaurant restaurant;
    MenuGUI stage_controler_stage2;
    
    public UsersGUI() {
    	restaurant = new Restaurant();
    }

    @FXML
    public void eliminateUserAccount(ActionEvent event) {

    }

    @FXML
    public void upgradeUserAccount(ActionEvent event) {

    }
    
  
    public void receiveData(MenuGUI menuGUI, List<User> users) {
    	
    	tableViewUser();
    	stage_controler_stage2 = menuGUI;
   
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
}
