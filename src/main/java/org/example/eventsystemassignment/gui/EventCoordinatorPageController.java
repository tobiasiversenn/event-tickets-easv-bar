package org.example.eventsystemassignment.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.Ticket;
import org.example.eventsystemassignment.bll.EventManager;
import org.example.eventsystemassignment.bll.TicketManager;
import org.example.eventsystemassignment.bll.UserManager;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;

import java.io.IOException;

public class EventCoordinatorPageController {

    @FXML
    private TableColumn<Event, String> eventnameColumn, starttimeColumn, startdateColumn, locationColumn, extranotesColumn, endtimeColumn, enddateColumn, locationguidanceColumn;

    @FXML
    private TableColumn<Ticket, String> eventnameTicketsColumn, ticketColumn;


    public TableView<Event> tblEvents;
    public TableView<Ticket> tblTickets;
    private TicketManager ticketManager;
    private EventManager eventManager;


    private TicketPageController ticketPageController;


    public void initialize() throws Exception {
        ticketManager = new TicketManager();
        eventManager = new EventManager();
        ticketPageController = new TicketPageController();

        eventnameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        starttimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        startdateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        extranotesColumn.setCellValueFactory(new PropertyValueFactory<>("extraNotes"));
        endtimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        enddateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationguidanceColumn.setCellValueFactory(new PropertyValueFactory<>("locationGuidance"));


        eventnameTicketsColumn.setCellValueFactory(new PropertyValueFactory<>("ticketEventName"));
        ticketColumn.setCellValueFactory(new PropertyValueFactory<>("ticket"));


        loadEventsIntoTable();
        loadTicketsIntoTable();
        addPrintButtonToTicketColumn();
    }


    public void loadEventsIntoTable(){
        try {
            ObservableList<Event> events = FXCollections.observableArrayList(eventManager.getAllEvents());
            tblEvents.setItems(events);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTicketsIntoTable(){
        try {
            ObservableList<Ticket> tickets = FXCollections.observableArrayList(ticketManager.getAllTickets());
            tblTickets.setItems(tickets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void onGoBackToMainPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/eventsystemassignment/loginpage.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login page");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onCreateEvent(ActionEvent actionEvent) {
        System.out.println("Creating event");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/eventsystemassignment/createevent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New event");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);

            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void onDeleteEvent(ActionEvent actionEvent) throws Exception {

        Event selectedEvent = tblEvents.getSelectionModel().getSelectedItem();
        eventManager.deleteEvent(selectedEvent);
        loadEventsIntoTable();

    }


    public void onAddTicket(ActionEvent actionEvent) throws Exception {
        Event selectedEvent = tblEvents.getSelectionModel().getSelectedItem();

        ticketManager.addTicket(selectedEvent);
        System.out.println("Add ticket button clicked");
        loadTicketsIntoTable();



    }

    private void addPrintButtonToTicketColumn() {
        ticketColumn.setCellFactory(col -> new TableCell<>() {

            private final Button btn = new Button("Print");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(btn);
                    box.setAlignment(Pos.CENTER); // <-- centers the button
                    box.setFillHeight(true);

                    btn.setOnAction(e -> {
                        Ticket ticket = getTableView().getItems().get(getIndex());
                        System.out.println("Printing ticket for: " + ticket.getTicketEventName());



                        Event foundEvent = null;

                        for (Event ev : tblEvents.getItems()) {
                            if (ev.getEventName().equals(ticket.getTicketEventName())) {
                                foundEvent = ev;
                                break;
                            }
                        }

                        if (foundEvent == null) {
                            System.out.println("Event not found!");
                            return;
                        }


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eventsystemassignment/ticket.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        TicketPageController controller = loader.getController();

                        controller.setTicketData(foundEvent);

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Ticket");
                        stage.show();

                    });

                    setGraphic(box);
                }
            }
        });
    }

    public void onRefreshPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/eventsystemassignment/eventcoordinatorpage.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Coordinator Page");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
