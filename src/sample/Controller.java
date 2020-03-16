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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;


public class Controller implements Initializable {
    public ListView list;
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






    //@FXML
    //private ListView<FriendList> list;// = new ListView<FriendList>();

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
                //list.setItems();
                listView.addAll(databas.getFriend(UserID));

                //list.setItems(listView);
                //list.setVisible(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*
            StackPane root = new StackPane();
            root.getChildren().add(list);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 200, 200));
            window.show();*/
/*
            AnchorPane root = new AnchorPane();
            list.setPrefWidth(200D);
            list.setPrefHeight(200D);
            list.setLayoutY(50);
            list.setLayoutX(50);
            root.getChildren().add(list);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 600, 500));
            window.show();*/



            FXMLLoader loader = new FXMLLoader(getClass().getResource("contactlist.fxml"));

            AnchorPane contactlistParent = (AnchorPane)loader.load();
            Scene contactlistScene = new Scene(contactlistParent);
/*
            javafx.collections.ObservableList<javafx.scene.Node> children = contactlistParent.getChildren();
            for(int i =0; i  < children.size(); i++) {
                Node lw = children.get(i);
                if (lw.getId().equals("list") == false) continue;;
                ((ListView) lw).setItems(listView);
            }*/
            contactlistParent.getChildren().add(list);
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










