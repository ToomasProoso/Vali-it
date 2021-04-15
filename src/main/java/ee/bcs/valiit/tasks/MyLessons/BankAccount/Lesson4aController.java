//package ee.bcs.valiit.tasks.MyLessons.BankAccount;
//
//import ee.bcs.valiit.solution.controller.CreateAccountRequest;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class Lesson4aController {
//    private static Map<String, Double> accountBalanceMap = new HashMap<>();
//    public static void main(String[] args) {
//
//    }
//    // http://localhost:8080/bank/createAccount?accountNr=EE123&balance=1245
//    @GetMapping("bank/createAccount")
//    public void createAccount(@RequestParam("accountNr") String accountNr, Double balance){
//            accountBalanceMap.put(accountNr, balance);
//
//        // http://localhost:8080/bank/account
//            @PostMapping("bank/account")
//            public void createAccount2 (CreateAccountRequest request){
//                accountBalanceMap.put(request.getAccountNumber());
//            }
//
//            @GetMapping("")
//
//
//        }
//
//            (line.equalsIgnoreCase("createAccount")) {
//
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
//
//}
