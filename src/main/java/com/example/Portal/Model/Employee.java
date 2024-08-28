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

import java.util.List;

public class Employee {


    private String employeeID;
    private String firstName;
    private String lastName;
    private String dob;
    private String personalEmail;
    private String ssn;
    private String phoneNumber;
    private String homeAddress;
    private String maritalStatus;

    private String drivingLicenseNumber;
    private String drivingLicenseExpiryDate;
    private String passportNumber;
    private String passportExpiryDate;
    private String visaStatus;
    private String visaExpiryDate;
    private String company;


    public String getEmployeeID() {
        return employeeID;
    }

//    public void setEmployeeID(String employeeID) {
//        this.employeeID = employeeID;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getDrivingLicenseExpiryDate() {
        return drivingLicenseExpiryDate;
    }

    public void setDrivingLicenseExpiryDate(String drivingLicenseExpiryDate) {
        this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

    public String getVisaStatus() {
        return visaStatus;
    }

    public void setVisaStatus(String visaStatus) {
        this.visaStatus = visaStatus;
    }

    public String getVisaExpiryDate() {
        return visaExpiryDate;
    }

    public void setVisaExpiryDate(String visaExpiryDate) {
        this.visaExpiryDate = visaExpiryDate;
    }

    public String getCompany() {
        return company;
    }

//    public void setCompany(String company) {
//        this.company = company;
//    }
}
