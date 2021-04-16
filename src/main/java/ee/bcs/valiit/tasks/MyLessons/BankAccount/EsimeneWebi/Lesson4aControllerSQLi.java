package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



}
