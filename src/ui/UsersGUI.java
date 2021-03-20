package ui;

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
    public UsersGUI() {
    	restaurant = new Restaurant();
    }

    @FXML
    public void eliminateUserAccount(ActionEvent event) {

    }

    @FXML
    public void upgradeUserAccount(ActionEvent event) {

    }
    
    public void cargarTabla() {
    	
    }
    
    public  void initializeTableView() {
    	
    	ObservableList<User> datos;
    	datos = FXCollections.observableArrayList(restaurant.getUsers());
    	
    	tableUser.setItems(datos);
    	this.columnNameUser.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
    	this.columnLastNameUser.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
    	this.columnIdUser.setCellValueFactory(new PropertyValueFactory<User, String>("ID"));
    	this.columnUser.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
    }
}
