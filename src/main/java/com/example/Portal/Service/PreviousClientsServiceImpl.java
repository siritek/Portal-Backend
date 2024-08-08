package com.example.Portal.Service;

import com.example.Portal.Model.PreviousClients;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PreviousClientsServiceImpl implements PreviousClientsService {

    @Override
    public void savePreviousClients(PreviousClients previousClients) {
        String query = "INSERT INTO previousClients (PreviousClientID, EmployeeID, PreviousVendor, PreviousClient, StartDate, EndDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, checkString(previousClients.getPreviousClientID(), "PreviousClientID"));
            ps.setString(2, checkString(previousClients.getEmployeeID(), "EmployeeID"));
            ps.setString(3, checkString(previousClients.getPreviousVendor(), "PreviousVendor"));
            ps.setString(4, checkString(previousClients.getPreviousClient(), "PreviousClient"));

            // Convert and set StartDate
            String startDateString = previousClients.getStartDate();
            if (startDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(startDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(5, sqlDate);
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            // Convert and set EndDate
            String endDateString = previousClients.getEndDate();
            if (endDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(endDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(6, sqlDate);
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            ps.executeUpdate();

        } catch (SQLException | ParseException e) {
            System.out.println("Exception in savePreviousClients: " + e);
        }
    }

    @Override
    public List<PreviousClients> getAllPreviousClients() {
        List<PreviousClients> previousClientsList = new ArrayList<>();
        String query = "SELECT * FROM previousClients";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PreviousClients previousClient = new PreviousClients();
                previousClient.setPreviousClientID(rs.getString("PreviousClientID"));
                previousClient.setEmployeeID(rs.getString("EmployeeID"));
                previousClient.setPreviousVendor(rs.getString("PreviousVendor"));
                previousClient.setPreviousClient(rs.getString("PreviousClient"));
                previousClient.setStartDate(rs.getString("StartDate"));
                previousClient.setEndDate(rs.getString("EndDate"));

                previousClientsList.add(previousClient);
            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllPreviousClients: " + e);
        }

        return previousClientsList;
    }

    @Override
    public PreviousClients getPreviousClientsById(int id) {
        return null;
    }

    @Override
    public PreviousClients getPreviousClientsById(String previousClientID) {
        PreviousClients previousClients = null;
        String query = "SELECT * FROM previousClients WHERE PreviousClientID = ?";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, previousClientID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    previousClients = new PreviousClients();
                    previousClients.setPreviousClientID(rs.getString("PreviousClientID"));
                    previousClients.setEmployeeID(rs.getString("EmployeeID"));
                    previousClients.setPreviousVendor(rs.getString("PreviousVendor"));
                    previousClients.setPreviousClient(rs.getString("PreviousClient"));
                    previousClients.setStartDate(rs.getString("StartDate"));
                    previousClients.setEndDate(rs.getString("EndDate"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception in getPreviousClientsById: " + e);
        }

        return previousClients;
    }

    @Override
    public void updatePreviousClients(PreviousClients previousClients) {
        String query = "UPDATE previousClients SET EmployeeID = ?, PrimeVendor = ?, PreviousClient = ?, StartDate = ?, EndDate = ? WHERE PreviousClientID = ?";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, checkString(previousClients.getEmployeeID(), "EmployeeID"));
            ps.setString(2, checkString(previousClients.getPreviousVendor(), "PreviousVendor"));
            ps.setString(3, checkString(previousClients.getPreviousClient(), "PreviousClient"));

            // Convert and set StartDate
            String startDateString = previousClients.getStartDate();
            if (startDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(startDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(4, sqlDate);
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            // Convert and set EndDate
            String endDateString = previousClients.getEndDate();
            if (endDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(endDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(5, sqlDate);
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setString(6, checkString(previousClients.getPreviousClientID(), "PreviousClientID"));

            ps.executeUpdate();

        } catch (SQLException | ParseException e) {
            System.out.println("Exception in updatePreviousClients: " + e);
        }
    }

    @Override
    public void deletePreviousClients(int id) throws SQLException {
        // Implementation not required per the current structure
    }

    @Override
    public void deletePreviousClients(String previousClientID) {
        String query = "DELETE FROM previousClients WHERE PreviousClientID = ?";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, previousClientID);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception in deletePreviousClients: " + e);
        }
    }

    // Helper method to check for null values
    private String checkString(String value, String fieldName) {
        if (value == null) {
            System.out.println("Warning: " + fieldName + " is null");
            return "";
        }
        return value;
    }
}
