package com.example.Portal.Service;

import com.example.Portal.Model.PresentClients;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PresentClientsServiceImpl implements PresentClientsService {

    @Override
    public void savePresentClients(PresentClients presentClients) {
        String query = "INSERT INTO presentClients (PresentClientID, EmployeeID, PresentVendor, PresentClient, PrimeVendor, StartDate, EndDate, ClientEmail, ClientPhoneNumber, ClientAddress) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, checkString(presentClients.getPresentClientID(), "PresentClientID"));
            ps.setString(2, checkString(presentClients.getEmployeeID(), "EmployeeID"));
            ps.setString(3, checkString(presentClients.getPresentVendor(), "PresentVendor"));
            ps.setString(4, checkString(presentClients.getPresentClient(), "PresentClient"));
            ps.setString(5, checkString(presentClients.getPrimeVendor(), "PrimeVendor"));

            // Convert and set StartDate
            String startDateString = presentClients.getStartDate();
            if (startDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(startDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(6, sqlDate);
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            // Convert and set EndDate
            String endDateString = presentClients.getEndDate();
            if (endDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(endDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(7, sqlDate);
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }

            ps.setString(8, checkString(presentClients.getClientEmail(), "ClientEmail"));
            ps.setString(9, checkString(presentClients.getClientPhoneNumber(), "ClientPhoneNumber"));
            ps.setString(10, checkString(presentClients.getClientAddress(), "ClientAddress"));

            ps.executeUpdate();

        } catch (SQLException | ParseException e) {
            System.out.println("Exception in savePresentClients: " + e);
        }
    }

    @Override
    public List<PresentClients> getAllPresentClients() {
        List<PresentClients> presentClientsList = new ArrayList<>();
        String query = "SELECT * FROM presentClients";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PresentClients presentClient = new PresentClients();
                presentClient.setPresentClientID(rs.getString("PresentClientID"));
                presentClient.setEmployeeID(rs.getString("EmployeeID"));
                presentClient.setPresentVendor(rs.getString("PresentVendor"));
                presentClient.setPresentClient(rs.getString("PresentClient"));
                presentClient.setPrimeVendor(rs.getString("PrimeVendor"));
                presentClient.setStartDate(rs.getString("StartDate"));
                presentClient.setEndDate(rs.getString("EndDate"));
                presentClient.setClientEmail(rs.getString("ClientEmail"));
                presentClient.setClientPhoneNumber(rs.getString("ClientPhoneNumber"));
                presentClient.setClientAddress(rs.getString("ClientAddress"));

                presentClientsList.add(presentClient);
            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllPresentClients: " + e);
        }

        return presentClientsList;
    }

    @Override
    public PresentClients getPresentClientsById(int id) {
        return null;
    }

    @Override
    public PresentClients getPresentClientsById(String presentClientID) {
        PresentClients presentClients = null;
        String query = "SELECT * FROM presentClients WHERE PresentClientID = ?";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, presentClientID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    presentClients = new PresentClients();
                    presentClients.setPresentClientID(rs.getString("PresentClientID"));
                    presentClients.setEmployeeID(rs.getString("EmployeeID"));
                    presentClients.setPresentVendor(rs.getString("PresentVendor"));
                    presentClients.setPresentClient(rs.getString("PresentClient"));
                    presentClients.setPrimeVendor(rs.getString("PrimeVendor"));
                    presentClients.setStartDate(rs.getString("StartDate"));
                    presentClients.setEndDate(rs.getString("EndDate"));
                    presentClients.setClientEmail(rs.getString("ClientEmail"));
                    presentClients.setClientPhoneNumber(rs.getString("ClientPhoneNumber"));
                    presentClients.setClientAddress(rs.getString("ClientAddress"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception in getPresentClientsById: " + e);
        }

        return presentClients;
    }

    @Override
    public void updatePresentClients(PresentClients presentClients) {
        String query = "UPDATE presentClients SET EmployeeID = ?, PresentVendor = ?, PresentClient = ?, PrimeVendor = ?, StartDate = ?, EndDate = ?, ClientEmail = ?, ClientPhoneNumber = ?, ClientAddress = ? WHERE PresentClientID = ?";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, checkString(presentClients.getEmployeeID(), "EmployeeID"));
            ps.setString(2, checkString(presentClients.getPresentVendor(), "PresentVendor"));
            ps.setString(3, checkString(presentClients.getPresentClient(), "PresentClient"));
            ps.setString(4, checkString(presentClients.getPrimeVendor(), "PrimeVendor"));

            // Convert and set StartDate
            String startDateString = presentClients.getStartDate();
            if (startDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(startDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(5, sqlDate);
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            // Convert and set EndDate
            String endDateString = presentClients.getEndDate();
            if (endDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(endDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(6, sqlDate);
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            ps.setString(7, checkString(presentClients.getClientEmail(), "ClientEmail"));
            ps.setString(8, checkString(presentClients.getClientPhoneNumber(), "ClientPhoneNumber"));
            ps.setString(9, checkString(presentClients.getClientAddress(), "ClientAddress"));
            ps.setString(10, checkString(presentClients.getPresentClientID(), "PresentClientID"));

            ps.executeUpdate();

        } catch (SQLException | ParseException e) {
            System.out.println("Exception in updatePresentClients: " + e);
        }
    }

    @Override
    public void deletePresentClients(int id) throws SQLException {

    }

    @Override
    public void deletePresentClients(String presentClientID) {
        String query = "DELETE FROM presentClients WHERE PresentClientID = ?";

        try (Connection con = DBConn.getMyConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, presentClientID);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception in deletePresentClients: " + e);
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
