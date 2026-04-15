package org.example.eventsystemassignment.bll;

import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;
import org.example.eventsystemassignment.dal.IEventTickets;

import java.io.IOException;
import java.util.List;

public class EventManager {
    public EventManager() throws Exception{}


    private IEventTickets dao = new EventTicketsDAO_DB();

    public List<Event> getAllEvents() throws Exception {
        return dao.getAllEvents();
    }

    public void createEvent(Event event) throws Exception {
        dao.createEvent(event);
    }

    public void deleteEvent(Event event) throws Exception {
        dao.deleteEvent(event);
    }




}
