package com.example.Portal.Service;

import com.example.Portal.Model.PresentClients;
import java.sql.SQLException;
import java.util.List;

public interface PresentClientsService {
    // Method to save a new PresentClients record
    void savePresentClients(PresentClients presentClients);

    // Method to retrieve all PresentClients records
    List<PresentClients> getAllPresentClients();

    // Method to retrieve a PresentClients record by ID
    PresentClients getPresentClientsById(int id);

    PresentClients getPresentClientsById(String presentClientID);

    // Method to update an existing PresentClients record
    void updatePresentClients(PresentClients presentClients);

    // Method to delete a PresentClients record by ID
    void deletePresentClients(int id) throws SQLException;

    void deletePresentClients(String presentClientID);
}
