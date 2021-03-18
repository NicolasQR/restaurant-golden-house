package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	private Aja aja;
	
	public Main() {
		aja = new Aja();
	}
	
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main-Anchor-pane.fxml"));
		fxmlLoader.setController(aja);
		
		Parent root = fxmlLoader.load();
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Casa Dorada");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		aja.loadLogin();
	}

}
