package com.example.Portal.Service;

import com.example.Portal.Model.Employee;
import com.example.Portal.Service.DBConn;  // Ensure you import or create this class for database connections

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeServiceImpl implements EmployeeService {

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

            String sql = "INSERT INTO employee (EmployeeID, FirstName, LastName, DOB, PersonalEmail, SSN, PhoneNumber, HomeAddress, MaritalStatus, Company) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, checkString(employee.getEmployeeID(), "EmployeeID"));
            ps.setString(2, checkString(employee.getFirstName(), "FirstName"));
            ps.setString(3, checkString(employee.getLastName(), "LastName"));

            // Convert String DOB to java.sql.Date
            String dobString = employee.getDOB();
            if (dobString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(dobString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(4, sqlDate);
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, checkString(employee.getPersonalEmail(), "PersonalEmail"));
            ps.setString(6, checkString(employee.getSSN(), "SSN"));
            ps.setString(7, checkString(employee.getPhoneNumber(), "PhoneNumber"));
            ps.setString(8, checkString(employee.getHomeAddress(), "HomeAddress"));
            ps.setString(9, checkString(employee.getMaritalStatus(), "MaritalStatus"));
            ps.setString(10, checkString(employee.getCompany(), "Company"));
            ps.setString(11,checkString(employee.getDrivingLicense(), "DrivingLicense"));
            ps.setString(12,checkString(employee.getDrivingLicenseExpiryDate(), "LicenseExpDate"));
            ps.setString(13,checkString(employee.getPassportNumber(), "PassportNumber"));
            ps.setString(14,checkString(employee.getPassportExpiryDate(), "PassportExpDate"));
            ps.setString(15,checkString(employee.getVisaStatus(), "VisaStatus"));
            ps.setString(16,checkString(employee.getVisaExpiryDate(), "VisaExpDate"));

            ps.executeUpdate();

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
                employee.setEmployeeID(rs.getString("EmployeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setDOB(rs.getDate("DOB").toString());
                employee.setPersonalEmail(rs.getString("PersonalEmail"));
                employee.setSSN(rs.getString("SSN"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setHomeAddress(rs.getString("HomeAddress"));
                employee.setMaritalStatus(rs.getString("MaritalStatus"));
                employee.setCompany(rs.getString("Company"));


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
