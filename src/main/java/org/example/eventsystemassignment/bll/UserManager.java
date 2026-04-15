package org.example.eventsystemassignment.bll;

import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.dal.EventTicketsDAO_DB;
import org.example.eventsystemassignment.dal.IEventTickets;

import java.util.List;

public class UserManager {
    public UserManager() throws Exception{}

    private IEventTickets dao = new EventTicketsDAO_DB();

    public void createUser(User user) throws Exception{
        dao.createUser(user);
    }

    public List<User> getAllUsers() throws Exception{
        return dao.getAllUsers();
    }

    public void deleteUser(User user) throws Exception{
        dao.deleteUser(user);
    }

    public boolean checkLogin(String username, String password, String usertype){
        return dao.checkLogin(username, password, usertype);
    }

    public boolean checkIfUserWasDeleted(User user){
        return dao.checkIfUserWasDeleted(user);
    }

}
