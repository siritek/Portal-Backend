package com.example.Portal.Model;

public class PreviousClients {

    private String previousClientID;
    private String employeeID;
    private String previousVendor;
    private String previousClient;
    private String startDate;
    private String endDate;

    // Helper method to check for null values
    // TODO: check for null values
    // Getters and Setters

    public String getPreviousClientID() {
        return previousClientID;
    }

    public void setPreviousClientID(String previousClientID) {
        this.previousClientID = previousClientID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPreviousVendor() {
        return previousVendor;
    }

    public void setPreviousVendor(String previousVendor) {
        this.previousVendor = previousVendor;
    }

    public String getPreviousClient() {
        return previousClient;
    }

    public void setPreviousClient(String previousClient) {
        this.previousClient = previousClient;
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
}
