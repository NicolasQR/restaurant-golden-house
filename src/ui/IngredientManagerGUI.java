package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Ingredient;
import model.Restaurant;

public class IngredientManagerGUI {
	@FXML
    private TableView<Ingredient> tableViewIngredient;

    @FXML
    private TableColumn<Ingredient, String> columName;

    @FXML
    private TableColumn<Ingredient, String> columCode;
    
    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtCode;
    
    Restaurant restaurant;
    
    public IngredientManagerGUI() {
    	restaurant = new Restaurant();
	}
    
    public void receiveData(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public void loadTableView() {
    	ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(restaurant.getIngredients());
    	
    	tableViewIngredient.setItems(ingredients);
    	columName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
    	columCode.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("code"));
    	
    	tableViewIngredient.setRowFactory( tv -> {
			TableRow<Ingredient> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Ingredient rowData = row.getItem();
					txtName.setText(rowData.getName());
					txtCode.setText(rowData.getCode());
					updateButton.setDisable(false);
				}
			});
			return row ;
		});
    }
    
    public void initialize() {
    	loadTableView();
    	deleteButton.setDisable(true);
    	updateButton.setDisable(true);
    }
    
    @FXML
    void selectItem(MouseEvent event) {
    	Ingredient p = tableViewIngredient.getSelectionModel().getSelectedItem();
    	
    	if(p != null) {
    		deleteButton.setDisable(false);
    	}
    }
    
    public Restaurant getRestaurant() {
		return restaurant;
	}

	@FXML
    public void deleteIngredient(ActionEvent event) {
		if(!tableViewIngredient.getSelectionModel().isEmpty()) {
			restaurant.getIngredients().remove(tableViewIngredient.getSelectionModel().getSelectedIndex());
			loadTableView();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Unselected");
    		alert.setHeaderText(null);
    		alert.setContentText("No se ha seleccionado un ingrediente.");
    		alert.showAndWait();
		}
    }

    @FXML
    public void desactivateIngredient(ActionEvent event) {

    }

    @FXML
    public void enableIngredient(ActionEvent event) {

    }

    @FXML
    public void newIngredient(ActionEvent event) throws IOException {
    	
    	boolean added = restaurant.addIngredient(txtName.getText());
    	
    	if(added) {
    		loadTableView();
    		txtName.clear();
    		txtCode.clear();
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Successfully added");
    		alert.setHeaderText(null);
    		alert.setContentText("El ingrediente se agrego con éxito.");
    		alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Empty field");
    		alert.setHeaderText(null);
    		if(txtName.getText().isEmpty()) {
	    		alert.setContentText("El campo está vacío.");
    		}else {
    			alert.setContentText("El ingrediente ya está agregado.");
    		}
    		alert.showAndWait();
    	}
    }

    @FXML
    public void updateIngredient(ActionEvent event) {
    	if(!txtName.getText().isEmpty() && !tableViewIngredient.getSelectionModel().isEmpty()) {
    		tableViewIngredient.getSelectionModel().getSelectedItem().setName(txtName.getText());
    		txtName.clear();
    		txtCode.clear();
    		loadTableView();
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Empty fields");
    		alert.setHeaderText(null); 
    		alert.setContentText("No se ha seleccionado un ingrediente.");
    		alert.showAndWait();
    	}
    }
}
