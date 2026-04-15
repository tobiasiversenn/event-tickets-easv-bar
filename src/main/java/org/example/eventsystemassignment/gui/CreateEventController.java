package org.example.eventsystemassignment.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.bll.EventManager;
import org.example.eventsystemassignment.bll.TicketManager;
import org.example.eventsystemassignment.bll.UserManager;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateEventController {

    @FXML
    private TextField txtEventName;

    @FXML
    private TextField txtStartTime;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtExtraNotes;

    @FXML
    private TextField txtEndTime;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtLocationGuidance;


    private EventManager eventManager;


    public CreateEventController() {
        // Required by JavaFX
    }

    @FXML
    public void initialize() {
        try {
            eventManager = new EventManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onCreateEventToDatabase(ActionEvent actionEvent) throws Exception {
        try {
            if (txtEventName.getText().isBlank() || txtStartTime.getText().isBlank() || txtStartDate.getText().isBlank()
                    || txtLocation.getText().isBlank() || txtExtraNotes.getText().isBlank()) {
                MessageHandler.displayErrorAlertBox(new Exception("Please enter all mandatory fields"));
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate StartDate = LocalDate.parse(txtStartDate.getText(), formatter);
            LocalDate EndDate = LocalDate.parse(txtEndDate.getText(), formatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTime = LocalTime.parse(txtStartTime.getText(), timeFormatter);
            LocalTime endTime = LocalTime.parse(txtEndTime.getText(), timeFormatter);

            java.sql.Date sqlStartDate = java.sql.Date.valueOf(StartDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(EndDate);
            Event event = new Event(txtEventName.getText(), startTime, sqlStartDate, txtLocation.getText(),
                    txtExtraNotes.getText(), endTime, sqlEndDate, txtLocationGuidance.getText());
            eventManager.createEvent(event);
            eventManager.getAllEvents();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            MessageHandler.displayErrorAlertBox(e);
        }
    }
}
