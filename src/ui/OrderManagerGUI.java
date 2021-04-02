package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Restaurant;

public class OrderManagerGUI {

	private Restaurant restaurant;
	
	@FXML
    private AnchorPane container;
	
	public OrderManagerGUI() {
		restaurant = new Restaurant();
	}
	
	@FXML
    public void showScreenOfExport(ActionEvent event) throws IOException {
		
		FXMLLoader open = new FXMLLoader(getClass().getResource("ChoseFileSeparator.fxml"));
    	open.setController(this);
    	Parent root = open.load();
    	
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.setTitle("Gestionar export");
    	stage.showAndWait();
    	
		
    }
	
	@FXML
    public void exportDataofOrder(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		   filechooser.setTitle("Open Import File");
		   File f = filechooser.showSaveDialog(container.getScene().getWindow());
		   
		   if(f != null) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Export orders");
			   try {
				   	restaurant.exportDataofOrder(f.getAbsolutePath());
					alert.setContentText("Las ordenes se han exportado correctamente");
					alert.showAndWait();
			} catch (Exception e) {
					alert.setContentText("La orden no se pudo exportar");
					alert.showAndWait();
			}
			   
		   }
    }
	
	@FXML
    public void cancelExport(ActionEvent event) {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
