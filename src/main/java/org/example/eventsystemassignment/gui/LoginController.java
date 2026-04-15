package org.example.eventsystemassignment.gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.eventsystemassignment.bll.TicketManager;
import org.example.eventsystemassignment.bll.UserManager;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;

import java.io.IOException;

public class LoginController {


    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    public ChoiceBox cbLoginType;


    private UserManager userManager;


    public void initialize() {
        try {
            userManager = new UserManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cbLoginType.getItems().addAll(
                "Admin",
                "Event Coordinator"
        );
    }

    public void onLogIn(ActionEvent actionEvent) throws SQLServerException, IOException {
        System.out.println("Login button clicked");
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String usertype = cbLoginType.getValue().toString();

        boolean success = userManager.checkLogin(username, password, usertype);
        if (success == true && usertype == "Admin"){
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/eventsystemassignment/adminpage.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Admin Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (success == true && usertype == "Event Coordinator"){
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/eventsystemassignment/eventcoordinatorpage.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Event Coordinator Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("Incorrect Username or Password");
            MessageHandler.displayErrorAlertBox(new Exception("Incorrect username or password"));
        }
    }
}
