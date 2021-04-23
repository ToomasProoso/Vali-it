package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

import ee.bcs.valiit.solution.controller.ObjectRowMapper;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import ee.bcs.valiit.tasks.MyLessons.BankAccountSQL.BankServiseSQL;
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

    @Autowired
    private BankServiseSQL bankServiseSQL;


    public static void main(String[] args) {

    }

    // http://localhost:8080/banksql/createAccount?accountNr=EE123&balance=1550&locked=false&accountId=10&ownerName=john
    @GetMapping("banksql/createAccount")
    public void account(@RequestParam("accountNr") String accountNr,
                        @RequestParam("balance") Double balance,
                        @RequestParam("locked") Boolean locked,
                        @RequestParam("accountId") Integer accountId,
                        @RequestParam("ownerName") String ownerName) {
        bankServiseSQL.account(accountNr, balance, locked, accountId, ownerName);
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
    @CrossOrigin
    @PutMapping("banksql/deposit")
    public String deposit(@RequestBody Account depositReq) {
        return bankServiseSQL.deposit(depositReq);

    }

    // http://localhost:8080/banksql/withdraw/
    @PutMapping("banksql/withdraw")
    public String withdraw(@RequestBody Account withdrawReq) {
        return bankServiseSQL.withdraw(withdrawReq);

    }

    @PutMapping("banksql/transfer")
    public String transfer(@RequestBody Account transferReq) {
        return bankServiseSQL.transfer(transferReq);

    }

    @PutMapping("banksql/account/{accountNumber}/lock")
    public String lock(@PathVariable("accountNumber") String accountNr) {
        return null;
    }

    @PutMapping("banksql/account/{accountNumber}/unlock")
    public String unlock(@PathVariable("accountNumber") String accountNr) {
        return null;
    }
}