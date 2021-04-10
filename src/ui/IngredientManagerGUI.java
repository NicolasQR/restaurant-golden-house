package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    	tableViewIngredient.setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
	    		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
	    			Ingredient ingredient = tableViewIngredient.getSelectionModel().getSelectedItem();
	    			txtName.setText(ingredient.getName());
					txtCode.setText(ingredient.getCode());
					updateButton.setDisable(false);
	    		}
    		}
    	});
    	
    	tableViewIngredient.setRowFactory(tv -> new TableRow<Ingredient>() {
    	    @Override
    	    protected void updateItem(Ingredient item, boolean empty) {
    	        super.updateItem(item, empty);

	    	    	if (item == null || empty) {
	    	    		setStyle("");
					} else {
						if (item.getStatus()) {
							setStyle("-fx-background-color: #7FFF00;");
						} else {
							setStyle("-fx-background-color: #FF6347;");
						}
					}
    	    }
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
    public void desactivateIngredient(ActionEvent event) throws FileNotFoundException, IOException {
    	Ingredient p = tableViewIngredient.getSelectionModel().getSelectedItem();
    	int index = tableViewIngredient.getSelectionModel().getSelectedIndex();
    	
    	if(p != null) {
    		restaurant.getIngredients().get(index).updateStatus(false);
    		restaurant.saveDataofIngredient();
    	}
    	loadTableView();
    }

    @FXML
    public void enableIngredient(ActionEvent event) throws FileNotFoundException, IOException {
    	Ingredient p = tableViewIngredient.getSelectionModel().getSelectedItem();
    	int index = tableViewIngredient.getSelectionModel().getSelectedIndex();
    	
    	if(p != null) {
    		restaurant.getIngredients().get(index).updateStatus(true);
    		restaurant.saveDataofIngredient();
    	}
    	loadTableView();
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
