package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Ingredient;
import model.Restaurant;
import model.Size;
import model.Type;

public class CreateProductGUI {
	@FXML
    private TextField txtName;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ComboBox<Type> comboType;

    @FXML
    private ComboBox<Size> comboSyze;

    @FXML
    private ListView<Ingredient> listIngredients;
    
    @FXML
    private ListView<Ingredient> listAddedIngredients;
    
    private Restaurant restaurant;
    
    private IngredientManagerGUI controllerIngredients;
    
    public CreateProductGUI() {
    	restaurant = new Restaurant();
    	controllerIngredients = new IngredientManagerGUI();
    }
    
    public void receiveData(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public void passController(IngredientManagerGUI controller) {
    	this.controllerIngredients = controller;
    }
    
    public Restaurant getRestaurant() {
    	return restaurant;
    }
    
    public IngredientManagerGUI getPassController() {
		return controllerIngredients;
	}

	public void putIngredientListInfo() {  
		ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(restaurant.getIngredients());
		listIngredients.setItems(ingredients);
    }
    
    public void putTypeInfo() {
    	ObservableList<Type> types = FXCollections.observableArrayList(restaurant.getProductTypes());
    	comboType.setItems(types);
    }
    public void putSizeInfo() {
    	ObservableList<Size> sizes = FXCollections.observableArrayList(restaurant.getProductsSize());
    	comboSyze.setItems(sizes);
    }
    
    @FXML
    public void addIngredient(ActionEvent event) {
    	if(listIngredients.getSelectionModel() != null) {
    		Ingredient ingredient = listIngredients.getFocusModel().getFocusedItem();		
			if(listAddedIngredients.getItems().size() > 0 && ingredient != null) {
				int mini = -1;
				for(int i = 0; i < listAddedIngredients.getItems().size(); i++) {
					
					if(ingredient.compareTo(listAddedIngredients.getItems().get(i)) < 0) {
						mini = i;
						i = listAddedIngredients.getItems().size();
					} else if(ingredient.compareTo(listAddedIngredients.getItems().get(i)) == 0){
						mini = -2;
						i = listAddedIngredients.getItems().size();
					}
				}
				if(mini != -1 && mini != -2) {
					listAddedIngredients.getItems().add(mini,ingredient);
				} else if(mini != -2){
					listAddedIngredients.getItems().add(ingredient);
				}
			}else if(ingredient != null){
				listAddedIngredients.getItems().add(ingredient);
			}
		}		
    }
    
    @FXML
    public void removeIngredient(ActionEvent event) {
    	if(!listAddedIngredients.getSelectionModel().isEmpty()) {
    		int idx = listAddedIngredients.getSelectionModel().getSelectedIndex();
    		listAddedIngredients.getItems().remove(idx);
    	}
    }


    @FXML
    public void createProduct(ActionEvent event) {
    	
    	try {
    		ArrayList<Ingredient> items = new ArrayList<>(listAddedIngredients.getItems());
    		boolean added = restaurant.addProduct(txtName.getText(), Long.parseLong(txtPrecio.getText()), 
    				comboType.getValue(), comboSyze.getValue(), items);
        	
    		if(added) {
        		
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Successfully created");
        		alert.setHeaderText(null);
        		alert.setContentText("El producto fue creado con éxito.");
        		alert.showAndWait();
        		
        		txtName.clear();
        		txtPrecio.clear();
        		comboType.getSelectionModel().clearSelection();
        		comboSyze.getSelectionModel().clearSelection();
        		listAddedIngredients.getItems().clear();
        		
        	} else {
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Empty fields");
        		alert.setHeaderText(null);
        		alert.setContentText("Hay campos vacíos o el producto ya está registrado.");
        		alert.showAndWait();
        	}
    	
    	} catch(NumberFormatException e){
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Wrong price formatt");
    		alert.setHeaderText(null);
    		alert.setContentText("El formato del precio es incorrecto.");
    		alert.showAndWait();
    	}
    	
    }

    @FXML
    public void createSize(ActionEvent event) {

    }
    
    @FXML
    public void createIngredient(ActionEvent event) throws IOException {
    	FXMLLoader open = new FXMLLoader(getClass().getResource("Gestion-ingredient.fxml"));
    	open.setController(controllerIngredients);
    	controllerIngredients.receiveData(restaurant);
    	Parent root = open.load();
    	
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.setTitle("Gestionar ingredientes");
    	stage.setResizable(false);
    	stage.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				restaurant = controllerIngredients.getRestaurant();
			}
		});
    	stage.showAndWait();
    }
    
    @FXML
    public void createTypes(ActionEvent event) {

    }
    
    public void initialize() {
		putIngredientListInfo();
		putSizeInfo();
		putTypeInfo();
    }
}
