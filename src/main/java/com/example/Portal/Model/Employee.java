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
    private String DrivingLicenseNumber;
    private String DrivingLicenseExpiryDate;
    private String PassportNumber;
    private String PassportExpiryDate;
    private String VisaStatus;
    private String VisaExpiryDate;

    private String Kids;

    public String getKids() {
        return Kids;
    }

    public void setKids(String kids) {
        Kids = kids;
    }



    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPersonalEmail() {
        return PersonalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        PersonalEmail = personalEmail;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getDrivingLicenseNumber() {
        return DrivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        DrivingLicenseNumber = drivingLicenseNumber;
    }

    public String getDrivingLicenseExpiryDate() {
        return DrivingLicenseExpiryDate;
    }

    public void setDrivingLicenseExpiryDate(String drivingLicenseExpiryDate) {
        DrivingLicenseExpiryDate = drivingLicenseExpiryDate;
    }

    public String getPassportNumber() {
        return PassportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        PassportNumber = passportNumber;
    }

    public String getPassportExpiryDate() {
        return PassportExpiryDate;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        PassportExpiryDate = passportExpiryDate;
    }

    public String getVisaStatus() {
        return VisaStatus;
    }

    public void setVisaStatus(String visaStatus) {
        VisaStatus = visaStatus;
    }

    public String getVisaExpiryDate() {
        return VisaExpiryDate;
    }

    public void setVisaExpiryDate(String visaExpiryDate) {
        VisaExpiryDate = visaExpiryDate;
    }
}
