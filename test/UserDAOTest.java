import Controller.UserDAO;
import Model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDAOTest {

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword");
        UserDAO.save(testUser);
    }

    @After
    public void tearDown() {
        List<User> users = UserDAO.getAllUsers();
        for (User user : users) {
            if ("testuser".equals(user.getUsername())) {
                UserDAO.delete(user);
            }
        }
    }

    @Test
    public void testSave() {
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setPassword("newpassword");

        int status = UserDAO.save(newUser);
        assertEquals(1, status);

        // Bersihkan user yang baru saja ditambahkan
        UserDAO.delete(newUser);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = UserDAO.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetUserById() {
        List<User> users = UserDAO.getAllUsers();
        User lastInsertedUser = users.get(users.size() - 1);
        User user = UserDAO.getUserById(lastInsertedUser.getId());
        assertNotNull(user);
        assertEquals(testUser.getUsername(), user.getUsername());
        assertEquals(testUser.getPassword(), user.getPassword());
    }

    @Test
    public void testDelete() {
        List<User> users = UserDAO.getAllUsers();
        User lastUser = users.get(users.size() - 1);
        int status = UserDAO.delete(lastUser);
        assertEquals(1, status);

        User deletedUser = UserDAO.getUserById(lastUser.getId());
        assertNull(deletedUser);
    }
    
    @Test
public void testSaveWithNullUser() {
    // Test saving when the User object is null
    int status = UserDAO.save(null);
    assertEquals(0, status); // Expected: Save fails and returns status 0
}

@Test
public void testSaveWithEmptyUsername() {
    // Test saving when the username is empty
    User user = new User();
    user.setUsername("");
    user.setPassword("password");

    int status = UserDAO.save(user);
    assertEquals(0, status); // Expected: Save fails and returns status 0
}

@Test
public void testGetUserByInvalidId() {
    // Test retrieving a user with an ID that does not exist in the database
    User user = UserDAO.getUserById(-1); // ID -1 is most likely invalid
    assertNull(user); // Expected: No user is found, so the result is null
}

@Test
public void testDeleteNonExistentUser() {
    // Test deleting a user that does not exist in the database
    User nonExistentUser = new User();
    nonExistentUser.setId(-1); // Use an invalid ID

    int status = UserDAO.delete(nonExistentUser);
    assertEquals(0, status); // Expected: Deletion fails and returns status 0
}

@Test
public void testGetAllUsersWhenNoUsers() {
    // Example: Delete all users to ensure the database is empty
    List<User> users = UserDAO.getAllUsers();
    for (User user : users) {
        UserDAO.delete(user); // Delete all existing users
    }

    List<User> emptyUsers = UserDAO.getAllUsers();
    assertTrue(emptyUsers.isEmpty()); // Expected: The users list is empty
}

}
