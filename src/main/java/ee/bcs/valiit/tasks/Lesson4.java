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
            System.out.println("insert command:\n"+
                    "1. To create account insert : createAccount \n"+
                    "2. Get balance insert : getBalance \n" +
                    "3. Deposit money : depositMoney \n" +
                    "4. Withdraw money: withdrawMoney\n" +
                    "5. Transfer money : transfer \n" );
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
                System.out.println("Account balance is: " + accountBalanceMap.get(accountNr));

            } else if (line.equalsIgnoreCase("depositMoney")) {


                System.out.println();
                // TODO 3
                // Add command: "depositMoney ${accountNr} ${amount}
                // this has to add specified amount of money to account
                // You have to check that amount is positive number


            } else if (line.equalsIgnoreCase("withdrawMoney")) {
                // TODO 4
                // Add command: "withdrawMoney ${accountNr} ${amount}
                // This has to remove specified amount of money from account
                // You have to check that amount is positive number
                // You may not allow this transaction if account balance would become negative
            } else if (line.equalsIgnoreCase("transfer")) {
                // TODO 5
                // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
                // This has to remove specified amount from fromAccount and add it to toAccount
                // Your application needs to check that toAccount is positive
                // And from account has enough money to do that transaction
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
            else {
                System.out.println("Unknown command");
            }
        }
    }
}
