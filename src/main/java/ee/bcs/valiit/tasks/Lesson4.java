package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    private static Map<String, Double> accountBalanceMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    //
    public static void main(String[] args) {

        while (true) {
            System.out.println("insert command:\n" +
                    "1. To create account insert : createAccount \n" +
                    "2. Get balance insert : getBalance \n" +
                    "3. Deposit money : depositMoney \n" +
                    "4. Withdraw money: withdrawMoney\n" +
                    "5. Transfer money : transfer \n");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            } else if (line.equalsIgnoreCase("createAccount")) {
                // TODO 1
                // Add command: "createAccount ${accountNr}"
                // this has to store accountNr with 0 balance

                System.out.println("Please enter account nr:");
                String accountNr = scanner.nextLine();
                System.out.println("Please enter initial balance");
                Double balance = scanner.nextDouble();
                scanner.nextLine();
                accountBalanceMap.put(accountNr, balance);
            } else if (line.equalsIgnoreCase("getBalance")) {
                // TODO 2
                // Add command: "getBalance ${accountNr}"
                // this has to display account balance of specific acount

                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine();
                System.out.println("Account: " + accountNr + " balance is: " + accountBalanceMap.get(accountNr));
                System.out.println();

            } else if (line.equalsIgnoreCase("depositMoney")) {
                // TODO 3
                // Add command: "depositMoney ${accountNr} ${amount}
                // this has to add specified amount of money to account
                // You have to check that amount is positive number

                System.out.println("To what account you want to deposit to?: ");
                String account = scanner.nextLine();
                System.out.println("Ok, so you want to add money to account: " + account + ". How much?: ");
                Double depositMoney = scanner.nextDouble();
                if (depositMoney > 0) {
                    Double currentBalance = accountBalanceMap.get(account);
                    accountBalanceMap.put(account, depositMoney + currentBalance);
                    System.out.println("Account: " + account + " balance is: " + accountBalanceMap.get(account));
                    System.out.println();
                } else {
                    System.out.println("Enter a positive value.");
                }
                scanner.nextLine();

            } else if (line.equalsIgnoreCase("withdrawMoney")) {
                // TODO 4
                // Add command: "withdrawMoney ${accountNr} ${amount}
                // This has to remove specified amount of money from account
                // You have to check that amount is positive number
                // You may not allow this transaction if account balance would become negative

                System.out.println("which account you want to withdraw ?: ");
                String account = scanner.nextLine();
                System.out.println("Ok, so you want to withdraw from account: " + account + ". How much?: ");
                Double withdrawMoney = scanner.nextDouble();
                if (withdrawMoney <= accountBalanceMap.get(account)) {
                    Double currentBalance = accountBalanceMap.get(account);
                    accountBalanceMap.put(account, currentBalance - withdrawMoney);
                    System.out.println("Account: " + account + " balance is: " + accountBalanceMap.get(account));
                    System.out.println();
                } else {
                    System.out.println("You can't withdraw that much. You have: " + accountBalanceMap.get(account));
                    System.out.println();
                }
                scanner.nextLine();
            } else if (line.equalsIgnoreCase("transfer")) {
                // TODO 5
                // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
                // This has to remove specified amount from fromAccount and add it to toAccount
                // Your application needs to check that toAccount is positive
                // And from account has enough money to do that transaction

                System.out.println("To which account you want to transfer money to?: ");
                String account = scanner.nextLine();
                System.out.println("Which account you want to transfer from?: ");
                String account2 = scanner.nextLine();
                System.out.println("Ok, so you want to transfer from " + account2 + " to " + account);
                System.out.println("Ok, how much?: ");
                Double transferMoney = scanner.nextDouble();
                if ((accountBalanceMap.get(account2) >= (accountBalanceMap.get(account)))) {
                    //take accounts current balances
                    Double account1Money = accountBalanceMap.get(account);
                    Double account2Money = accountBalanceMap.get(account2);
                    //update accounts balances
                    Double dedeuctedMoney = account2Money - transferMoney;
                    Double addedMoney = account1Money + transferMoney;
                    // put new balances back to both accounts
                    accountBalanceMap.put(account, addedMoney);
                    accountBalanceMap.put(account2, dedeuctedMoney);
                    System.out.println("Transferred from " + account2 + " to " + account + ", " + transferMoney + " success");
                    System.out.println("New balance of " + account + " is " + accountBalanceMap.get(account));
                    System.out.println("Remaining balance of " + account2 + " is " + accountBalanceMap.get(account2));
                } else {
                    System.out.println("Oops, you don't have that much money.");
                }
                scanner.nextLine();
            }
        }
    }
}


// TODO 1
// Add command: "createAccount ${accountNr}"
// this has to store accountNr with 0 balance
// TODO 2
// Add command: "getBalance ${accountNr}"
// this has to display account balance of specific acount
// TODO 3
// Add command: "depositMoney ${accountNr} ${amount}
// this has to add specified amount of money to account
// You have to check that amount is positive number
// TODO 4
// Add command: "withdrawMoney ${accountNr} ${amount}
// This has to remove specified amount of money from account
// You have to check that amount is positive number
// You may not allow this transaction if account balance would become negative
// TODO 5
// Add command: "transfer ${fromAccount} ${toAccount} ${amount}
// This has to remove specified amount from fromAccount and add it to toAccount
// Your application needs to check that toAccount is positive
// And from account has enough money to do that transaction
