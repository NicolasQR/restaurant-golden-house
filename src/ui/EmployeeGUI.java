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
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Employee;
import model.Restaurant;

public class EmployeeGUI {

	private Restaurant restaurant;
	public EmployeeGUI() {
		restaurant = new Restaurant();
	}
	
	@FXML
    private TextField txtNameEmployeeScreen1;

    @FXML
    private TextField txtLastNameEmployeeScreen1;

    @FXML
    private TextField txtIdEmployeeScreen1;
	
	@FXML
    private AnchorPane auxAnchorPane;
	
	@FXML
    private TextField txtNameEmployee;

    @FXML
    private TextField txtLastNameEmployee;

    @FXML
    private TextField txtIdEmployee;
	
	@FXML
    private TableView<Employee> tableEmployee;

    @FXML
    private TableColumn<Employee, String> nameEmployee;

    @FXML
    private TableColumn<Employee, String> lastNameEmployee;

    @FXML
    private TableColumn<Employee, String> idEmployee;

    @FXML
    public void addEmployee(ActionEvent event) throws IOException {

    	FXMLLoader open = new FXMLLoader(getClass().getResource("addEmployee.fxml"));
    	open.setController(this);
    	Parent root = open.load();
    	
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.setTitle("Gestionar empleados");
    	stage.showAndWait();
    }
    
    @FXML
    public void createEmployee(ActionEvent event) throws FileNotFoundException, IOException {
    
    	try {
    		String name = txtNameEmployee.getText();
        	String lastName = txtLastNameEmployee.getText();
        	long id = Long.parseLong(txtIdEmployee.getText());
        	
        	if(!name.equals("") && !lastName.equals("") && id > 0) {
        		restaurant.addEmployee(name, lastName, id);
        		
        		
        		Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Employee Created");
            	alert.setHeaderText(null);
            	alert.setContentText("El empleado ha sido creado correctamente");
            	alert.showAndWait();
            	
            	txtNameEmployee.setText("");
            	txtLastNameEmployee.setText("");
            	txtIdEmployee.setText("");
            	tableView();

        	} else {
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Warning");
        		alert.setHeaderText("Error");
        		alert.setContentText("Debes diligenciar toda la información requerida para crear un empelado");
        		alert.showAndWait();
        	}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("Error");
    		alert.setContentText("Debes diligenciar solo caracteres númericos en Identificación y no dejarla sin llenar");
    		alert.showAndWait();
		}
    	
    	
    }
    
    @FXML
    public void cancelCreateEmployee(ActionEvent event) throws IOException {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @SuppressWarnings("unchecked")
	@FXML
    public void disableEmployee(ActionEvent event) {
    	
    	//TableRow<Employee> row = new TableRow<Employee>();
    	//row = (TableRow<Employee>) tableEmployee.getSelectionModel().getSelectedItems();
    	//row.setStyle("-fx-background-color: green;");
    	int index = tableEmployee.getSelectionModel().getFocusedIndex();
    	restaurant.getEmployee().get(index).setStatus(false);
    	System.out.println(restaurant.getEmployee().get(index).getStatus());
    }
    

    @FXML
    public void eliminateEmployee(ActionEvent event) throws FileNotFoundException, IOException {
    	Employee p = tableEmployee.getSelectionModel().getSelectedItem();
    	restaurant.getEmployee().remove(p);
    	restaurant.saveDataOfEmployees();
    	tableView();
    }

    @FXML
    public void enableEmployee(ActionEvent event) {
    	int index = tableEmployee.getSelectionModel().getFocusedIndex();
    	restaurant.getEmployee().get(index).setStatus(true);
    	System.out.println(restaurant.getEmployee().get(index).getStatus());
    }

    @FXML
    public void upgradeEmployee(ActionEvent event) throws IOException {
    	int index = tableEmployee.getSelectionModel().getFocusedIndex();
    	 
    	restaurant.getEmployee().get(index).setName(txtNameEmployeeScreen1.getText());
    	restaurant.getEmployee().get(index).setLastName(txtLastNameEmployeeScreen1.getText());
    	restaurant.getEmployee().get(index).setID(Long.parseLong(txtIdEmployeeScreen1.getText()));
    	restaurant.saveDataOfEmployees();
    	
    	try {
			restaurant.loadDatafEmployee();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	tableView();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("User Upgrade");
    	alert.setHeaderText(null);
    	alert.setContentText("El empleado ha sido modificado correctamente");
    	alert.showAndWait();
    	
    }
    
    @FXML
    public void showTableInformation(MouseEvent event) {
    	Employee p = tableEmployee.getSelectionModel().getSelectedItem();
    	
    	if(p != null) {
    		txtNameEmployeeScreen1.setText(p.getName());
        	txtLastNameEmployeeScreen1.setText(p.getLastName());
        	txtIdEmployeeScreen1.setText(String.valueOf(p.getID()));
    	}
    }
    
    
    public void tableView() {
    	ObservableList<Employee> datos;
    	datos = FXCollections.observableArrayList(restaurant.getEmployee());
    	
    	tableEmployee.setItems(datos);
    	this.nameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    	this.lastNameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
    	this.idEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("iD"));
    }
    
    public void receiveData(Restaurant a) {
    	restaurant = a;
    }

	public void initialize() {
		tableView();
	}
    
    
}
