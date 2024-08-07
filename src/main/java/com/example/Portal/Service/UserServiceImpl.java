package com.example.Portal.Service; // Ensure you import or create this class for database connections


import com.example.Portal.Model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public void saveUser(User user) {
        try (Connection con = com.example.Portal.Service.DBConn.getMyConnection()) {
            String query = "INSERT INTO users (FirstName, LastName, Company, Username, Password, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getCompany());
                ps.setString(4, user.getUsername());
                ps.setString(5, user.getPassword());  // Ensure password is hashed
                ps.setString(6, user.getEmail());
                ps.setString(7, user.getRole());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in saveUser: " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = com.example.Portal.Service.DBConn.getMyConnection()) {
            String query = "SELECT * FROM users";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    User user = new User();
                    user.setUserID(rs.getLong("UserID"));
                    user.setFirstName(rs.getString("FirstName"));
                    user.setLastName(rs.getString("LastName"));
                    user.setCompany(rs.getString("Company"));
                    user.setUsername(rs.getString("Username"));
                    user.setPassword(rs.getString("Password"));  // Be cautious with raw passwords
                    user.setEmail(rs.getString("Email"));
                    user.setRole(rs.getString("Role"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllUsers: " + e);
        }
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        User user = null;
        try (Connection con = com.example.Portal.Service.DBConn.getMyConnection()) {
            String query = "SELECT * FROM users WHERE UserID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setUserID(rs.getLong("UserID"));
                        user.setFirstName(rs.getString("FirstName"));
                        user.setLastName(rs.getString("LastName"));
                        user.setCompany(rs.getString("Company"));
                        user.setUsername(rs.getString("Username"));
                        user.setPassword(rs.getString("Password"));  // Be cautious with raw passwords
                        user.setEmail(rs.getString("Email"));
                        user.setRole(rs.getString("Role"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception in getUserById: " + e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Connection con = com.example.Portal.Service.DBConn.getMyConnection()) {
            String query = "UPDATE users SET FirstName = ?, LastName = ?, Company = ?, Username = ?, Password = ?, Email = ?, Role = ? WHERE UserID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getCompany());
                ps.setString(4, user.getUsername());
                ps.setString(5, user.getPassword());  // Ensure password is hashed
                ps.setString(6, user.getEmail());
                ps.setString(7, user.getRole());
                ps.setLong(8, user.getUserID());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in updateUser: " + e);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "DELETE FROM users WHERE UserID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception in deleteUser: " + e);
        }
    }
}
