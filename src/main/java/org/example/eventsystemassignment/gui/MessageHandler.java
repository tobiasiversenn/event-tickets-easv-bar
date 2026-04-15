package org.example.eventsystemassignment.gui;

import javafx.scene.control.Alert;

public class MessageHandler {
    public static void displayErrorAlertBox(Exception t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");

        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
}
