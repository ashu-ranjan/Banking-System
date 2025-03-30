package com.HexBankAssign.TASK5;

import java.util.Scanner;

public class PasswordValidation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Create password for bank account : ");
        String password = sc.nextLine();

        if (isValidPassword(password)){
            System.out.println("Password created successfully !! ");
        } else {
            System.out.println("Invalid password! Make sure your password contains : ");
            System.out.println(" - At least 8 characters. ");
            System.out.println(" - At least one uppercase letter. ");
            System.out.println(" - At least one digit");
        }
        sc.close();

    }

    public static boolean isValidPassword(String password){
        if (password.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasDigit = false;

        for (char ch : password.toCharArray()){
            if (Character.isUpperCase(ch)) hasUppercase = true;
            if (Character.isDigit(ch)) hasDigit = true;
        }
        return hasUppercase && hasDigit;
    }
}
