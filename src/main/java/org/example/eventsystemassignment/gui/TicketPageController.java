package org.example.eventsystemassignment.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.eventsystemassignment.be.Event;

public class TicketPageController {

    @FXML private Label lblEventName;
    @FXML private Label lblStartTime;
    @FXML private Label lblStartDate;
    @FXML private Label lblLocation;
    @FXML private Label lblEventNotes;
    @FXML private Label lblEndTime;
    @FXML private Label lblEndDate;
    @FXML private Label lblLocationGuidance;

    public void setTicketData(Event foundEvent) {
        lblEventName.setText("Eventname: " + foundEvent.getEventName());
        lblStartTime.setText("Time: " + foundEvent.getStartTime().toString());
        lblStartDate.setText("Date: " + foundEvent.getStartDate());
        lblLocation.setText("Location: " + foundEvent.getLocation());
        lblEventNotes.setText("Notes: " + foundEvent.getExtraNotes());
        lblEndTime.setText("End time: " + foundEvent.getEndTime().toString());
        lblEndDate.setText("End date: " + foundEvent.getEndDate());
        lblLocationGuidance.setText("Location guidance: " + foundEvent.getLocationGuidance());
    }
}
