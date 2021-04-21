package ee.bcs.valiit.tasks.MyLessons.BankAccountSQL;


import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BankServiseSQL {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BankRepositorySQL bankRepositorySQL;

    public void account(String accountNr, Double balance, Boolean locked, Integer accountId, String ownerName) {
        bankRepositorySQL.getAccount(accountNr, balance, locked, accountId, ownerName);
    }

    public String deposit(Account depositReq) {
        if (depositReq.getBalance() >= 0) {
            Double oldBalance = bankRepositorySQL.getAccount(depositReq.getAccountNumber());
            Double newBalance = depositReq.getBalance() + (double) oldBalance;
            bankRepositorySQL.updateBalance(depositReq.getAccountNumber(), newBalance);
            return "Added " + depositReq.getBalance() + " to " + depositReq.getAccountNumber() + " new balance is " + newBalance;
        } else {
            return "Enter a positive value.";
        }
    }

    public String withdraw(Account withdrawReq) {
        if (withdrawReq.getBalance() >= 0) {
            Double oldBalance = bankRepositorySQL.getAccount(withdrawReq.getAccountNumber());
            Double newBalance = (double) oldBalance - withdrawReq.getBalance();
            bankRepositorySQL.updateBalance(withdrawReq.getAccountNumber(), newBalance);
            return "withdraw " + withdrawReq.getBalance() + withdrawReq.getAccountNumber() + " new balace in = " + newBalance;
        }
        return "You can't withdraw that much.";
    }

    public String transfer(Account transferReq) {
        Double balance = bankRepositorySQL.getAccount(transferReq.getAccountNumber());
        balance = balance - transferReq.getBalance();
        if (balance >= 0) {
            bankRepositorySQL.updateBalance(transferReq.getAccountNumber(), balance);
            Double balance1 = bankRepositorySQL.getAccount(transferReq.getAccountNumber1());
            balance1 = balance1 + transferReq.getBalance();
            bankRepositorySQL.updateBalance(transferReq.getAccountNumber1(), balance1);
            return " New balance is " + balance1;
        }
        return " ";


    }

//        String transferForm = "SELECT balance From account Where account_number = :account_number";
//       Map<String, Object> paramMap3 = new HashMap<>();
//        paramMap3.put("account_number", transferReq.getAccountNumber());
//        Double balance = jdbcTemplate.queryForObject(transferForm, paramMap3, Double.class);
//        balance = balance - transferReq.getBalance();
//        if (balance >= 0) {
//            String transferForm1 = "UPDATE account SET balance = :balance WHERE account_number = :account_number";
//            paramMap3.put("balance", balance);
//            jdbcTemplate.update(transferForm1, paramMap3);
//
//            String transferTo = "SELECT balance From account Where account_number = :account_number";
//            paramMap3.put("account_number", transferReq.getAccountNumber1());
//            Double balance1 = jdbcTemplate.queryForObject(transferTo, paramMap3, Double.class);
//            balance1 = balance1 + transferReq.getBalance();
//            String transferTo1 = "UPDATE account SET balance =:balance1 WHERE account_number =:account_number";
//            paramMap3.put("balance1", balance1);
//            jdbcTemplate.update(transferTo1, paramMap3);
//            return " New balance is " + balance1;
//        }
//        return " ";


}
