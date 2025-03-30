package HexBankAssign.TASK1;

import java.util.Scanner;

public class ConditionalStatement {
    public static void main(String[] args) {

        double creditScore;
        double annualIncome;

        // 1. TAKING CUSTOMERS INPUT

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the credit score : ");
        creditScore = sc.nextDouble();
        System.out.print("Enter the annual income : ");
        annualIncome = sc.nextDouble();

        // 2. CHECKING OF CUSTOMER'S ELIGIBILITY FOR THE LOAN
        // 3. DISPLAYING OUTPUT

        if (creditScore > 700 && annualIncome >= 50000)
            System.out.println("Congratulations! You are eligible for loan.");
        else
            System.out.println("Sorry! Yor are not meeting the loan eligibility requirements.");

    }
}
