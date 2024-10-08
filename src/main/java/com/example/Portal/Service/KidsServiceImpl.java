package com.example.Portal.Service;

import com.example.Portal.Model.Kids;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class KidsServiceImpl implements KidsService {

    @Override
    public  List<Kids> saveKids(List<Kids> kids) throws SQLException {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "INSERT INTO kids (FirstName, LastName, DOB, SSN) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                for(Kids kid : kids) {

                    System.out.println("Processing kid: " + kid.getFirstName() + " " + kid.getLastName());

                    // Set the values for the parameters
                    ps.setString(1, kid.getFirstName());
                ps.setString(2, kid.getLastName());


                String dobString = kid.getDateOfBirth();
                if (dobString != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = sdf.parse(dobString);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    ps.setDate(3, sqlDate);
                } else {
                    ps.setNull(3, java.sql.Types.DATE);
                }
                ps.setString(4, kid.getSsn());

                    // Add this kid's data to the batch
                    ps.addBatch();
                }

                // Execute all batched statements
                int[] updateCounts = ps.executeBatch();
                System.out.println("Batch execution completed. Rows affected: " + updateCounts.length);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println("Exception in saveKids: " + e);
            throw e; // Propagate the exception if needed
        }
        return kids; // Return the saved kid object
    }


   /* ------------------------------------*/
    @Override
    public List<Kids> getAllKids() {
        List<Kids> kids = new ArrayList<>();
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM kids";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Kids kid = new Kids();
                    kid.setFirstName(rs.getString("FirstName"));
                    kid.setLastName(rs.getString("LastName"));
                    kid.setDateOfBirth(rs.getString("DOB"));
                    kid.setSsn(rs.getString("SSN"));
                    kids.add(kid);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllKids: " + e);
        }
        return kids;
    }

    @Override
    public Kids getKidById(Long kidId) {
        Kids kid = null;
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM kids WHERE KidID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, kidId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        kid = new Kids();
                        kid.setFirstName(rs.getString("FirstName"));
                        kid.setLastName(rs.getString("LastName"));
                        //kid.(rs.getString("DOB"));
                        //kid.setSSN(rs.getString("SSN"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception in getKidById: " + e);
        }
        return kid;
    }

    @Override
    public Kids updateKid(Kids kid) throws SQLException {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "UPDATE kids SET FirstName = ?, LastName = ?, DOB = ?, SSN = ? WHERE KidID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, kid.getFirstName());
                ps.setString(2, kid.getLastName());
              //  ps.setString(3, kid.getDOB());
              //  ps.setString(4, kid.getSSN());
               // ps.setLong(5, kid.getKidId()); // Assuming KidID is part of Kids model

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in updateKid: " + e);
            throw e; // Propagate the exception if needed
        }
        return kid; // Return the updated kid object
    }

    @Override
    public void deleteKid(Long kidId) throws SQLException {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "DELETE FROM kids WHERE KidID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, kidId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in deleteKid: " + e);
            throw e; // Propagate the exception if needed
        }
    }
}
