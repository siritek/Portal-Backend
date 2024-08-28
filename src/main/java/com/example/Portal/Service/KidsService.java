package com.example.Portal.Service;

import com.example.Portal.Model.Kids;

import java.sql.SQLException;
import java.util.List;

public interface KidsService {

    /**
     * Save a new kid or update an existing kid.
     *
     * @param kids the kid object to be saved
     * @return the saved kid object
     * @throws SQLException if there is an error with the database operation
     */
    List<Kids> saveKids(List<Kids> kids) throws SQLException;

    /**
     * Retrieve all kids from the database.
     *
     * @return a list of all kids
     */
    List<Kids> getAllKids();

    /**
     * Retrieve a kid by its ID.
     *
     * @param kidId the ID of the kid to be retrieved
     * @return the kid object if found, null otherwise
     */
    Kids getKidById(Long kidId);

    /**
     * Update an existing kid.
     *
     * @param kid the kid object with updated information
     * @return the updated kid object
     * @throws SQLException if there is an error with the database operation
     */
    Kids updateKid(Kids kid) throws SQLException;

    /**
     * Delete a kid by its ID.
     *
     * @param kidId the ID of the kid to be deleted
     * @throws SQLException if there is an error with the database operation
     */
    void deleteKid(Long kidId) throws SQLException;
}
