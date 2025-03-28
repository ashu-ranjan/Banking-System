package com.HexBankAssign.TASK3;

import java.util.Scanner;

public class LoopStructures {


    static double calFutureBalance(double initialBalance, double annualInterest, int years){
        return initialBalance * Math.pow((1 + annualInterest / 100), years);
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        System.out.print("Enter the number of customers : ");
        int numCustomer = sc.nextInt();

        for (int i = 1; i <= numCustomer ; i++) {
            System.out.println("Customer " + i + " : ");

            System.out.print("Enter the Initial Balance : ");
            double initialBalance = sc.nextDouble();

            System.out.print("Enter the annual interest rate :");
            double annualInterest = sc.nextDouble();

            System.out.print("Enter the number of years : ");
            int years = sc.nextInt();


            double futureBalance = calFutureBalance(initialBalance, annualInterest, years);
            System.out.printf("Future balance after %d years : %.2f \n", years, futureBalance);

        }
        sc.close();

    }
}
