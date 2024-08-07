//package com.example.Portal.Model;
//
//
//public class Employee {
//
//    private static int counter = 0;
//    private String EmployeeID;
//    private String  FirstName;
//    private String LastName;
//    private String  DOB;
//    private String PersonalEmail;
//    private String SSN;
//    private String PhoneNumber;
//    private String HomeAddress;
//    private String MaritalStatus;
//    private String Company;
//
//    public Employee() {
//        this.EmployeeID = generateEmployeeID();
//    }
//
//    private synchronized String generateEmployeeID() {
//        counter++;
//        return "sirin" + counter;
//    }
//
//    public String getEmployeeID() {
//        return EmployeeID;
//    }
//
//    public String getFirstName() {
//        return FirstName;
//    }
//
//    public void setFirstName(String firstName) {
//        FirstName = firstName;
//    }
//
//    public String getLastName() {
//        return LastName;
//    }
//
//    public void setLastName(String lastName) {
//        LastName = lastName;
//    }
//
//    public String getDOB() {
//        return DOB;
//    }
//
//    public void setDOB(String DOB) {
//        this.DOB = DOB;
//    }
//
//    public String getPersonalEmail() {
//        return PersonalEmail;
//    }
//
//    public void setPersonalEmail(String personalEmail) {
//        PersonalEmail = personalEmail;
//    }
//
//    public String getSSN() {
//        return SSN;
//    }
//
//    public void setSSN(String SSN) {
//        this.SSN = SSN;
//    }
//
//    public String getPhoneNumber() {
//        return PhoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        PhoneNumber = phoneNumber;
//    }
//
//    public String getHomeAddress() {
//        return HomeAddress;
//    }
//
//    public void setHomeAddress(String homeAddress) {
//        HomeAddress = homeAddress;
//    }
//
//    public String getMaritalStatus() {
//        return MaritalStatus;
//    }
//
//    public void setMaritalStatus(String maritalStatus) {
//        MaritalStatus = maritalStatus;
//    }
//
//    public String getCompany() {
//        return Company;
//    }
//
//    public void setCompany(String company) {
//        Company = company;
//    }
//
//
//}

package com.example.Portal.Model;

public class Employee {
    private String EmployeeID;
    private String FirstName;
    private String LastName;
    private String DOB;
    private String PersonalEmail;
    private String SSN;
    private String PhoneNumber;
    private String HomeAddress;
    private String MaritalStatus;
    private String UserID;
    private String Company;

    // Getters and Setters

    public String getEmployeeID() { return EmployeeID; }
    public void setEmployeeID(String employeeID) { EmployeeID = employeeID; }

    public String getFirstName() { return FirstName; }
    public void setFirstName(String firstName) { FirstName = firstName; }

    public String getLastName() { return LastName; }
    public void setLastName(String lastName) { LastName = lastName; }

    public String getDOB() { return DOB; }
    public void setDOB(String DOB) { this.DOB = DOB; }

    public String getPersonalEmail() { return PersonalEmail; }
    public void setPersonalEmail(String personalEmail) { PersonalEmail = personalEmail; }

    public String getSSN() { return SSN; }
    public void setSSN(String SSN) { this.SSN = SSN; }

    public String getPhoneNumber() { return PhoneNumber; }
    public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }

    public String getHomeAddress() { return HomeAddress; }
    public void setHomeAddress(String homeAddress) { HomeAddress = homeAddress; }

    public String getMaritalStatus() { return MaritalStatus; }
    public void setMaritalStatus(String maritalStatus) { MaritalStatus = maritalStatus; }

    public String getUserID() { return UserID; }
    public void setUserID(String userID) { UserID = userID; }

    public String getCompany() { return Company; }
    public void setCompany(String company) { Company = company; }
}