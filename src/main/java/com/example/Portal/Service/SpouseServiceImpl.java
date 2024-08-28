package com.example.Portal.Service;

import com.example.Portal.Model.Spouse;
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
public class SpouseServiceImpl implements SpouseService {

    @Override
    public Spouse saveSpouse(Spouse spouse) throws SQLException {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "INSERT INTO spouse (FirstName, LastName, DOB, SSN) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, spouse.getSpouseFirstName());
                ps.setString(2, spouse.getSpouseLastName());

                String dobString = spouse.getSpouseDateOfBirth();
                if (dobString != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = sdf.parse(dobString);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    ps.setDate(3, sqlDate);
                } else {
                    ps.setNull(3, java.sql.Types.DATE);
                }
                ps.setString(4, spouse.getSpouseSSN());

                ps.executeUpdate();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println("Exception in saveSpouse: " + e);
            throw e; // Propagate the exception if needed
        }
        return spouse; // Return the saved spouse object
    }

    @Override
    public List<Spouse> getAllSpouses() {
        List<Spouse> spouses = new ArrayList<>();
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM spouses";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Spouse spouse = new Spouse();
                    spouse.setSpouseFirstName(rs.getString("FirstName"));
                    spouse.setSpouseLastName(rs.getString("LastName"));
                    spouses.add(spouse);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllSpouses: " + e);
        }
        return spouses;
    }

    @Override
    public Spouse getSpouseById(Long spouseId) {
        Spouse spouse = null;
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM spouses WHERE SpouseID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, spouseId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        spouse = new Spouse();
                        spouse.setSpouseFirstName(rs.getString("FirstName"));
                        spouse.setSpouseLastName(rs.getString("LastName"));

                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception in getSpouseById: " + e);
        }
        return spouse;
    }

    @Override
    public Spouse updateSpouse(Spouse spouse) throws SQLException {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "UPDATE spouses SET FirstName = ?, LastName = ?, DOB = ?, SSN = ? WHERE SpouseID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, spouse.getSpouseLastName());
                ps.setString(2, spouse.getSpouseFirstName());
//                ps.setString(3, spouse.getDOB());
//                ps.setString(4, spouse.getSSN());
              //  ps.setLong(5, spouse.getSpouseId()); // Assuming SpouseID is part of Spouse model

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in updateSpouse: " + e);
            throw e; // Propagate the exception if needed
        }
        return spouse; // Return the updated spouse object
    }

    @Override
    public void deleteSpouse(Long spouseId) throws SQLException {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "DELETE FROM spouses WHERE SpouseID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, spouseId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in deleteSpouse: " + e);
            throw e; // Propagate the exception if needed
        }
    }
}
