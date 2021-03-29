package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Restaurant;

public class IngredientManagerGUI {
	@FXML
    private TableView<?> tableViewIngredient;

    @FXML
    private TableColumn<?, ?> columName;

    @FXML
    private TableColumn<?, ?> columCode;
    
    Restaurant restaurant;
    
    public IngredientManagerGUI() {
    	restaurant = new Restaurant();
	}
    
    public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	@FXML
    void deleteIngredient(ActionEvent event) {

    }

    @FXML
    void desactivateIngredient(ActionEvent event) {

    }

    @FXML
    void enableIngredient(ActionEvent event) {

    }

    @FXML
    void newIngredient(ActionEvent event) {

    }

    @FXML
    void updateIngredient(ActionEvent event) {

    }
}
