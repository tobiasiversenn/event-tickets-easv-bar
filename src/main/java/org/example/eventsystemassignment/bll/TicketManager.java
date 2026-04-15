package org.example.eventsystemassignment.bll;

import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.Ticket;
import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;
import org.example.eventsystemassignment.dal.IEventTickets;

import java.util.List;

public class TicketManager {
    public TicketManager() throws Exception{}


    private IEventTickets dao = new EventTicketsDAO_DB();



    public List<Ticket> getAllTickets() throws Exception{
        return dao.getAllTickets();
    }

    public void addTicket(Event selectedEvent) throws Exception{
        dao.addTicket(selectedEvent);
    }

    public void assignCoordinators(User selectedUser) throws Exception {
        dao.assignCoordinators(selectedUser);
    }

}
