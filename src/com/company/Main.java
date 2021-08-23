package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    BankAccount account = new BankAccount("Daotaku", "XYZ-001");
        account.showMenu();
    }

}

class BankAccount {
    String accountName, accountId;
    long balance, previousTransaction;

    BankAccount(String accountName, String accountId)
    {
        this.accountName = accountName;
        this.accountId = accountId;
    }

    public void deposit(int amount)
    {
        this.balance += amount;
        this.previousTransaction += amount;
    }

    public void withdraw(int amount)
    {
        this.balance -= amount;
        this.previousTransaction = -amount;
    }

    public void getPreviousTransaction()
    {
        if (previousTransaction > 0) {
            System.out.println("You Deposit: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("You Withdraw: " + previousTransaction);
        } else {
            System.out.println("There is no transaction record.");
        }
    }

    public void choiceMenu()
    {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Select your choice:");
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Exit");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Please enter an option:");
        System.out.println("------------------------------------------------------------------------");
    }

    public void showMenu()
    {
        char option = '\0';
        Scanner scanner = new Scanner(System.in);

        do {
            choiceMenu();
            option = scanner.next().charAt(0);
            option = Character.toLowerCase(option);

            switch(option)
            {
                case 'a':
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Balance : " + String.format("%,d", balance));
                    System.out.println("------------------------------------------------------------------------");
                    break;
                case 'b':
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Please enter amount of deposit:");
                    System.out.println("------------------------------------------------------------------------");
                    while(!scanner.hasNextInt())
                    {
                        System.out.println("Please enter a number:");
                        scanner.next();
                    }
                    int amount = scanner.nextInt();
                    deposit(amount);
                    break;
                case 'c':
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Please enter amount to withdraw:");
                    System.out.println("------------------------------------------------------------------------");
                    while(!scanner.hasNextInt())
                    {
                        System.out.println("Please enter a number:");
                        scanner.next();
                    }
                    int amount2 = scanner.nextInt();
                    withdraw(amount2);
                    break;
                case 'd':
                    System.out.println("------------------------------------------------------------------------");
                    getPreviousTransaction();
                    System.out.println("------------------------------------------------------------------------");
                    break;
                case 'e':
                    System.out.println("***************************************************************");
                    break;
                default:
                    System.out.println("Invalid Option! Please try again!");
                    break;
            }

        } while (option != 'e');

        System.out.println("Thank you for using our services!");
    }
}
