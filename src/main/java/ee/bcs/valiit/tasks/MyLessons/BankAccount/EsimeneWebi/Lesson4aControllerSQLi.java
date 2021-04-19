package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

import ee.bcs.valiit.solution.controller.ObjectRowMapper;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Lesson4aControllerSQLi {
    private static Map<String, Double> accountBalanceMap = new HashMap<>();


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public static void main(String[] args) {

    }

    // http://localhost:8080/banksql/createAccount?accountNr=EE123&balance=1550&locked=false&accountId=10&ownerName=john
    @GetMapping("banksql/createAccount")
    public void Account(@RequestParam("accountNr") String accountNr,
                        @RequestParam("balance") Double balance,
                        @RequestParam("locked") Boolean locked,
                        @RequestParam("accountId") Integer accountId,
                        @RequestParam("ownerName") String ownerName) {
        String sql = "INSERT INTO account(account_number, balance, locked, account_id, owner_name)" +
                " VALUES(:dbAccNr, :dbBalance, :dbLocked, :dbAccountId, :dbOwnerName)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNr);
        paramMap.put("dbBalance", balance);
        paramMap.put("dbLocked", locked);
        paramMap.put("dbAccountId", accountId);
        paramMap.put("dbOwnerName", ownerName);
        jdbcTemplate.update(sql, paramMap);
    }


    //localhost:8080/banksql/all
    @GetMapping("banksql/all")
    public List<Account> getAll() {
        String sql = "SELECT*FROM account";
        Map<String, Object> accountMap = new HashMap<>();
        List<Account> resultList = jdbcTemplate.query(sql, accountMap, new ObjectRowMapper());
        return resultList;
    }


    // http://localhost:8080/banksql/depositMoney/
    @PutMapping("banksql/depositMoney")
    public String deposit(@RequestBody Account depositReq) {
        if (depositReq.getBalance() > 0) {
            //new balance for printbalance
            String old = "SELECT balance From account Where account_number = :account_number";
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("account_number", depositReq.getAccountNumber());
            Double oldBalance = jdbcTemplate.queryForObject(old, paramMap1, Double.class);

            //insert deposit
            String deposit = "UPDATE account SET balance = :balance WHERE account_number = :account_number";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put(("account_number"), depositReq.getAccountNumber());
            Double balance = new Double(depositReq.getBalance() + (double) oldBalance);
            paramMap2.put(("balance"), depositReq.getBalance() + (double) oldBalance);
            jdbcTemplate.update(deposit, paramMap2);
            return "Added " + depositReq.getBalance() + " to " + depositReq.getAccountNumber() + " new balance is " + balance;
        } else {
            return "Enter a positive value.";
        }
    }

    // http://localhost:8080/banksql/withdraw/
    @PutMapping("banksql/withdraw")
    public String withdraw(@RequestBody Account withdrawReq) {
        String old = "SELECT balance From account Where account_number = :account_number";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("account_number", withdrawReq.getAccountNumber());
        Double oldBalance = jdbcTemplate.queryForObject(old, paramMap1, Double.class);
        if (withdrawReq.getBalance() <= oldBalance) {

            String withdraw = "UPDATE account SET balance = :balance WHERE account_number =:account_number";
            String newBalance = "SELECT balance FROM account Where account_number =:account_number";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put(("account_number"), withdrawReq.getAccountNumber());
            paramMap2.put(("balance"), oldBalance - withdrawReq.getBalance());
            jdbcTemplate.update(withdraw, paramMap2);
            Double balance = jdbcTemplate.queryForObject(newBalance, paramMap2, Double.class);

            return "withdraw " + withdrawReq.getBalance() + withdrawReq.getAccountNumber() + " new balace in = " + balance;

        } else {

            return "You can't withdraw that much. You have: " + oldBalance;
        }
    }

    @PutMapping("banksql/transfer")
    public String transfer(@RequestBody Account transferReq) {
        String transferForm = "SELECT balance From account Where account_number = :account_number";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("account_number", transferReq.getAccountNumber());
        Double balance = jdbcTemplate.queryForObject(transferForm, paramMap3, Double.class);
        balance = balance -transferReq.getBalance();
        if (balance>0) {
            String transferForm1 = "UPDATE account SET balance = dbBalance WHERE account_number = account_number";
            paramMap3.put("account_number", balance);
            jdbcTemplate.update(transferForm1, paramMap3);

            String transferTo = "SELECT balance From account Where account_number = :account_number";
            paramMap3.put("account_number", transferReq.getAccountNumber());
            Double balance1 = jdbcTemplate.queryForObject(transferTo, paramMap3, Double.class);
            balance1 = balance1 + transferReq.getBalance();
            String transferTo1 = "UPDATE account SET balance =:dbBalance WHERE account_number =:account_number";
            paramMap3.put("account_number", balance1);
            jdbcTemplate.update(transferTo1, paramMap3);
            return " New balance is " + balance1;
        }
return " ";

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
}