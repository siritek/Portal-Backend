package com.example.Portal.Controller;

import com.example.Portal.Model.User;
import com.example.Portal.Model.LoginRequest;
import com.example.Portal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})

public class UserController {

    @Autowired
    private UserService userService; // Inject UserService

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody User user) {
        try {
            System.out.println("Received this JSON from client for User: " + user);

            userService.saveUser(user);
            System.out.println("User added successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("User added successfully"));

        } catch (Exception e) {
            System.out.println("Exception in UserController: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error adding user"));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            System.out.println("Exception in getAllUsers: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
            }
        } catch (Exception e) {
            System.out.println("Exception in getUserById: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error fetching user"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok(new SuccessResponse("User updated successfully"));
        } catch (Exception e) {
            System.out.println("Exception in updateUser: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Failed to update user"));
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new SuccessResponse("User deleted successfully"));
        } catch (Exception e) {
            System.out.println("Exception in deleteUser: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Failed to delete user"));
        }
    }

    @PostMapping("/login")  // Ensure this matches your frontend request
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isValidUser = userService.validateUserCredentials(loginRequest.getUsername(), loginRequest.getPassword());

            if (isValidUser) {
                User user = userService.findByUsername(loginRequest.getUsername());
                return ResponseEntity.ok(new LoginResponse("Login successful", user));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid credentials"));
            }

        } catch (Exception e) {
            System.out.println("Exception in login: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error during login"));
        }
    }

    // Define response classes
    static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    static class LoginResponse {
        private String message;
        private User user;

        public LoginResponse(String message, User user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() {
            return message;
        }

        public User getUser() {
            return user;
        }
    }
}
