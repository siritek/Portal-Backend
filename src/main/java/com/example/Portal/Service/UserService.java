package com.example.Portal.Service;


import com.example.Portal.Model.User;

import java.util.List;

public interface UserService {

    // Method to save a new user
    public void saveUser(User user);

    // Method to retrieve all users
    public List<User> getAllUsers();

    // Method to retrieve a user by ID
    public User getUserById(Long userId);

    // Method to update user information
    public void updateUser(User user);

    // Method to delete a user by ID
    public void deleteUser(Long userId);
}
