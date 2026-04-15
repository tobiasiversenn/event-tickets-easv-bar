package org.example.eventsystemassignment.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.bll.EventManager;
import org.example.eventsystemassignment.bll.UserManager;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;

public class CreateUserController {


    public ChoiceBox cbUsertype;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    private UserManager userManager;

    public CreateUserController() {
        // Required by JavaFX
    }
    @FXML
    public void initialize() {
        try {
            userManager = new UserManager();

        } catch (Exception e) {
            e.printStackTrace();
        }

        cbUsertype.getItems().addAll(
                "Admin",
                "Event Coordinator"
        );

    }

    public void onCreateUserToDatabase(ActionEvent actionEvent) throws Exception {
        if (txtName.getText() == null || txtPassword.getText() == null || cbUsertype.getValue() == null)
        {
            MessageHandler.displayErrorAlertBox(new Exception("All fields must be entered"));
            return;
        }
        else if (txtPassword.getText().length() < 7){
            MessageHandler.displayErrorAlertBox(new Exception("Password must be at least 7 characters long"));
            return;
        }

        else if (txtName.getText().equals(txtPassword.getText())){
            MessageHandler.displayErrorAlertBox(new Exception("Username and password must not be the same"));
            return;
            }

        String userpassword = txtPassword.getText();
            boolean includesLetters = userpassword.matches(".*[A-Za-z].*");
            boolean includesNumbers = userpassword.matches(".*[1-9].*");
            if (includesLetters == false || includesNumbers == false){
                MessageHandler.displayErrorAlertBox(new Exception("Password must include both letters and numbers"));
                return;
            }

            boolean allSame = true;
            String password = txtPassword.getText();
            char firstChar = password.charAt(0);

            for (int i = 0; i < password.length(); i++){
                if (password.charAt(i) != firstChar)
                    allSame = false;
            }
            if (allSame == true){
                MessageHandler.displayErrorAlertBox(new Exception("All characters in the password must not be the same"));
                return;
            }

            System.out.println("Creating a new user");
            User user = new User(txtName.getText(), txtPassword.getText(), (String) cbUsertype.getValue(), "", "");
        try {
            userManager.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            userManager.getAllUsers();
        } catch (Exception e) {

        }
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }

}
