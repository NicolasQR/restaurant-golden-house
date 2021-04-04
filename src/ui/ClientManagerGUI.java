package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
import model.Restaurant;

public class ClientManagerGUI {

	@FXML
    private TableView<Client> tableClient;
	
	@FXML
    private TableColumn<Client, String> columnName;

    @FXML
    private TableColumn<Client, String> columnLastName;

    @FXML
    private TableColumn<Client, String> columnId;

    @FXML
    private TableColumn<Client, String> columnAddress;

    @FXML
    private TableColumn<Client, String> columnPhone;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;
    
    @FXML
    private TextField txtScreen2Name;

    @FXML
    private TextField txtScreen2LastName;

    @FXML
    private TextField txtScreen2Id;

    @FXML
    private TextField txtScreen2Address;

    @FXML
    private TextField txtScreen2Phone;

    @FXML
    private TextArea txtAreaObservations;
    
    @FXML
    private TextArea txtScreen1Observations;
    
    private Restaurant restaurant;
    public ClientManagerGUI() {
    	restaurant = new Restaurant();
    }
    
    public Restaurant getRestaurant() {
		return restaurant;
	}

	@FXML
    public void btnNewClient(ActionEvent event) throws IOException {
    	FXMLLoader open = new FXMLLoader(getClass().getResource("AddClient.fxml"));
    	open.setController(this);
    	Parent root = open.load();
    	
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.setTitle("Gestionar clientes");
    	stage.showAndWait();
    }
    
    @FXML
    public void btnCancelCreatedofClient(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnCreateClient(ActionEvent event) throws FileNotFoundException, IOException {
    	
    	try {
    		String name = txtScreen2Name.getText();
        	String lastName = txtScreen2LastName.getText();
        	long id = Long.parseLong(txtScreen2Id.getText());
        	String address = txtScreen2Address.getText();
        	long phone = Long.parseLong(txtScreen2Phone.getText());
        	String observations = txtAreaObservations.getText();
        	
        	if(!name.equals("") && !lastName.equals("") && id > 0 && !address.equals("") && phone > 0) {
        		
        		restaurant.addClient(name, lastName, id, address, phone, observations);
        		
        		Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Client Created");
            	alert.setHeaderText(null);
            	alert.setContentText("El cliente ha sido creado correctamente");
            	alert.showAndWait();
            	
            	txtScreen2Name.setText("");
            	txtScreen2LastName.setText("");
            	txtScreen2Id.setText("");
            	txtScreen2Address.setText("");
            	txtScreen2Phone.setText("");
            	txtAreaObservations.setText("");
            	
            	tableView();
        	}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("Solo puedes poner caracteres númericos en identificación y telefono y no dejarlos vacíos");
    		alert.showAndWait();
		}
    	
    }
    
    @FXML
    public void disableClient(ActionEvent event) throws FileNotFoundException, IOException {
    	int index = tableClient.getSelectionModel().getFocusedIndex();
    	restaurant.getClients().get(index).updateStatus(false);
    	restaurant.saveDataofClient();
    }

    @FXML
    public void enableClient(ActionEvent event) throws FileNotFoundException, IOException {
    	int index = tableClient.getSelectionModel().getFocusedIndex();
    	restaurant.getClients().get(index).updateStatus(true);
    	restaurant.saveDataofClient();
    }
    
    public void tableView() {
    	ObservableList<Client> datos;
    	datos = FXCollections.observableArrayList(restaurant.getClients());
    	
    	tableClient.setItems(datos);
    	this.columnName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	this.columnLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
    	this.columnId.setCellValueFactory(new PropertyValueFactory<Client, String>("ID"));
    	this.columnAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
    	this.columnPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
    	
    	tableClient.setRowFactory( tv -> {
			TableRow<Client> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Client rowData = row.getItem();
					txtName.setText(rowData.getName());
					txtLastName.setText(rowData.getLastName());
					toString();
					txtId.setText(String.valueOf(rowData.getID()));
					txtAddress.setText(rowData.getAddress());
					txtPhone.setText(String.valueOf(rowData.getPhone()));
					txtScreen1Observations.setText(rowData.getObservations());
					
				}
			});
			return row ;
		});
    }
    
    public void receiveData(Restaurant a) {
    	restaurant = a;
    }
    
    @FXML
    public void updateClienInformation(ActionEvent event) throws FileNotFoundException, IOException {
    	int index = tableClient.getSelectionModel().getFocusedIndex();
   	 
    	restaurant.updateClient(index, txtName.getText(), txtLastName.getText(), Long.parseLong(txtId.getText())
    			, txtAddress.getText(), Long.parseLong(txtPhone.getText()));
    }
    
    @FXML
    public void deleteClient(ActionEvent event) throws FileNotFoundException, IOException {
    	Client p = tableClient.getSelectionModel().getSelectedItem();
    	restaurant.getClients().remove(p);
    	restaurant.saveDataofClient();
    	tableView();
    }
    
    public void initialize() {
    	tableView();
    }
}
