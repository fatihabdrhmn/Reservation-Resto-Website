package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.DBConnection;
import Model.Reservation;
import Model.Done;

public class DoneDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql;
        
    // Method to move reservation to done table
    public static int moveToDone(int id) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();

            // Fetch the reservation by ID
            ps = conn.prepareStatement("select * from restoku_db.reservations where id_reservation=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                Done done = new Done();
                done.setId_reservation(rs.getInt("id_reservation"));
                done.setFirst_name(rs.getString("first_name"));
                done.setLast_name(rs.getString("last_name"));
                done.setPhone(rs.getString("phone"));
                done.setEmail(rs.getString("email"));
                done.setPeople(rs.getInt("people"));
                done.setReservation_date(rs.getString("reservation_date"));
                done.setReservation_time(rs.getString("reservation_time"));
                
                // Insert the reservation into done table
                ps = conn.prepareStatement("insert into restoku_db.done (id_reservation, first_name, last_name, phone, email, people, reservation_date, reservation_time) values(?,?,?,?,?,?,?,?)");
                ps.setInt(1, done.getId_reservation());
                ps.setString(2, done.getFirst_name());
                ps.setString(3, done.getLast_name());
                ps.setString(4, done.getPhone());
                ps.setString(5, done.getEmail());
                ps.setInt(6, done.getPeople());
                ps.setString(7, done.getReservation_date());
                ps.setString(8, done.getReservation_time());
                ps.executeUpdate();
                
                // Delete the reservation from the reservations table
                ps = conn.prepareStatement("delete from restoku_db.reservations where id_reservation=?");
                ps.setInt(1, id);
                status = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }

    
        public static List<Done> getAllRecordsdone() {
        List<Done> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("select * from restoku_db.done");
            ResultSet rsu;
            rsu = ps.executeQuery();
            while (rsu.next()) {
                Done d = new Done();
                d.setId_reservation(rsu.getInt("id_reservation"));
                d.setFirst_name(rsu.getString("first_name"));
                d.setLast_name(rsu.getString("last_name"));
                d.setPhone(rsu.getString("phone"));
                d.setEmail(rsu.getString("email"));
                d.setPeople(rsu.getInt("people"));
                d.setReservation_date(rsu.getString("reservation_date"));
                d.setReservation_time(rsu.getString("reservation_time"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
        
        public static int delete(Done don) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("delete from restoku_db.done where id_reservation=?");
            ps.setInt(1, don.getId_reservation());
            status = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return status;
    }
}

