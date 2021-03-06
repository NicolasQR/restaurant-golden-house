package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    	
    	tableViewSize.setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent event) {
	    		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
	    			Size rowData = tableViewSize.getSelectionModel().getSelectedItem();
					updateButton.setDisable(false);
					txtName.setText(rowData.getName());
					txtCode.setText(rowData.getCode());
	    		}
    		}
    	});
    	
    	tableViewSize.setRowFactory(tv -> new TableRow<Size>() {
    	    @Override
    	    protected void updateItem(Size item, boolean empty) {
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
    public void deleteSize(ActionEvent event) {
    	if (!tableViewSize.getSelectionModel().isEmpty()) {
			restaurant.getProductsSize().remove(tableViewSize.getSelectionModel().getSelectedIndex());
			loadTableView();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Unselected");
    		alert.setHeaderText(null);
    		alert.setContentText("No se ha seleccionado un tama?o.");
    		alert.showAndWait();
		}
    }

    @FXML
    public void desactivateSize(ActionEvent event) throws FileNotFoundException, IOException {
    	Size p = tableViewSize.getSelectionModel().getSelectedItem();
    	int index = tableViewSize.getSelectionModel().getSelectedIndex();
    	
    	if(p != null) {
    		restaurant.getProductsSize().get(index).updateStatus(false);
    		restaurant.saveDataofProductSize();
    	}
    	loadTableView();
    }

    @FXML
    public void enableSize(ActionEvent event) throws FileNotFoundException, IOException {
    	Size p = tableViewSize.getSelectionModel().getSelectedItem();
    	int index = tableViewSize.getSelectionModel().getSelectedIndex();
    	
    	if(p != null) {
    		restaurant.getProductsSize().get(index).updateStatus(true);
    		restaurant.saveDataofProductSize();
    	}
    	loadTableView();
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
    		alert.setContentText("El tama?o se agrego con ?xito.");
    		alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Empty field");
    		alert.setHeaderText(null);
    		if(txtName.getText().isEmpty()) {
	    		alert.setContentText("El campo est? vac?o.");
    		}else {
    			alert.setContentText("El tama?o ya est? agregado.");
    			loadTableView();
    		}
    		alert.showAndWait();
    	}
    }

    @FXML
    public void updateSize(ActionEvent event) throws FileNotFoundException, IOException {
    	
    	if(!txtName.getText().isEmpty()) {
    		
    		int idx = tableViewSize.getSelectionModel().getSelectedIndex();
    		boolean update = restaurant.updateSize(idx, txtName.getText());
    		
    		if(update) {
    			tableViewSize.getSelectionModel().getSelectedItem().setName(txtName.getText());;
	    		txtName.clear();
	    		txtCode.clear();
	    		restaurant.saveDataofProductSize();
	    		loadTableView();
    		} else {
    			Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Size existing");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Tama?o en existencia.");
	    		alert.showAndWait();
    		}
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Empty fields");
    		alert.setHeaderText(null); 
    		alert.setContentText("El campo est? vac?o.");
    		alert.showAndWait();
    	}
    }

}
