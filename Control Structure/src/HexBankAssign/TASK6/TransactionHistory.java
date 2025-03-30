package HexBankAssign.TASK6;

import java.util.ArrayList;
import java.util.Scanner;

public class TransactionHistory {
    public static void main(String[] args) {

        ArrayList<String> transaction = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        double balance = 0.0;
        boolean run = true;

        System.out.println("Welcome to bank transaction system.");

        while (run){

            if (run) {
                System.out.println("\nChoose an option : ");
                System.out.println("1 - Deposit Money.");
                System.out.println("2 - Withdraw Money.");
                System.out.println("3 - Exit.");
                System.out.print("\nEnter your choice : ");

                int choice = sc.nextInt();

                switch (choice){
                    case 1:
                        System.out.print("Enter amount to deposit : ");
                        double deposit = sc.nextDouble();
                        if (deposit > 500){
                            balance += deposit;
                            transaction.add("Deposited : ₹" + deposit);
                            System.out.println("Successfully Deposited : ₹" + deposit);
                        }
                        else
                            System.out.println("Invalid amount! Deposit amount must be greater than 500.");
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw : ");
                        double withdraw = sc.nextDouble();
                        if (withdraw > 0 && withdraw <= balance){
                            balance -= withdraw;
                            transaction.add("Withdrew : ₹" + withdraw);
                            System.out.println("Successfully Withdrew : ₹" + withdraw);
                        }
                        else
                            System.out.println("Insufficient Balance!!!");
                        break;

                    case 3:
                        run = false;
                        break;

                    default:
                        System.out.println("Invalid selection!! Please choose valid option.");

                }
            }


        }

        System.out.println("\nTransaction History : ");
        if (transaction.isEmpty()){
            System.out.println("No transactions done yet.");
        }
        else{
            for (String trans : transaction){
                System.out.println(trans);
            }
        }
        System.out.println("Available Balance : ₹" + balance);
        System.out.println("Thanks for choosing our banking service.");


        sc.close();
    }
}
