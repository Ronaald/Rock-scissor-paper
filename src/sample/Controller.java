package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;


public class Controller implements Initializable {
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
    @FXML
    private ListView<FriendList> list = new ListView<FriendList>();

    ObservableList<FriendList> listView = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Databas databas = new Databas();
    long UserID = 0;

    public Controller() {

        databas.startaConnection();
    }

    public void logiinButton(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {

        String userName = userText.getText();
        String password = passwordText.getText();
        UserID = databas.login(userName, password);

        if (UserID > 0) {

            try {
                list.setItems(databas.getFriend(UserID));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contactlist.fxml"));

            Parent contactlistParent = loader.load();
            Scene contactlistScene = new Scene(contactlistParent);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(contactlistScene);
            window.show();
        } else {
            logMessage.setText("Username or password invalid");
        }
        userText.setText("");
        passwordText.setText("");
    }
}





   /* public void contactOne ( javafx.event.ActionEvent actionEvent) throws SQLException {
        int xx = databas.getFriend(UserID);
        friendOne.setText(Integer.toString(xx));
    }*/










