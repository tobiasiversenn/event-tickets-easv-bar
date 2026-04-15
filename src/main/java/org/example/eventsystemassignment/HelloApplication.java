package org.example.eventsystemassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginpage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();


        //Scene scene = new Scene(fxmlLoader.load(), 450, 500);
    }
}
