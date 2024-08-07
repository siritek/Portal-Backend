package com.example.Portal.Controller;


import com.example.Portal.Model.User;
import com.example.Portal.Service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserServiceImpl userService = new UserServiceImpl();
    @PostMapping("/add")
    public String add(@RequestBody User user) {
        try {
            // Log the received JSON for debugging
            String json = new ObjectMapper().writeValueAsString(user);
          System.out.println("Received this JSON from client for User: " + json);

            // Save the user
            userService.saveUser(user);
            System.out.println("Connection reached UserController");

        } catch (Exception e) {
            System.out.println("Exception in UserController: " + e);
        }
        return "New User info is added";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return "User updated successfully";
        } catch (Exception e) {
            System.out.println("Exception in updateUser: " + e);
            return "Failed to update user";
        }
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return "User deleted successfully";
        } catch (Exception e) {
            System.out.println("Exception in deleteUser: " + e);
            return "Failed to delete user";
        }
    }
}

