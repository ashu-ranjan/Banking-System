package com.HexBankAssign.entity;

// TASK 7.1

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String address;

    public Customer() {

    }

    public Customer(int customerID, String firstName, String lastName, String email, long phoneNumber, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void customerInfo(){

        System.out.println("\n---> Customer Details <---");
        System.out.printf("\n%-20s : %d%n", "Customer ID", getCustomerID());
        System.out.printf("%-20s : %s%n", "Name", getFirstName() + " " + getLastName());
        System.out.printf("%-20s : %s%n", "Email", getEmail());
        System.out.printf("%-20s : %d%n", "Phone Number", getPhoneNumber());
        System.out.printf("%-20s : %s%n", "Address", getAddress());

    }

}

