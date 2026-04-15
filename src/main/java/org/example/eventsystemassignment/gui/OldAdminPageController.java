package org.example.eventsystemassignment.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;


import java.io.IOException;

public class OldAdminPageController {


    @FXML
    private TableColumn<User, String> usernameColumn,
            passwordColumn, usertypeColumn;

    @FXML
    private TableColumn<Event, String> eventnameColumn, starttimeColumn, locationColumn, extranotesColumn, enddateandtimeColumn, locationguidanceColumn;


    private EventTicketsDAO_DB Database;

    public TableView<User> tblUsers;

    public TableView<Event> tblEvents;



    public void initialize() {

        try{
            Database = new EventTicketsDAO_DB();
        }
                catch (Exception e){
                    e.printStackTrace();
            }

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        usertypeColumn.setCellValueFactory(new PropertyValueFactory<>("usertype"));

        eventnameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        starttimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        extranotesColumn.setCellValueFactory(new PropertyValueFactory<>("extraNotes"));
        enddateandtimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        locationguidanceColumn.setCellValueFactory(new PropertyValueFactory<>("locationGuidance"));

        loadEventsIntoTable();
        loadUsersIntoTable();
    }
    public void loadUsersIntoTable(){
        try {
            EventTicketsDAO_DB dao = new EventTicketsDAO_DB();
            ObservableList<User> users = FXCollections.observableArrayList(dao.getAllUsers());
            tblUsers.setItems(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void loadEventsIntoTable(){
        try {
            EventTicketsDAO_DB dao = new EventTicketsDAO_DB();
            ObservableList<Event> events = FXCollections.observableArrayList(dao.getAllEvents());
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
            stage.setTitle("New/Edit user");
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
        Database.deleteEvent(selectedEvent);
        loadEventsIntoTable();

    }

    public void btnOnDeleteUser(ActionEvent actionEvent) throws Exception {
        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        Database.deleteUser(selectedUser);
        loadUsersIntoTable();
    }

    public void onAssignCoordinator(ActionEvent actionEvent) throws Exception {
        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        Database.assignCoordinators(selectedUser);
        loadUsersIntoTable();
    }
}
