package ui;

import java.util.Arrays;

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
import model.Restaurant;
import model.Type;

public class TypeManagerGUI {
	  	@FXML
	    private TableView<Type> tableViewType;

	    @FXML
	    private TableColumn<Type, String> columName;

	    @FXML
	    private TableColumn<Type, String> columCode;
	    
	    @FXML
	    private Button updateButton;
	    
	    @FXML
	    private Button deleteButton;
	    
	    @FXML
	    private TextField txtName;

	    @FXML
	    private TextField txtCode;
	    
	    private Restaurant restaurant;
	    
	    public TypeManagerGUI() {
			restaurant = new Restaurant();
		}
	    
	    public void receiveData(Restaurant restaurant) {
	    	this.restaurant = restaurant;
	    }
	    
	    public void loadTableView() {
	    	ObservableList<Type> types = FXCollections.observableArrayList(restaurant.getProductTypes());
	    	
	    	tableViewType.setItems(types);
	    	columName.setCellValueFactory(new PropertyValueFactory<Type, String>("name"));
	    	columCode.setCellValueFactory(new PropertyValueFactory<Type, String>("code"));
	    	
	    	tableViewType.setRowFactory( tv -> {
				TableRow<Type> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
						Type rowData = row.getItem();
						updateButton.setDisable(false);
						txtName.setText(rowData.getName());
						txtCode.setText(rowData.getCode());
					}
				});
				return row ;
			});
	    }
	    
	    public void initialize() {
	    	loadTableView();
	    	updateButton.setDisable(true);
	    	deleteButton.setDisable(true);
	    }
	    

	    @FXML
	    void selectItem(MouseEvent event) {
	    	Type p = tableViewType.getSelectionModel().getSelectedItem();
	    	
	    	if(p != null) {
	    		deleteButton.setDisable(false);
	    	}
	    }
	    
	    public Restaurant getRestaurant() {
			return restaurant;
	    }

		@FXML
	    void deleteType(ActionEvent event) {
			if (!tableViewType.getSelectionModel().isEmpty()) {
				restaurant.getProductTypes().remove(tableViewType.getSelectionModel().getSelectedIndex());
				loadTableView();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Unselected");
	    		alert.setHeaderText(null);
	    		alert.setContentText("No se ha seleccionado un tipo.");
	    		alert.showAndWait();
			}
	    }

	    @FXML
	    void desactivateType(ActionEvent event) {

	    }

	    @FXML
	    void enableType(ActionEvent event) {

	    }

	    @FXML
	    void newType(ActionEvent event) {
	    	boolean added = restaurant.addProductType(txtName.getText());
	    	
	    	if(added) {
	    		loadTableView();
	    		txtName.clear();
	    		txtCode.clear();
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Successfully added");
	    		alert.setHeaderText(null);
	    		alert.setContentText("El tipo se agrego con éxito.");
	    		alert.showAndWait();
	    	} else {
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Empty field");
	    		alert.setHeaderText(null);
	    		if(txtName.getText().isEmpty()) {
		    		alert.setContentText("El campo está vacío.");
	    		}else {
	    			alert.setContentText("El tipo ya está agregado.");
	    		}
	    		alert.showAndWait();
	    	}
	    }

	    @FXML
	    void updateType(ActionEvent event) {
	    	if(!txtName.getText().isEmpty()) {
	    		
	    		int idx = tableViewType.getSelectionModel().getSelectedIndex();
	    		boolean update = restaurant.updateType(idx, txtName.getText());
	    		
	    		if(update) {
	    			tableViewType.getSelectionModel().getSelectedItem().setName(txtName.getText());;
		    		txtName.clear(); 
		    		txtCode.clear();
		    		loadTableView();
	    		} else {
	    			Alert alert = new Alert(AlertType.WARNING);
		    		alert.setTitle("Type existing");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Tipo en existencia.");
		    		alert.showAndWait();
	    		}
	    		System.out.println(Arrays.toString(restaurant.getProductTypes().toArray()));
	    	} else {
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Empty fields");
	    		alert.setHeaderText(null); 
	    		alert.setContentText("El campo está vacío.");
	    		alert.showAndWait();
	    	}

	    }
}
