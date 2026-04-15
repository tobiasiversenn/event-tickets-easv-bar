package org.example.eventsystemassignment.be;

public class User {

    private String username;
    private String usertype;
    private String password;
    private String firstname;
    private String lastname;
    private int id;


    public User(int id, String username, String password, String usertype, String firstname, String lastname){
        this.id = id;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String username, String password, String usertype, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }


    public String getUsertype(){
        return usertype;
    }

    public void setUsertype(String usertype){
        this.usertype = usertype;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setFirstname(String firstname){ this.firstname = firstname; }

    public String getFirstname(){
        return firstname;
    }

    public void setLastname(String lastname){ this.lastname = lastname; }

    public String getLastname(){
        return lastname;
    }
}
