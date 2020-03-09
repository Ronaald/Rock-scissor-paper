package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
//import javafx.event.ActionEvent;

//import java.awt.*;
//import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;


public class Controller {
    @FXML
    javafx.scene.control.Button loginButton;

    @FXML
    PasswordField passwordText;
    @FXML
    javafx.scene.control.TextField userText;
    @FXML
    javafx.scene.control.Label logMessage;
    @FXML
    javafx.scene.control.Label friendOne;


    Databas databas = new Databas();
    long UserID = 0;
    public Controller() {

        databas.startaConnection();

    }


    public void logiinButton(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {

        String userName = userText.getText();
        String password = passwordText.getText();
        UserID = databas.login(userName, password);

        if(UserID > 0) {
            Parent contactlistParent = FXMLLoader.load(getClass().getResource("contactlist.fxml"));

            Scene contactlistscene = new Scene (contactlistParent);
            Stage window = (Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(contactlistscene);
            window.show();
        } else {
            logMessage.setText("Username or password invalid");
        }
        userText.setText("");
        passwordText.setText("");
    }

    public void contactOne ( javafx.event.ActionEvent actionEvent) throws SQLException {
        int xx = databas.getFriend(UserID);
        friendOne.setText(Integer.toString(xx));
    }
}


