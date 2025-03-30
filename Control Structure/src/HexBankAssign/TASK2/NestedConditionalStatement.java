package HexBankAssign.TASK2;

import java.util.Scanner;

public class NestedConditionalStatement {
    public static void main(String[] args) {

        int choice;
        double currentBalance;
        double withdrawAmount;
        double depositAmount;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Current Balance : ");
        currentBalance = sc.nextDouble();


        System.out.println("\nATM Menu : ");
        System.out.println("Select 1 - Check Balance");
        System.out.println("Select 2 - Withdraw Money");
        System.out.println("Select 3 - Deposit Money");
        
        System.out.print("Choose an option : ");
        choice = sc.nextInt();
        
        if (choice == 1){
            System.out.print("Your current balance is ₹" + currentBalance);

        } else if (choice == 2) {

            System.out.print("Enter Amount to withdraw : ");
            withdrawAmount = sc.nextDouble();

            if (withdrawAmount > currentBalance)
                System.out.println("    Insufficient Balance");
            else if (withdrawAmount % 100 != 0 && withdrawAmount % 500 != 0)
                System.out.println("    Please enter the amount in multiple of 100 and 500.");
            else {
                currentBalance -= withdrawAmount;
                System.out.println("    Withdrawn Successfully! Remaining balance is ₹" + currentBalance);
            }
        }

        else if (choice == 3){
            System.out.print("Enter Amount to deposit : ");
            depositAmount = sc.nextDouble();

            if(depositAmount <= 500)
                System.out.println("    Please enter the amount above 500.");
            else {
                currentBalance += depositAmount;
                System.out.println("    Deposited Successfully! Total amount after deposit ₹" + currentBalance);
            }
        }
        else
            System.out.println("    Invalid Choice! Please choose the valid option.");

        sc.close();
    }
}
