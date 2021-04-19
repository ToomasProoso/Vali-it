package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4aControllerSQLi {
    private static Map<String, Double> accountBalanceMap = new HashMap<>();


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

    }

    // http://localhost:8080/banksql/createAccount?accountNr=EE123&balance=1550
    @GetMapping("banksql/createAccount")
    public void createAccount(@RequestParam("accountNr") String accountNr,
                              @RequestParam("balance") Double balance) {
        String sql = "INSERT INTO account(account_number, balance) VALUES(:dbAccNr, :dbAmount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNr);
        paramMap.put("dbAmount", balance);
        jdbcTemplate.update(sql, paramMap);
    }
    // http://localhost:8080/banksql/depositMoney?accountNr=EE123&balance=1550
    @PutMapping("banksql/depositMoney")
    public String depositMoney(@RequestParam CreateAccount depositReq) {
        if (depositReq.getAmount() > 0) {
            //new balance for print
            String old = "SELECT balanc From account Where account_number = :account_number";
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("dbAmount", depositReq.getAmount());
            paramMap1.put("dbAccNr", depositReq.getAccountNumber());
            Integer oldBalance = jdbcTemplate.queryForObject(old, paramMap1, Integer.class);

            //insert deposit
            String insertDeposit = "UPDATE account SET balance = :balance WHERE account = account";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put(("dbAccNr"), depositReq.getAccountNumber());
            paramMap2.put("dbAmount", depositReq.getAmount() + (double) oldBalance);
            jdbcTemplate.update(insertDeposit, paramMap2);
        }
        return "Added " + depositReq.getAccountNumber() + " to " + depositReq.getAccountNumber() + " new balance is";




    }
}

//    @PutMapping("sample/bank/account/{accountNumber}/lock")
//    public String lock(@PathVariable("accountNumber") String accountNr){
//        return null;
//    }
//
//    @PutMapping("sample/bank/account/{accountNumber}/unlock")
//    public String unlock(@PathVariable("accountNumber") String accountNr){
//        return null;
//    }
//}