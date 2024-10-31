/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.ReservationDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fatih
 */
import Database.DBConnection;
import Model.Reservation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ReservationDAOTest {
    private Reservation testReservation;

    @Before
    public void setUp() {
        testReservation = new Reservation();
        testReservation.setFirst_name("John");
        testReservation.setLast_name("Doe");
        testReservation.setPhone("1234567890");
        testReservation.setEmail("john.doe@gmail.com");
        testReservation.setPeople(4);
        testReservation.setDate("2024-12-25");
        testReservation.setTime("18:00");

        // Simpan reservasi untuk digunakan dalam pengujian
        ReservationDAO.save(testReservation);
    }

    @After
    public void tearDown() {
        // Hapus data reservasi yang digunakan untuk pengujian
        try (Connection conn = new DBConnection().setConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM restoku_db.reservations WHERE first_name = ? AND last_name = ?")) {
            ps.setString(1, testReservation.getFirst_name());
            ps.setString(2, testReservation.getLast_name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() {
        Reservation newReservation = new Reservation();
        newReservation.setFirst_name("Jane");
        newReservation.setLast_name("Doe");
        newReservation.setPhone("0987654321");
        newReservation.setEmail("jane.doe@gmail.com");
        newReservation.setPeople(2);
        newReservation.setDate("2024-12-26");
        newReservation.setTime("19:00");

        int status = ReservationDAO.save(newReservation);
        assertEquals(1, status);

        // Delete data
        try (Connection conn = new DBConnection().setConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM restoku_db.reservations WHERE first_name = ? AND last_name = ?")) {
            ps.setString(1, newReservation.getFirst_name());
            ps.setString(2, newReservation.getLast_name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetAllRecords() {
        List<Reservation> reservations = ReservationDAO.getAllRecords();
        assertNotNull(reservations);
        assertTrue(reservations.size() > 0);
    }

    @Test
    public void testUpdate() {
        List<Reservation> reservations = ReservationDAO.getAllRecords();
        Reservation lastInsertedReservation = reservations.get(reservations.size() - 1);

        lastInsertedReservation.setFirst_name("UpdatedName");
        int status = ReservationDAO.update(lastInsertedReservation);
        assertEquals(1, status);

        // Verify that the reservation was updated successfully
        Reservation updatedReservation = ReservationDAO.getRecordById(lastInsertedReservation.getId_reservation());
        assertNotNull(updatedReservation);
        assertEquals("UpdatedName", updatedReservation.getFirst_name());
    }

    @Test
    public void testMoveToDone() {
        List<Reservation> reservations = ReservationDAO.getAllRecords();
        Reservation lastInsertedReservation = reservations.get(reservations.size() - 1);

        int status = ReservationDAO.moveToDone(lastInsertedReservation.getId_reservation());
        assertEquals(1, status);

        // Verify that the reservation is no longer in the reservations table
        Reservation movedReservation = ReservationDAO.getRecordById(lastInsertedReservation.getId_reservation());
        assertNull(movedReservation);


    }
    
    @Test
public void testSaveWithNullReservation() {
    // Attempt to save a null reservation object
    int status = ReservationDAO.save(null);
    assertEquals(0, status); // Expected: Save should fail, return status 0
}

@Test
public void testSaveWithInvalidEmail() {
    // Attempt to save a reservation with an invalid email format
    Reservation invalidEmailReservation = new Reservation();
    invalidEmailReservation.setFirst_name("Invalid");
    invalidEmailReservation.setLast_name("Email");
    invalidEmailReservation.setPhone("1234567890");
    invalidEmailReservation.setEmail("invalid-email"); // Invalid email format
    invalidEmailReservation.setPeople(3);
    invalidEmailReservation.setDate("2024-12-25");
    invalidEmailReservation.setTime("18:00");

    int status = ReservationDAO.save(invalidEmailReservation);
    assertEquals(0, status); // Expected: Save should fail, return status 0
}

@Test
public void testGetRecordByInvalidId() {
    // Attempt to retrieve a reservation with an invalid ID
    Reservation reservation = ReservationDAO.getRecordById(-1); // Assuming -1 is an invalid ID
    assertNull(reservation); // Expected: Should return null, as ID is invalid
}

@Test
public void testUpdateNonExistentReservation() {
    // Attempt to update a reservation that doesn't exist in the database
    Reservation nonExistentReservation = new Reservation();
    nonExistentReservation.setId_reservation(-1); // Assuming -1 is an invalid ID
    nonExistentReservation.setFirst_name("Ghost");

    int status = ReservationDAO.update(nonExistentReservation);
    assertEquals(0, status); // Expected: Update should fail, return status 0
}

@Test
public void testMoveToDoneNonExistentReservation() {
    // Attempt to move a non-existent reservation to "done"
    int status = ReservationDAO.moveToDone(-1); // Assuming -1 is an invalid ID
    assertEquals(0, status); // Expected: Move should fail, return status 0
}

@Test
public void testSaveWithInvalidPeopleCount() {
    // Attempt to save a reservation with an invalid people count
    Reservation invalidPeopleReservation = new Reservation();
    invalidPeopleReservation.setFirst_name("InvalidPeople");
    invalidPeopleReservation.setLast_name("Count");
    invalidPeopleReservation.setPhone("1234567890");
    invalidPeopleReservation.setEmail("invalid@count.com");
    invalidPeopleReservation.setPeople(-5); // Invalid people count
    invalidPeopleReservation.setDate("2024-12-25");
    invalidPeopleReservation.setTime("18:00");

    int status = ReservationDAO.save(invalidPeopleReservation);
    assertEquals(0, status); // Expected: Save should fail, return status 0
}

@Test
public void testGetAllRecordsWhenDatabaseIsEmpty() {
    // Precondition: Ensure the reservations table is empty
    List<Reservation> reservations = ReservationDAO.getAllRecords();
    for (Reservation reservation : reservations) {
        ReservationDAO.delete(reservation); // Clear all reservations
    }

    // Fetch all records when the database is empty
    List<Reservation> emptyReservations = ReservationDAO.getAllRecords();
    assertTrue(emptyReservations.isEmpty()); // Expected: The list should be empty
}

@Test
public void testDeleteNonExistentReservation() {
    // Attempt to delete a reservation that doesn't exist in the database
    Reservation nonExistentReservation = new Reservation();
    nonExistentReservation.setId_reservation(-1); // Assuming -1 is an invalid ID

    int status = ReservationDAO.delete(nonExistentReservation);
    assertEquals(0, status); // Expected: Delete should fail, return status 0
}

}

