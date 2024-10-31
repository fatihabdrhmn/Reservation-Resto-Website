package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.DBConnection;
import Model.Reservation;
import Model.Done;

public class ReservationDAO {
    
    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql;
    
    // Other methods...

    public static int save(Reservation rs) {
        int status = 0;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("insert into restoku_db.reservations (first_name, last_name, phone, email, people, reservation_date, reservation_time) values(?,?,?,?,?,?,?)");
            ps.setString(1, rs.getFirst_name());
            ps.setString(2, rs.getLast_name());
            ps.setString(3, rs.getPhone());
            ps.setString(4, rs.getEmail());
            ps.setInt(5, rs.getPeople());
            ps.setString(6, rs.getDate());
            ps.setString(7, rs.getTime());
            status = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }
    
 
    
    public static List<Reservation> getAllRecords() {
        List<Reservation> list = new ArrayList<>();

        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("select * from restoku_db.reservations");
            ResultSet rsu;
            rsu = ps.executeQuery();
            while (rsu.next()) {
                Reservation r = new Reservation();
                r.setId_reservation(rsu.getInt("id_reservation"));
                r.setFirst_name(rsu.getString("first_name"));
                r.setLast_name(rsu.getString("last_name"));
                r.setPhone(rsu.getString("phone"));
                r.setEmail(rsu.getString("email"));
                r.setPeople(rsu.getInt("people"));
                r.setDate(rsu.getString("reservation_date"));
                r.setTime(rsu.getString("reservation_time"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static int update(Reservation reservation) {
    int status = 0;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = new DBConnection().setConnection();
        String sql = "UPDATE restoku_db.reservations SET first_name = ?, last_name = ?, phone = ?, email = ?, people = ?, reservation_date = ?, reservation_time = ? WHERE id_reservation = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, reservation.getFirst_name());
        ps.setString(2, reservation.getLast_name());
        ps.setString(3, reservation.getPhone());
        ps.setString(4, reservation.getEmail());
        ps.setInt(5, reservation.getPeople());
        ps.setString(6, reservation.getDate());
        ps.setString(7, reservation.getTime());
        ps.setInt(8, reservation.getId_reservation());

        status = ps.executeUpdate(); // Returns 1 if the update was successful
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return status;
}
    
    public static Reservation getRecordById(int id) {
        Reservation r = null;
        try {
            conn = new DBConnection().setConnection();
            ps = conn.prepareStatement("select * from restoku_db.reservations where id_reservation=?");
            ps.setInt(1, id);
            ResultSet rsu;
            rsu = ps.executeQuery();
            while (rsu.next()) {
                r = new Reservation();
                r.setId_reservation(rsu.getInt("id_reservation"));
                r.setFirst_name(rsu.getString("first_name"));
                r.setLast_name(rsu.getString("last_name"));
                r.setPhone(rsu.getString("phone"));
                r.setEmail(rsu.getString("email"));
                r.setPeople(rsu.getInt("people"));
                r.setDate(rsu.getString("reservation_date"));
                r.setTime(rsu.getString("reservation_time"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }
    
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
    
    public static int delete(Reservation reservation) {
    if (reservation == null || reservation.getId_reservation() <= 0) {
        return 0; // Invalid reservation ID, return failure status
    }

    int status = 0;
    try (Connection conn = new DBConnection().setConnection();
         PreparedStatement ps = conn.prepareStatement("DELETE FROM reservations WHERE id_reservation = ?")) {
        ps.setInt(1, reservation.getId_reservation());
        status = ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}

    
    public static List<Reservation> searchRecords(String keyword) {
        List<Reservation> list = new ArrayList<>();
        try {
            conn = new DBConnection().setConnection();
            sql = "SELECT * FROM restoku_db.reservations where first_name LIKE ? OR last_name LIKE ? OR phone LIKE ? OR email LIKE ?";
            ps = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId_reservation(rs.getInt("id_reservation"));
                r.setFirst_name(rs.getString("first_name"));
                r.setLast_name(rs.getString("last_name"));
                r.setPhone(rs.getString("phone"));
                r.setEmail(rs.getString("email"));
                r.setPeople(rs.getInt("people"));
                r.setDate(rs.getString("reservation_date"));
                r.setTime(rs.getString("reservation_time"));
                list.add(r);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
