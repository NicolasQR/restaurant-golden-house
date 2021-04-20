package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private LoginGUI goldenHouseGUI;
	
	public Main() {
		goldenHouseGUI = new LoginGUI();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main-Anchor-pane.fxml"));
		fxmlLoader.setController(goldenHouseGUI);
		
		Parent root = fxmlLoader.load();
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Casa Dorada");
		primaryStage.setResizable(false);
		primaryStage.show();
		goldenHouseGUI.loadLogin();
		goldenHouseGUI.runThread();
	}

}
