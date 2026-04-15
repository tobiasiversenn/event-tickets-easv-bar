package org.example.eventsystemassignment.be;

public class Ticket {
    private int id;
    private String ticketeventname;
    private String ticket;

    private Event event;

    public Ticket(int id, String ticketeventname, String ticket) {
        this.id = id;
        this.ticketeventname = ticketeventname;
        this.ticket = ticket;
    }

    public Ticket(String ticketeventname, String ticket) {

        this.ticketeventname = ticketeventname;
        this.ticket = ticket;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTicketEventName(){
        return ticketeventname;
    }
    public void setTicketEventName(String ticketeventname){
        this.ticketeventname = ticketeventname;
    }

    public String getTicket(){
        return ticket;
    }
    public void setTicket(String ticket){
        this.ticket = ticket;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
