package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Restaurant;
import model.Size;

public class SizeManagerGUI {
	@FXML
    private TableView<Size> tableViewSize;

    @FXML
    private TableColumn<Size, String> columName;

    @FXML
    private TableColumn<Size, String> columCode;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCode;

    private Restaurant restaurant;
    
    public SizeManagerGUI() {
    	restaurant = new Restaurant();
  	}
    
    public void receiveData(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void loadTableView() {
    	ObservableList<Size> sizes = FXCollections.observableArrayList(restaurant.getProductsSize());
    	
    	tableViewSize.setItems(sizes);
    	columName.setCellValueFactory(new PropertyValueFactory<Size, String>("name"));
    	columCode.setCellValueFactory(new PropertyValueFactory<Size, String>("code"));
    	
    	tableViewSize.setRowFactory( tv -> {
			TableRow<Size> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Size rowData = row.getItem();
					updateButton.setDisable(false);
					txtName.setText(rowData.getName());
					txtCode.setText(rowData.getCode());
				}
			});
			return row;
		});
    }
    
    public void initialize() {
    	loadTableView();
    	updateButton.setDisable(true);
    	deleteButton.setDisable(true);
    }
    
    @FXML
    public void deleteSize(ActionEvent event) {
    	if (!tableViewSize.getSelectionModel().isEmpty()) {
			restaurant.getProductsSize().remove(tableViewSize.getSelectionModel().getSelectedIndex());
			loadTableView();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Unselected");
    		alert.setHeaderText(null);
    		alert.setContentText("No se ha seleccionado un tamaño.");
    		alert.showAndWait();
		}
    }

    @FXML
    void desactivateSize(ActionEvent event) {

    }

    @FXML
    void enableSize(ActionEvent event) {

    }
    
    @FXML
    public void selectItem(MouseEvent event) {
    	Size p = tableViewSize.getSelectionModel().getSelectedItem();
    	
    	if(p != null) {
    		deleteButton.setDisable(false);
    	}
    }
    
    @FXML
    public void newSize(ActionEvent event) throws FileNotFoundException, IOException {
    	boolean added = restaurant.addProductSize(txtName.getText());
    	
    	if(added) {
    		loadTableView();
    		txtName.clear();
    		txtCode.clear();
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Successfully added");
    		alert.setHeaderText(null);
    		alert.setContentText("El tamaño se agrego con éxito.");
    		alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Empty field");
    		alert.setHeaderText(null);
    		if(txtName.getText().isEmpty()) {
	    		alert.setContentText("El campo está vacío.");
    		}else {
    			alert.setContentText("El tamaño ya está agregado.");
    		}
    		alert.showAndWait();
    	}
    }

    @FXML
    public void updateSize(ActionEvent event) {
    	
    	if(!txtName.getText().isEmpty()) {
    		
    		int idx = tableViewSize.getSelectionModel().getSelectedIndex();
    		boolean update = restaurant.updateSize(idx, txtName.getText());
    		
    		if(update) {
    			tableViewSize.getSelectionModel().getSelectedItem().setName(txtName.getText());;
	    		txtName.clear();
	    		txtCode.clear();
	    		loadTableView();
    		} else {
    			Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Size existing");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Tamaño en existencia.");
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
