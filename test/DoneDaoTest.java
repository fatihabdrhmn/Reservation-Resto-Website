/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.DoneDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Database.DBConnection;
import Model.Done;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author Fatih
 */
public class DoneDaoTest {
    
     private Connection conn;
    private PreparedStatement ps;

    @Before
    public void setUp() {
        conn = new DBConnection().setConnection();
    }
    
    @After
    public void tearDown() {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error during closing resources: " + e.getMessage());
        }
    }


     @Test
    public void testGetAllRecordsdone() {
        List<Done> doneList = DoneDAO.getAllRecordsdone();
        assertNotNull("The done list should not be null", doneList);
        System.out.println("Done list fetched successfully with " + doneList.size() + " records.");
    }}
