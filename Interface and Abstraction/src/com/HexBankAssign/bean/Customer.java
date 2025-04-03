// TASK 11.1

package com.HexBankAssign.bean;

public class Customer {

    private static long custIDCounter = 101;
    private long customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.customerID = custIDCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.address = address;
    }

    public long getCustomerID() {
        return customerID;
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

    public void setEmail(String email) {
        if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            this.email = email;
        }
        else
            throw new IllegalArgumentException("Invalid email format!");
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d{10}")){
            this.phoneNumber = phoneNumber;
        }
        else
            throw new IllegalArgumentException("Invalid Phone Number format! Must contain 10 digits.");
    }

    public void displayCustInfo(){
        System.out.printf("%-20s : %s%n", "Customer ID", getCustomerID());
        System.out.printf("%-20s : %s%n", "Name", getFirstName() + " " + getLastName());
        System.out.printf("%-20s : %s%n", "Email", getEmail());
        System.out.printf("%-20s : %s%n", "Phone Number", getPhoneNumber());
        System.out.printf("%-20s : %s%n", "Address", getAddress());

    }
}

