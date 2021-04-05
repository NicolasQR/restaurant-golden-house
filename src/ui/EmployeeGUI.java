package ui;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Employee;
import model.Restaurant;

public class EmployeeGUI {

	private Restaurant restaurant;
	
	public EmployeeGUI() {
		restaurant = new Restaurant();
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	
	@FXML
    private AnchorPane container;
	
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
        	boolean employeeRepeat = false;
        	
        	if(!name.equals("") && !lastName.equals("") && id > 0) {
        		
        		for(int i = 0; i < restaurant.getEmployee().size(); i++) {
        			if(restaurant.getEmployee().get(i).getID() == id) {
        				employeeRepeat = true;
        				
        				Alert alert = new Alert(AlertType.WARNING);
                		alert.setTitle("Warning");
                		alert.setHeaderText(null);
                		alert.setContentText("Ya existe un empleado con este número de identificación, intenta con otro número");
                		alert.showAndWait();
        			}
        		}
        		
        		if(!employeeRepeat) {
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
        		}
        		

        	} else {
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Warning");
        		alert.setHeaderText(null);
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

    @FXML
    public void disableEmployee(ActionEvent event) throws FileNotFoundException, IOException {
    	
    	
    	int index = tableEmployee.getSelectionModel().getFocusedIndex();
    	restaurant.getEmployee().get(index).setStatus(false);
    	restaurant.saveDataOfEmployees();
    }
    

    @FXML
    public void eliminateEmployee(ActionEvent event) throws FileNotFoundException, IOException {
    	Employee p = tableEmployee.getSelectionModel().getSelectedItem();
    	restaurant.getEmployee().remove(p);
    	restaurant.saveDataOfEmployees();
    	tableView();
    }

    @FXML
    public void enableEmployee(ActionEvent event) throws FileNotFoundException, IOException {
    	int index = tableEmployee.getSelectionModel().getFocusedIndex();
    	restaurant.getEmployee().get(index).setStatus(true);
    	restaurant.saveDataOfEmployees();
    }

    @FXML
    public void upgradeEmployee(ActionEvent event) throws IOException {
    	int index = tableEmployee.getSelectionModel().getFocusedIndex();
    	
    	restaurant.updateEmployee(index, txtNameEmployeeScreen1.getText(), txtLastNameEmployeeScreen1.getText(), Long.parseLong(txtIdEmployeeScreen1.getText()));
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("User Upgrade");
    	alert.setHeaderText(null);
    	alert.setContentText("El empleado ha sido modificado correctamente");
    	alert.showAndWait();
    	
    }
    
    public void tableView() {
    	ObservableList<Employee> datos;
    	datos = FXCollections.observableArrayList(restaurant.getEmployee());
    	
    	tableEmployee.setItems(datos);
    	this.nameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    	this.lastNameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
    	this.idEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("iD"));
    	
    	tableEmployee.setRowFactory( tv -> {
			TableRow<Employee> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Employee rowData = row.getItem();
					//updateButton.setDisable(false);
					txtNameEmployeeScreen1.setText(rowData.getName());
					txtLastNameEmployeeScreen1.setText(rowData.getLastName());
					toString();
					txtIdEmployeeScreen1.setText(String.valueOf(rowData.getID()));
				}
			});
			return row ;
		});
    }
    
    public void receiveData(Restaurant a) {
    	restaurant = a;
    }
    
    @FXML
    public void exportEmployees(ActionEvent event) {
    	
    	FileChooser filechooser = new FileChooser();
		   filechooser.setTitle("Open Import File");
		   filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		   File f = filechooser.showSaveDialog(container.getScene().getWindow());
		   
		   if(f != null) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Export employee");
			   alert.setHeaderText(null);
			   		try {
			   			restaurant.exportDataofEmployee(f.getAbsolutePath());
				   		alert.setContentText("Los empleados se han exportado correctamente");
				   		alert.showAndWait();
			   		} catch (Exception e) {
			   			alert.setContentText("Los empleados no se pudieron exportar");
			   			alert.showAndWait();
			   		}
		   }
    }

	public void initialize() {
		tableView();
	}
    
    
}
