package com.HexBankAssignHMB.entity;
import java.time.LocalDate;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public void setEmail(String email) {
        if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            this.email = email;
        }
        else
            throw new IllegalArgumentException("Invalid email format!");
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d{10}")){
            this.phoneNumber = "+91-" + phoneNumber;
        }
        else
            throw new IllegalArgumentException("Invalid Phone Number format! Must contain 10 digits.");
    }

    public void displayCustInfo(){
        System.out.printf("%-20s : %s%n", "Customer ID", getCustomerId());
        System.out.printf("%-20s : %s%n", "Name", getFirstName() + " " + getLastName());
        System.out.printf("%-20s : %s%n", "Date of Birth", getDateOfBirth());
        System.out.printf("%-20s : %s%n", "Email", getEmail());
        System.out.printf("%-20s : %s%n", "Phone Number", getPhoneNumber());
        System.out.printf("%-20s : %s%n", "Address", getAddress());

    }
}
