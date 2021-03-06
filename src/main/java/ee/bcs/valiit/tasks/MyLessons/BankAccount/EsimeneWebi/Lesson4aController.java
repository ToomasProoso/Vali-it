package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi.CreateAccount;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4aController {
    private static Map<String, Double> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {

    }

    // http://localhost:8080/bank/createAccount?accountNr=EE123&balance=1245
//    @GetMapping("bank/createAccount")
//    public void createAccount(@RequestParam("accountNr") String accountNr, @RequestParam("balance") Double balance) {
//        accountBalanceMap.put(accountNr, balance);
//    }

    // http://localhost:8080/bank/account
    @PostMapping("bank/account")
    public void createAccount2(@RequestBody CreateAccount request) {
        accountBalanceMap.put(request.getAccountNumber(), request.getAmount());
    }

    // http://localhost:8080/bank/account/EE123
    @GetMapping("bank/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") String accountNr) {
        return "Account balance is: " + accountBalanceMap.get(accountNr);
    }

    //localhost:8080/bank/1/account/EE123/500
    @PutMapping("bank/1/account/{accountNumber}/{deposit}")
    public String depositMoney(@PathVariable("accountNumber") String accountNr, @PathVariable("deposit") double amount) {
        if (amount > 0) {
            Double currentBalance = accountBalanceMap.get(accountNr);
            accountBalanceMap.put(accountNr, currentBalance + amount);
        }
        return "Account: " + accountNr + " balance is: " + accountBalanceMap.get(accountNr);
    }

    //localhost:8080/bank/2/account/EE123/500
    @PutMapping("bank/2/account/{accountNumber}/{withdraw}")
    public String withdrawMoney(@PathVariable("accountNumber") String accountNr, @PathVariable("withdraw") double amount) {
        if (amount > accountBalanceMap.get(accountNr)) {
            return "Sorry!!! No money mate, only: " + accountBalanceMap.get(accountNr) + " left";
        } else {
            Double currentBalance = accountBalanceMap.get(accountNr);
            accountBalanceMap.put(accountNr, currentBalance - amount);
        }
        return "Account: " + accountNr + " balance is: " + accountBalanceMap.get(accountNr);


    }

    @PutMapping("bank/3/{firstAccountNumber}")
    public String transfer(@PathVariable("firstAccountNumber") String firstAccountNr,
                           @RequestBody CreateAccount request) {
        double firstAccountBalance = accountBalanceMap.get(firstAccountNr);
        if ((firstAccountBalance) >= (request.getAmount())) {
            firstAccountBalance = firstAccountBalance - request.getAmount();
            accountBalanceMap.replace(firstAccountNr, firstAccountBalance);

            double secondAccountBalance = accountBalanceMap.get(request.getAccountNumber());
            secondAccountBalance = secondAccountBalance + request.getAmount();
            accountBalanceMap.replace(request.getAccountNumber(), secondAccountBalance);
            return request.getAmount() + " transfer from " + firstAccountNr +
                    " to " + request.getAccountNumber();
        } else {
            return "Not enough money on account nr: " + firstAccountNr;

        }

    }


}


//                System.out.println("Please enter account nr:");
//                String accountNr = scanner.nextLine();
//                System.out.println("Please enter initial balance");
//                Double balance = scanner.nextDouble();
//                scanner.nextLine();
//                accountBalanceMap.put(accountNr, balance);
//            } else if (line.equalsIgnoreCase("getBalance")) {
//
//
//                System.out.println("Please enter account nr");
//                String accountNr = scanner.nextLine();
//                System.out.println("Account: " + accountNr + " balance is: " + accountBalanceMap.get(accountNr));
//                System.out.println();
//
//            } else if (line.equalsIgnoreCase("depositMoney")) {
//
//
//                System.out.println("To what account you want to deposit to?: ");
//                String account = scanner.nextLine();
//                System.out.println("Ok, so you want to add money to account: " + account + ". How much?: ");
//                Double depositMoney = scanner.nextDouble();
//                if (depositMoney > 0) {
//                    Double currentBalance = accountBalanceMap.get(account);
//                    accountBalanceMap.put(account, depositMoney + currentBalance);
//                    System.out.println("Account: " + account + " balance is: " + accountBalanceMap.get(account));
//                    System.out.println();
//                } else {
//                    System.out.println("Enter a positive value.");
//                }
//                scanner.nextLine();
//
//            } else if (line.equalsIgnoreCase("withdrawMoney")) {
//
//                System.out.println("which account you want to withdraw ?: ");
//                String account = scanner.nextLine();
//                System.out.println("Ok, so you want to withdraw from account: " + account + ". How much?: ");
//                Double withdrawMoney = scanner.nextDouble();
//                if (withdrawMoney <= accountBalanceMap.get(account)) {
//                    Double currentBalance = accountBalanceMap.get(account);
//                    accountBalanceMap.put(account, currentBalance - withdrawMoney);
//                    System.out.println("Account: " + account + " balance is: " + accountBalanceMap.get(account));
//                    System.out.println();
//                } else {
//                    System.out.println("You can't withdraw that much. You have: " + accountBalanceMap.get(account));
//                    System.out.println();
//                }
//                scanner.nextLine();
//            } else if (line.equalsIgnoreCase("transfer")) {
//
//                System.out.println("To which account you want to transfer money to?: ");
//                String account = scanner.nextLine();
//                System.out.println("Which account you want to transfer from?: ");
//                String account2 = scanner.nextLine();
//                System.out.println("Ok, so you want to transfer from " + account2 + " to " + account);
//                System.out.println("Ok, how much?: ");
//                Double transferMoney = scanner.nextDouble();
//                if ((accountBalanceMap.get(account2) >= (accountBalanceMap.get(account)))) {
//                    //take accounts current balances
//                    Double account1Money = accountBalanceMap.get(account);
//                    Double account2Money = accountBalanceMap.get(account2);
//                    //update accounts balances
//                    Double dedeuctedMoney = account2Money - transferMoney;
//                    Double addedMoney = account1Money + transferMoney;
//                    // put new balances back to both accounts
//                    accountBalanceMap.put(account, addedMoney);
//                    accountBalanceMap.put(account2, dedeuctedMoney);
//                    System.out.println("Transferred from " + account2 + " to " + account + ", " + transferMoney + " success");
//                    System.out.println("New balance of " + account + " is " + accountBalanceMap.get(account));
//                    System.out.println("Remaining balance of " + account2 + " is " + accountBalanceMap.get(account2));
//
//            }
//        }
//    }
//}


