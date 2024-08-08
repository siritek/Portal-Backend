package com.example.Portal.Model;

import java.util.Date;

public class PresentClients {
    private String presentClientID;
    private String employeeID;
    private String presentVendor;
    private String presentClient;
    private String primeVendor;
    private String startDate;
    private String endDate;
    private String clientEmail;
    private String clientPhoneNumber;
    private String clientAddress;

    // Helper method to check for null values
    // TODO: check for null values
    // Getters and Setters

    public String getPresentClientID() {
        return presentClientID;
    }

    public void setPresentClientID(String presentClientID) {
        this.presentClientID = presentClientID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPresentVendor() {
        return presentVendor;
    }

    public void setPresentVendor(String presentVendor) {
        this.presentVendor = presentVendor;
    }

    public String getPresentClient() {
        return presentClient;
    }

    public void setPresentClient(String presentClient) {
        this.presentClient = presentClient;
    }

    public String getPrimeVendor() {
        return primeVendor;
    }

    public void setPrimeVendor(String primeVendor) {
        this.primeVendor = primeVendor;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
}
