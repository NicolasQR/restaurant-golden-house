package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;

public class UsersGUI {

	
	@FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> columnNameUser;

    @FXML
    private TableColumn<User, String> columnLastNameUser;

    @FXML
    private TableColumn<User, ?> columnIdUser;

    @FXML
    private TableColumn<User, String> columnUser;

    @FXML
    void eliminateUserAccount(ActionEvent event) {

    }

    @FXML
    void upgradeUserAccount(ActionEvent event) {

    }
}
