package org.example.eventsystemassignment.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.Ticket;
import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.dal.db.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventTicketsDAO_DB implements IEventTickets {


    private DBConnector dbConnector = DBConnector.getInstance();

    public EventTicketsDAO_DB() throws IOException {
    }


    @Override
    public void createUser(User user) throws Exception {
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO dbo.[User](Username, Password, Usertype, Firstname, Lastname) Values (?, ?, ?, ?, ?) ");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUsertype());
            stmt.setString(4, user.getFirstname());
            stmt.setString(5, user.getLastname());
            stmt.executeUpdate();
        }

    }

    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> user = new ArrayList<>();
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.[User]");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String Usertype = rs.getString("Usertype");
                String Firstname = rs.getString("Firstname");
                String Lastname = rs.getString("Lastname");
                user.add(new User(id, Username, Password, Usertype, Firstname, Lastname));

            }
        }
        return user;

    }

    @Override
    public List<Event> getAllEvents() throws Exception {
        List<Event> event = new ArrayList<>();
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.[Event]");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String EventName = rs.getString("EventName");
                LocalTime StartTime = rs.getTime("StartTime").toLocalTime();
                Date StartDate = rs.getDate("StartDate");
                String Location = rs.getString("Location");
                String ExtraNotes = rs.getString("ExtraNotes");
                LocalTime EndTime = rs.getTime("EndTime").toLocalTime();
                Date EndDate = rs.getDate("EndDate");
                String LocationGuidance = rs.getString("LocationGuidance");

                event.add(new Event(id, EventName, StartTime, StartDate, Location, ExtraNotes, EndTime, EndDate, LocationGuidance));

            }
        }
        return event;

    }

    @Override
    public List<Ticket> getAllTickets() throws Exception {
        List<Ticket> ticket = new ArrayList<>();
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.[Ticket]");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String TicketEventName = rs.getString("TicketEventname");
                String TicketInfo = rs.getString("TicketInfo");


                ticket.add(new Ticket(id, TicketEventName, TicketInfo));
            }
        }
        return ticket;
    }

    @Override
    public void deleteUser(User user) throws Exception {
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM dbo.[User] WHERE Id = ?");
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();

        }
    }


    @Override
    public void createEvent(Event event) throws Exception {
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO dbo.[Event]" +
                    "(EventName, StartTime, StartDate, Location, ExtraNotes, EndTime, EndDate, LocationGuidance)" +
                    "Values (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, event.getEventName());
            stmt.setTime(2, Time.valueOf(event.getStartTime()));
            stmt.setDate(3, (Date) event.getStartDate());
            stmt.setString(4, event.getLocation());
            stmt.setString(5, event.getExtraNotes());
            stmt.setTime(6, Time.valueOf(event.getEndTime()));
            stmt.setDate(7, (Date) event.getEndDate());
            stmt.setString(8, event.getLocationGuidance());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteEvent(Event event) throws Exception {
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM dbo.Event WHERE Id = ?");
            stmt.setInt(1, event.getId());
            stmt.executeUpdate();


        }
    }

    @Override
    public void assignCoordinators(User selectedUser) throws Exception {

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE dbo.[User] SET Usertype = ? WHERE Id = ?");
            stmt.setString(1, "Event Coordinator");
            stmt.setInt(2, selectedUser.getId());
            stmt.executeUpdate();
        }


    }

    @Override
    public void addTicket(Event selectedEvent) throws Exception {
        try (Connection conn = dbConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO dbo.[Ticket](TicketEventname, TicketInfo) VALUES(?, ?)");
            stmt.setString(1, selectedEvent.getEventName());
            stmt.setString(2, "open");


            stmt.executeUpdate();
        }


    }

    @Override
    public boolean checkLogin(String username, String password, String usertype) {
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dbo.[User] WHERE Username = ? AND Password = ? AND Usertype = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, usertype);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean checkIfUserWasDeleted(User user) {
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dbo.[User] Where Id = ?");
            stmt.setInt(1, user.getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}