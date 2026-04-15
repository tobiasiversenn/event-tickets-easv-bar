import org.example.eventsystemassignment.be.Event;
import org.example.eventsystemassignment.be.User;
import org.example.eventsystemassignment.bll.EventManager;
import org.example.eventsystemassignment.bll.TicketManager;
import org.example.eventsystemassignment.bll.UserManager;
import org.example.eventsystemassignment.gui.CreateUserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    private UserManager userManager;
    private CreateUserController createUserController;

    @BeforeEach
    public void Initialize() throws Exception {
        try{
            userManager = new UserManager();
            createUserController = new CreateUserController();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testSuccessfulLogin() throws Exception {
        // Arrange
        Boolean ExpectedLogin = true;
        //Act
        Boolean LoginResult = userManager.checkLogin("mike87", "summer21", "Admin");

        //Assert
        Assertions.assertEquals(ExpectedLogin, LoginResult);
    }

    @Test
    void testIncorrectLogin() throws Exception {
        // Arrange
        Boolean ExpectedLogin = false;
        //Act
        Boolean LoginResult = userManager.checkLogin("user", "user", "Admin");

        //Assert
        Assertions.assertEquals(ExpectedLogin, LoginResult);
    }


    @Test
    void testDeleteUser() throws Exception {
        // Arrange
        User newUser = new User("Ben", "Ben123Ben123Ben", "Admin", "", "");
        boolean deleteUser = true;

        //Act
        userManager.deleteUser(newUser);
        boolean wasUserDeleted = userManager.checkIfUserWasDeleted(newUser);

        //Assert

        Assertions.assertEquals(deleteUser, wasUserDeleted);

    }



    }
