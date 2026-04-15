package org.example.eventsystemassignment.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.bll.EventManager;
import org.example.eventsystemassignment.bll.TicketManager;
import org.example.eventsystemassignment.bll.UserManager;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;


import java.io.IOException;

public class AdminPageController {


    @FXML
    private TableColumn<User, String> usernameColumn, usertypeColumn, firstnameColumn, lastnameColumn;

    @FXML
    private TableColumn<Event, String> eventnameColumn, starttimeColumn, startdateColumn, locationColumn, extranotesColumn, endtimeColumn, enddateColumn, locationguidanceColumn;

    @FXML
    public ChoiceBox cbTableType;

    @FXML
    public Button btnAssignCoordinator;

    @FXML
    public Button btnDeleteEvent;

    @FXML
    public Button btnCreateUser;

    @FXML
    public Button btnDeleteUser;

    private EventManager eventManager;

    private UserManager userManager;

    private TicketManager ticketManager;

    public TableView<User> tblUsers;

    public TableView<Event> tblEvents;


    public void initialize() {

        try{
            userManager = new UserManager();
            eventManager = new EventManager();
            ticketManager = new TicketManager();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        cbTableType.getItems().addAll(
                "Manage Users",
                "Manage Events"
        );

        cbTableType.getSelectionModel().select("Manage Users");



        cbTableType.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;

            switch (newVal.toString()) {
                case "Manage Users" -> {
                    tblUsers.setVisible(true);
                    btnCreateUser.setVisible(true);
                    btnDeleteUser.setVisible(true);
                    tblEvents.setVisible(false);
                    btnDeleteEvent.setVisible(false);
                    btnAssignCoordinator.setVisible(false);

                }
                case "Manage Events" -> {
                    tblUsers.setVisible(false);
                    btnCreateUser.setVisible(false);
                    btnDeleteUser.setVisible(false);
                    tblEvents.setVisible(true);
                    btnDeleteEvent.setVisible(true);
                    btnAssignCoordinator.setVisible(true);}
            }
        });

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usertypeColumn.setCellValueFactory(new PropertyValueFactory<>("usertype"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        eventnameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        starttimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        startdateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        extranotesColumn.setCellValueFactory(new PropertyValueFactory<>("extraNotes"));
        endtimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        enddateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationguidanceColumn.setCellValueFactory(new PropertyValueFactory<>("locationGuidance"));


        loadEventsIntoTable();
        loadUsersIntoTable();


    }
    public void loadUsersIntoTable(){
        try {
            ObservableList<User> users = FXCollections.observableArrayList(userManager.getAllUsers());
            tblUsers.setItems(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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




    public void onGoBackToMainPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/eventsystemassignment/loginpage.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login Page");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void onCreateUser(ActionEvent actionEvent) throws Exception {
        System.out.println("Creating user");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eventsystemassignment/createuser.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setTitle("New user");
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
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

    public void btnOnDeleteUser(ActionEvent actionEvent) throws Exception {
        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        userManager.deleteUser(selectedUser);
        loadUsersIntoTable();
    }

    public void onAssignCoordinator(ActionEvent actionEvent) throws Exception {
        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        ticketManager.assignCoordinators(selectedUser);
        loadUsersIntoTable();
    }

    public void onRefreshPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/eventsystemassignment/adminpage.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Admin Page");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
