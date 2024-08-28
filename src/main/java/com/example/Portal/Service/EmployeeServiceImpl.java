package com.example.Portal.Service;

import com.example.Portal.Model.Employee;
import com.example.Portal.Service.DBConn;  // Ensure you import or create this class for database connections
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {


//    @Override
//    public String saveEmployee(Employee employee) {
//        if (employee == null) {
//            throw new IllegalArgumentException("Employee cannot be null");
//        }
//
//        Connection con = null;
//        PreparedStatement ps = null;
//
//        try {
//            con = DBConn.getMyConnection();
//            if (con == null) {
//                throw new SQLException("Failed to establish a database connection");
//            }
//
//            // Assuming EmployeeID is already set in the Employee object
//            String employeeID = employee.getEmployeeID();
//            if (employeeID.equals("0"))  {
//                throw new SQLException("EmployeeID cannot be zero or null");
//            }
//
//            // Insert into the employee table
//            String sql = "INSERT INTO employee (EmployeeID, FirstName, LastName, DOB, PersonalEmail, SSN, PhoneNumber, HomeAddress, MaritalStatus, DrivingLicense, LicenseExpDate, PassportNumber, PassportExpDate, VisaStatus, VisaExpDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1, employeeID);
//            ps.setString(2, checkString(employee.getFirstName(), "FirstName"));
//            ps.setString(3, checkString(employee.getLastName(), "LastName"));
//
//            // Convert String DOB to java.sql.Date
//            String dobString = employee.getDOB();
//            if (dobString != null) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date date = sdf.parse(dobString);
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                ps.setDate(4, sqlDate);
//            } else {
//                ps.setNull(4, java.sql.Types.DATE);
//            }
//
//            ps.setString(5, checkString(employee.getPersonalEmail(), "PersonalEmail"));
//            ps.setString(6, checkString(employee.getSSN(), "SSN"));
//            ps.setString(7, checkString(employee.getPhoneNumber(), "PhoneNumber"));
//            ps.setString(8, checkString(employee.getHomeAddress(), "HomeAddress"));
//            ps.setString(9, checkString(employee.getMaritalStatus(), "MaritalStatus"));
//            ps.setString(10, checkString(employee.getDrivingLicenseNumber(), "DrivingLicense"));
//            ps.setString(11, checkString(employee.getDrivingLicenseExpiryDate(), "LicenseExpDate"));
//            ps.setString(12, checkString(employee.getPassportNumber(), "PassportNumber"));
//            ps.setString(13, checkString(employee.getPassportExpiryDate(), "PassportExpDate"));
//            ps.setString(14, checkString(employee.getVisaStatus(), "VisaStatus"));
//            ps.setString(15, checkString(employee.getVisaExpiryDate(), "VisaExpDate"));
//
//            ps.executeUpdate();
//
//        } catch (SQLException | ParseException e) {
//            System.out.println("Exception in saveEmployee method: " + e);
//        } finally {
//            // Close resources in the finally block to ensure they are closed even if an exception occurs
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                System.out.println("Exception closing resources: " + e);
//            }
//        }
//        return null;
//    }

//    private String checkString(String value, String fieldName) {
//        if (value == null || value.isEmpty()) {
//            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
//        }
//        return value;
//    }



    @Override
    public String saveEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConn.getMyConnection();
            if (con == null) {
                throw new SQLException("Failed to establish a database connection");
            }

            // Generate EmployeeID - assuming a prefix "EMP" and a unique identifier
           // String employeeID = "EMP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            // Generate EmployeeID
           String employeeId = generateEmployeeID(con);

            // Hardcode the Company field
            String company = "Siritek";

            String sql = "INSERT INTO employee (EmployeeID, FirstName, LastName, DOB, PersonalEmail, SSN, PhoneNumber, HomeAddress, MaritalStatus, DrivingLicense, LicenseExpDate, PassportNumber, PassportExpDate, VisaStatus, VisaExpDate, Company) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            // Set the auto-generated EmployeeID
            ps.setString(1, employeeId);
            ps.setString(2, checkString(employee.getFirstName(), "FirstName"));
            ps.setString(3, checkString(employee.getLastName(), "LastName"));

            // Convert String DOB to java.sql.Date
            String dobString = employee.getDob();
            if (dobString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(dobString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(4, sqlDate);
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, checkString(employee.getPersonalEmail(), "PersonalEmail"));
            ps.setString(6, checkString(employee.getSsn(), "SSN"));
            ps.setString(7, checkString(employee.getPhoneNumber(), "PhoneNumber"));
            ps.setString(8, checkString(employee.getHomeAddress(), "HomeAddress"));
            ps.setString(9, checkString(employee.getMaritalStatus(), "MaritalStatus"));
            ps.setString(10, checkString(employee.getDrivingLicenseNumber(), "DrivingLicense"));

            // Convert DrivingLicenseExpiryDate to java.sql.Date
            String drivingLicenseExpiryDateString = employee.getDrivingLicenseExpiryDate();
            if (drivingLicenseExpiryDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(drivingLicenseExpiryDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(11, sqlDate);
            } else {
                ps.setNull(11, java.sql.Types.DATE);
            }

            ps.setString(12, checkString(employee.getPassportNumber(), "PassportNumber"));

            // Convert PassportExpiryDate to java.sql.Date
            String passportExpiryDateString = employee.getPassportExpiryDate();
            if (passportExpiryDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(passportExpiryDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(13, sqlDate);
            } else {
                ps.setNull(13, java.sql.Types.DATE);
            }

            ps.setString(14, checkString(employee.getVisaStatus(), "VisaStatus"));

            // Convert VisaExpiryDate to java.sql.Date
            String visaExpiryDateString = employee.getVisaExpiryDate();
            if (visaExpiryDateString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(visaExpiryDateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(15, sqlDate);
            } else {
                ps.setNull(15, java.sql.Types.DATE);
            }

            ps.setString(16, company); // Hardcoded company

            ps.executeUpdate();

            return employeeId; // Return the generated EmployeeID

        } catch (SQLException | ParseException e) {
            System.out.println("Exception in saveEmployee method: " + e);
        } finally {
            // Close resources in the finally block to ensure they are closed even if an exception occurs
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Exception closing resources: " + e);
            }
        }
        return null;
    }


    // Method to generate EmployeeID
    private String generateEmployeeID(Connection con) throws SQLException {
        String prefix = "SIRIN";
        String query = "SELECT COUNT(*) FROM employee";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int count = rs.getInt(1) + 1;
                return prefix + String.format("%03d", count); // Generates EmployeeID like EMP001, EMP002, etc.
            }
        }
        return prefix + "001"; // Default EmployeeID if table is empty
    }

//    @Override
//    public String saveEmployee(Employee employee) {
//        if (employee == null) {
//            throw new IllegalArgumentException("Employee cannot be null");
//        }
//
//        Connection con = null;
//        PreparedStatement ps = null;
//
//        try {
//            con = DBConn.getMyConnection();
//            if (con == null) {
//                throw new SQLException("Failed to establish a database connection");
//            }
//
//            String sql = "INSERT INTO employee ( FirstName, LastName, DOB, PersonalEmail, SSN, PhoneNumber, HomeAddress, MaritalStatus, DrivingLicense, LicenseExpDate, PassportNumber, PassportExpDate, VisaStatus, VisaExpDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1, checkString(employee.getEmployeeID(), "EmployeeID"));
//            ps.setString(1, checkString(employee.getFirstName(), "FirstName"));
//            ps.setString(2, checkString(employee.getLastName(), "LastName"));
//
//            // Convert String DOB to java.sql.Date
//            String dobString = employee.getDOB();
//            if (dobString != null) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date date = sdf.parse(dobString);
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                ps.setDate(3, sqlDate);
//            } else {
//                ps.setNull(3, java.sql.Types.DATE);
//            }
//
//            ps.setString(4, checkString(employee.getPersonalEmail(), "PersonalEmail"));
//            ps.setString(5, checkString(employee.getSSN(), "SSN"));
//            ps.setString(6, checkString(employee.getPhoneNumber(), "PhoneNumber"));
//            ps.setString(7, checkString(employee.getHomeAddress(), "HomeAddress"));
//            ps.setString(8, checkString(employee.getMaritalStatus(), "MaritalStatus"));
//            ps.setString(10, checkString(employee.getCompany(), "Company"));
//            ps.setString(9,checkString(employee.getDrivingLicenseNumber(), "DrivingLicense"));
//            ps.setString(10,checkString(employee.getDrivingLicenseExpiryDate(), "LicenseExpDate"));
//            ps.setString(11,checkString(employee.getPassportNumber(), "PassportNumber"));
//            ps.setString(12,checkString(employee.getPassportExpiryDate(), "PassportExpDate"));
//            ps.setString(13,checkString(employee.getVisaStatus(), "VisaStatus"));
//            ps.setString(14,checkString(employee.getVisaExpiryDate(), "VisaExpDate"));
//
//
//            ps.executeUpdate();
//
//        } catch (SQLException | ParseException e) {
//            System.out.println("Exception in saveEmployee method: " + e);
//        } finally {
//            // Close resources in the finally block to ensure they are closed even if an exception occurs
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                System.out.println("Exception closing resources: " + e);
//            }
//        }
//        return null;
//    }

    @Override
    public List<Employee> getAllemployee() {
        List<Employee> employees = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConn.getMyConnection();
            if (con == null) {
                throw new SQLException("Failed to establish a database connection");
            }

            String sql = "SELECT EmployeeID, FirstName, LastName, DOB, PersonalEmail, SSN, PhoneNumber, HomeAddress, MaritalStatus, Company FROM employee";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
              //  employee.setEmployeeID(rs.getString("EmployeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setDob(rs.getDate("DOB").toString());
                employee.setPersonalEmail(rs.getString("PersonalEmail"));
                employee.setSsn(rs.getString("SSN"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setHomeAddress(rs.getString("HomeAddress"));
                employee.setMaritalStatus(rs.getString("MaritalStatus"));
               // employee.setCompany(rs.getString("Company"));


                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllemployee method: " + e);
        } finally {
            // Close resources in the finally block to ensure they are closed even if an exception occurs
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Exception closing resources: " + e);
            }
        }
        return employees;
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
