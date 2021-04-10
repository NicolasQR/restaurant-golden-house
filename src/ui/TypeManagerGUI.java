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
	    	
	    	tableViewType.setOnMousePressed(new EventHandler<MouseEvent>() {
	    		@Override
	    		public void handle(MouseEvent event) {
		    		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		    			Type rowData = tableViewType.getSelectionModel().getSelectedItem();
						updateButton.setDisable(false);
						txtName.setText(rowData.getName());
						txtCode.setText(rowData.getCode());
		    		}
	    		}
	    	});
	    	
	    	tableViewType.setRowFactory(tv -> new TableRow<Type>() {
	    	    @Override
	    	    protected void updateItem(Type item, boolean empty) {
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
	    	updateButton.setDisable(true);
	    	deleteButton.setDisable(true);
	    }
	    

	    @FXML
	    public void selectItem(MouseEvent event) {
	    	Type p = tableViewType.getSelectionModel().getSelectedItem();
	    	
	    	if(p != null) {
	    		deleteButton.setDisable(false);
	    	}
	    }
	    
	    public Restaurant getRestaurant() {
			return restaurant;
	    }

		@FXML
	    public void deleteType(ActionEvent event) throws FileNotFoundException, IOException {
			if (!tableViewType.getSelectionModel().isEmpty()) {
				restaurant.getProductTypes().remove(tableViewType.getSelectionModel().getSelectedIndex());
				restaurant.saveDataofProductType();
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
	    public void desactivateType(ActionEvent event) throws FileNotFoundException, IOException {
	    	Type p = tableViewType.getSelectionModel().getSelectedItem();
	    	int index = tableViewType.getSelectionModel().getSelectedIndex();
	    	
	    	if(p != null) {
	    		restaurant.getProductTypes().get(index).updateStatus(false);
	    		restaurant.saveDataofProductType();
	    	}
	    	loadTableView();
	    }

	    @FXML
	    public void enableType(ActionEvent event) throws FileNotFoundException, IOException {
	    	Type p = tableViewType.getSelectionModel().getSelectedItem();
	    	int index = tableViewType.getSelectionModel().getSelectedIndex();
	    	
	    	if(p != null) {
	    		restaurant.getProductTypes().get(index).updateStatus(true);
	    		restaurant.saveDataofProductType();
	    	}
	    	loadTableView();
	    }

	    @FXML
	    public void newType(ActionEvent event) throws FileNotFoundException, IOException {
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
	    		loadTableView();
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
	    public void updateType(ActionEvent event) throws FileNotFoundException, IOException {
	    	
	    	if(!txtName.getText().isEmpty()) {
	    		
	    		int idx = tableViewType.getSelectionModel().getSelectedIndex();
	    		boolean update = restaurant.updateType(idx, txtName.getText());
	    		
	    		if(update) {
	    			tableViewType.getSelectionModel().getSelectedItem().setName(txtName.getText());;
		    		txtName.clear(); 
		    		txtCode.clear();
		    		restaurant.saveDataofProductType();
		    		loadTableView();
	    		} else {
	    			Alert alert = new Alert(AlertType.WARNING);
		    		alert.setTitle("Type existing");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Tipo en existencia.");
		    		alert.showAndWait();
	    		}
	    		
	    	} else {
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Empty fields");
	    		alert.setHeaderText(null); 
	    		alert.setContentText("El campo está vacío.");
	    		alert.showAndWait();
	    	}

	    }
}
