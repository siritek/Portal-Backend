package com.example.Portal.Service;

import com.example.Portal.Model.PreviousClients;
import java.sql.SQLException;
import java.util.List;

public interface PreviousClientsService {
    // Method to save a new PreviousClients record
    void savePreviousClients(PreviousClients previousClients);

    // Method to retrieve all PreviousClients records
    List<PreviousClients> getAllPreviousClients();

    // Method to retrieve a PreviousClients record by ID
    PreviousClients getPreviousClientsById(int id);

    // Method to retrieve a PreviousClients record by previousClientID
    PreviousClients getPreviousClientsById(String previousClientID);

    // Method to update an existing PreviousClients record
    void updatePreviousClients(PreviousClients previousClients);

    // Method to delete a PreviousClients record by ID
    void deletePreviousClients(int id) throws SQLException;

    // Method to delete a PreviousClients record by previousClientID
    void deletePreviousClients(String previousClientID);
}
