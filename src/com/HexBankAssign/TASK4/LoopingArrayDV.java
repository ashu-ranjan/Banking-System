package com.HexBankAssign.TASK4;

import java.util.HashMap;
import java.util.Scanner;

public class LoopingArrayDV {
    public static void main(String[] args) {

        // CUSTOMERS ACCOUNTS AND BALANCE DECLARATION

        HashMap<Integer, Double> accounts = new HashMap<>();
        accounts.put(10001, 8039.98);
        accounts.put(10002, 47938.78);
        accounts.put(10003, 2876.54);
        accounts.put(10004, 29187.19);
        accounts.put(10005, 33876.66);

        Scanner sc = new Scanner(System.in);

        int accNumber;

        // LOOPING AND VALIDATION

        while (true){
            System.out.print("Enter your account number : ");
            accNumber = sc.nextInt();

            if (accounts.containsKey(accNumber)){
                System.out.print("Your balance is : ₹" + accounts.get(accNumber));
                break;
            }else
                System.out.println("Invalid!!! Please Enter Valid Account Number.");
        }
        sc.close();

    }
}
