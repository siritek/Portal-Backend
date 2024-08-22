//
//package com.example.Portal.Service;
//
//import com.example.Portal.Model.User;
//import com.example.Portal.Service.DBConn; // Assuming this is where DBConn is located
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserServiceImpl(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void saveUser(User user) {
//        try (Connection con = DBConn.getMyConnection()) {
//            String query = "INSERT INTO users (FirstName, LastName, Company, Username, Password, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setString(1, user.getFirstName());
//                ps.setString(2, user.getLastName());
//                ps.setString(3, user.getCompany());
//                ps.setString(4, user.getUsername());
//                ps.setString(5, passwordEncoder.encode(user.getPassword())); // Hash the password
//                ps.setString(6, user.getEmail());
//                ps.setString(7, user.getRole());
//
//                ps.executeUpdate();
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception in saveUser: " + e);
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        try (Connection con = DBConn.getMyConnection()) {
//            String query = "SELECT * FROM users";
//            try (PreparedStatement ps = con.prepareStatement(query);
//                 ResultSet rs = ps.executeQuery()) {
//
//                while (rs.next()) {
//                    User user = new User();
//                    user.setFirstName(rs.getString("FirstName"));
//                    user.setLastName(rs.getString("LastName"));
//                    user.setCompany(rs.getString("Company"));
//                    user.setUsername(rs.getString("Username"));
//                    user.setPassword(rs.getString("Password")); // Raw password
//                    user.setEmail(rs.getString("Email"));
//                    user.setRole(rs.getString("Role"));
//                    users.add(user);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception in getAllUsers: " + e);
//        }
//        return users;
//    }
//
//    @Override
//    public User getUserById(Long userId) {
//        User user = null;
//        try (Connection con = DBConn.getMyConnection()) {
//            String query = "SELECT * FROM users WHERE UserID = ?";
//            try (PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setLong(1, userId);
//                try (ResultSet rs = ps.executeQuery()) {
//                    if (rs.next()) {
//                        user = new User();
//                        user.setFirstName(rs.getString("FirstName"));
//                        user.setLastName(rs.getString("LastName"));
//                        user.setCompany(rs.getString("Company"));
//                        user.setUsername(rs.getString("Username"));
//                        user.setPassword(rs.getString("Password")); // Raw password
//                        user.setEmail(rs.getString("Email"));
//                        user.setRole(rs.getString("Role"));
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception in getUserById: " + e);
//        }
//        return user;
//    }
//
//    @Override
//    public void updateUser(User user) {
//        try (Connection con = DBConn.getMyConnection()) {
//            String query = "UPDATE users SET FirstName = ?, LastName = ?, Company = ?, Username = ?, Password = ?, Email = ?, Role = ? WHERE UserID = ?";
//            try (PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setString(1, user.getFirstName());
//                ps.setString(2, user.getLastName());
//                ps.setString(3, user.getCompany());
//                ps.setString(4, user.getUsername());
//                ps.setString(5, passwordEncoder.encode(user.getPassword())); // Hash the password
//                ps.setString(6, user.getEmail());
//                ps.setString(7, user.getRole());
//
//
//                ps.executeUpdate();
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception in updateUser: " + e);
//        }
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        try (Connection con = DBConn.getMyConnection()) {
//            String query = "DELETE FROM users WHERE UserID = ?";
//            try (PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setLong(1, userId);
//                ps.executeUpdate();
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception in deleteUser: " + e);
//        }
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        User user = null;
//        try (Connection con = DBConn.getMyConnection()) {
//            String query = "SELECT * FROM users WHERE Username = ?";
//            try (PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setString(1, username);
//                try (ResultSet rs = ps.executeQuery()) {
//                    if (rs.next()) {
//                        user = new User();
//                        user.setFirstName(rs.getString("FirstName"));
//                        user.setLastName(rs.getString("LastName"));
//                        user.setCompany(rs.getString("Company"));
//                        user.setUsername(rs.getString("Username"));
//                        user.setPassword(rs.getString("Password")); // Raw password
//                        user.setEmail(rs.getString("Email"));
//                        user.setRole(rs.getString("Role"));
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception in findByUsername: " + e);
//        }
//        return user;
//    }
//
//    @Override
//    public boolean validateUserCredentials(String username, String rawPassword) {
//        User user = findByUsername(username);
//        if (user != null) {
//            return passwordEncoder.matches(rawPassword, user.getPassword());
//        }
//        return false;
//    }
//}

package com.example.Portal.Service;

import com.example.Portal.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "INSERT INTO users (FirstName, LastName, Company, Username, Password, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getCompany());
                ps.setString(4, user.getUsername());
                ps.setString(5, passwordEncoder.encode(user.getPassword())); // Hash the password
                ps.setString(6, user.getEmail());
                ps.setString(7, user.getRole());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Exception in saveUser: ", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM users";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    User user = new User();
                    user.setFirstName(rs.getString("FirstName"));
                    user.setLastName(rs.getString("LastName"));
                    user.setCompany(rs.getString("Company"));
                    user.setUsername(rs.getString("Username"));
                    user.setPassword(rs.getString("Password")); // Raw password
                    user.setEmail(rs.getString("Email"));
                    user.setRole(rs.getString("Role"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in getAllUsers: ", e);
        }
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        User user = null;
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM users WHERE UserID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setFirstName(rs.getString("FirstName"));
                        user.setLastName(rs.getString("LastName"));
                        user.setCompany(rs.getString("Company"));
                        user.setUsername(rs.getString("Username"));
                        user.setPassword(rs.getString("Password")); // Raw password
                        user.setEmail(rs.getString("Email"));
                        user.setRole(rs.getString("Role"));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in getUserById: ", e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Connection con = DBConn.getMyConnection()) {
            String query = "UPDATE users SET FirstName = ?, LastName = ?, Company = ?, Username = ?, Password = ?, Email = ?, Role = ? WHERE UserID = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getCompany());
                ps.setString(4, user.getUsername());
                ps.setString(5, passwordEncoder.encode(user.getPassword())); // Hash the password
                ps.setString(6, user.getEmail());
                ps.setString(7, user.getRole());


                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Exception in updateUser: ", e);
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
            logger.error("Exception in deleteUser: ", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try (Connection con = DBConn.getMyConnection()) {
            String query = "SELECT * FROM users WHERE Username = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setFirstName(rs.getString("FirstName"));
                        user.setLastName(rs.getString("LastName"));
                        user.setCompany(rs.getString("Company"));
                        user.setUsername(rs.getString("Username"));
                        user.setPassword(rs.getString("Password")); // Raw password
                        user.setEmail(rs.getString("Email"));
                        user.setRole(rs.getString("Role"));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in findByUsername: ", e);
        }
        return user;
    }

    @Override
    public boolean validateUserCredentials(String username, String rawPassword) {
        User user = findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }
}
