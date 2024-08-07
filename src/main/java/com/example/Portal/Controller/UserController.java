package com.example.Portal.Controller;

import com.example.Portal.Model.User;
import com.example.Portal.Service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody User user) {
        try {
            // Log the received JSON for debugging
            String json = new ObjectMapper().writeValueAsString(user);
            System.out.println("Received this JSON from client for User: " + json);

            // Save the user
            userService.saveUser(user);
            System.out.println("Connection reached UserController");

            // Return the user object and a success message
            return ResponseEntity.status(HttpStatus.CREATED).body(user);

        } catch (Exception e) {
            System.out.println("Exception in UserController: " + e);
            // Return a JSON error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding user\"}");
        }
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
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok("{\"message\": \"User updated successfully\"}");
        } catch (Exception e) {
            System.out.println("Exception in updateUser: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to update user\"}");
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("{\"message\": \"User deleted successfully\"}");
        } catch (Exception e) {
            System.out.println("Exception in deleteUser: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to delete user\"}");
        }
    }
}
