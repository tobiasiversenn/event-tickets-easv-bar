package org.example.eventsystemassignment.dal;

import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.Ticket;
import org.example.eventsystemassignment.be.User;

import java.util.List;

public interface IEventTickets {
    void createUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;

    List<Event> getAllEvents() throws Exception;

    List<Ticket> getAllTickets() throws Exception;

    void deleteUser(User user) throws Exception;

    void createEvent(Event event) throws Exception;

    void deleteEvent(Event event) throws Exception;

    void assignCoordinators(User selectedUser) throws Exception;

    void addTicket(Event selectedEvent) throws Exception;

    public boolean checkLogin(String username, String password, String usertype);

    public boolean checkIfUserWasDeleted(User user);

}
