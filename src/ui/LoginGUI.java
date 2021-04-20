package ui;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class LoginGUI implements Runnable{
	
	@FXML
    private TextField txtTime;

    @FXML
    private TextField txtDate;
	
	@FXML
    private AnchorPane mainAnchorPane;
	
	@FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserLastName;

    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private TextField loginUser;
    
    @FXML
    private PasswordField loginPassword;
    
    private String hour, minutes, seconds;
    
    private String day, month, year;
    
	private  Thread h1;
	
    private Restaurant restaurant;
    
    private MenuGUI menuController;
    
    public LoginGUI() {
    	restaurant = new Restaurant();
    	menuController = new MenuGUI();
    	
    	try {
			restaurant.loadDataofUsers();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    }
    
    public void runThread(){
    	h1 = new Thread(this);
    	h1.start();
    }
    
    @Override
	public void run() {
    	
		Thread ct = Thread.currentThread();
		while(ct == h1)  {
			calcula();
			txtTime.setText(hour + ":" + minutes + ":" + seconds);
			txtDate.setText(day + "/" + month + "/" + year);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}
		}
		
	}
	
	private void calcula() {
		
		Calendar calendar = new GregorianCalendar();
		Date DateAndHour = new Date();
		calendar.setTime(DateAndHour);
		
		int h = Calendar.HOUR_OF_DAY;
		int m = Calendar.MINUTE;
		int s = Calendar.SECOND;
		
		hour = calendar.get(h) > 9?"" + calendar.get(h):"0"+ calendar.get(h);
		minutes = calendar.get(m) > 9?"" + calendar.get(m):"0"+ calendar.get(m);
		seconds = calendar.get(s) > 9?"" + calendar.get(s):"0"+ calendar.get(s);
		
		int d = Calendar.DAY_OF_MONTH;
		int mnth = Calendar.MONTH;
		int y = Calendar.YEAR;
		
		day = calendar.get(d) > 9?"" + calendar.get(d):"0"+ calendar.get(d);
		month = calendar.get(mnth) > 9?"" + calendar.get(mnth):"0"+ calendar.get(mnth);
		year = calendar.get(y) > 9?"" + calendar.get(y):"0"+ calendar.get(y);
	}

	public MenuGUI getMenuController() {
		return menuController;
	}

	public void setMenuController(MenuGUI menuController) {
		this.menuController = menuController;
	}
    
	public void loadLogin() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent root = fxmlLoader.load();
		
		mainAnchorPane.getChildren().clear();
		mainAnchorPane.getChildren().setAll(root);
    }
	
	@FXML
    public void btnRegisterUser(ActionEvent event) throws IOException {
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
    	
		mainAnchorPane.getChildren().clear();
		mainAnchorPane.getChildren().setAll(root);
    }
	
	@FXML
    public void btnGoToLoginPane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent root = fxmlLoader.load();
    	
		mainAnchorPane.getChildren().clear();
		mainAnchorPane.getChildren().setAll(root);
    }
	
	@FXML
    public void createUser(ActionEvent event) throws IOException {
		
		
		try {
			String userName = txtUserName.getText();
			String userLastName = txtUserLastName.getText();
			long id = Long.parseLong(txtId.getText());
			String user = txtUser.getText();
			String password = txtPassword.getText();
			boolean userRepeat = false;
			
			if(!userName.equals("") && !userLastName.equals("") && id != 0 &&
					!user.equals("") && !password.equals("")) {
				
				for(int i = 0; i < restaurant.getUsers().size(); i++) {
					
					if(restaurant.getUsers().get(i).getID() == id) {
						
						Alert alert = new Alert(AlertType.WARNING);
			    		alert.setTitle("Warning");
			    		alert.setHeaderText(null);
			    		alert.setContentText("Ya existe un usuario con ese número de identificación, intenta con otro número");
			    		alert.showAndWait();
			    		
			    		userRepeat = true;
					}
				}
				
				if(!userRepeat) {
					restaurant.addUsers(userName, userLastName, id, user, password);
					
					
					txtUserName.setText("");
					txtUserLastName.setText("");
					txtId.setText("");
					txtUser.setText("");
					txtPassword.setText("");
					
					Alert alert = new Alert(AlertType.INFORMATION);
		        	alert.setTitle("User Creted");
		        	alert.setHeaderText(null);
		        	alert.setContentText("El usuario ha sido creado correctamente");
		        	alert.showAndWait();
				}
				
	        	
			} else {
				Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Warning");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Debes diligenciar toda la información requerida para crear un usuario");
	    		alert.showAndWait();
			}
			
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText(null);
    		alert.setContentText("Solo puedes poner caracteres númericos en identificación y no dejarlo vacío");
    		alert.showAndWait();
		}
		
    }
	
	@FXML
    public void logIn(ActionEvent event) throws IOException {
		boolean login = false;
		runThread(); 
		for(int i = 0; i < restaurant.getUsers().size() ; i++) {
    		
    		if(restaurant.getUsers().get(i).getUserName().equals(loginUser.getText()) &&
    				restaurant.getUsers().get(i).getPassword().equals(String.valueOf(loginPassword.getCharacters()))) {
    			
    	    	login = true;
    	    	break; //comprobar si funciona
    	    	
    		}
		}
		if(login){
			 
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
			fxmlLoader.setController(menuController);
			Parent root = fxmlLoader.load();
			
			mainAnchorPane.getChildren().clear();
			mainAnchorPane.getChildren().setAll(root);
	
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
   		 	alert.setTitle("Login incorrect");
   		 	alert.setContentText("El usuario o la contraseña es incorrecta.");
   		 	alert.showAndWait();
		}
	
	}
}
