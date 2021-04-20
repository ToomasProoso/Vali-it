package ee.bcs.valiit.tasks.MyLessons.BankAccountSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepositorySQL {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void account(String accountNr, Double balance, Boolean locked, Integer accountId, String ownerName) {
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

    public double account(String accountNr) {

        String old = "SELECT balance From account Where account_number = :account_number";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("account_number", accountNr);
        return jdbcTemplate.queryForObject(old, paramMap1, Double.class);
    }

    public void updateBalance(String accountNumber, Double newBalance) {
        String deposit = "UPDATE account SET balance = :balance WHERE account_number = :account_number";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("account_number", accountNumber);
        paramMap2.put("balance", newBalance);
        jdbcTemplate.update(deposit, paramMap2);

    }

    public void withdraw(String accountNumber, Double newBalance) {
        String withdraw = "UPDATE account SET balance = :balance WHERE account_number =:account_number";
        Map<String, Object> paramMap4 = new HashMap<>();
        paramMap4.put(("account_number"), accountNumber);
        paramMap4.put(("balance"), newBalance);
        jdbcTemplate.update(withdraw, paramMap4);

    }

    public Double transferfrom(String accountNumber, Double balance) {
        String sql = "SELECT balance From account Where account_number = :account_number";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("account_number", accountNumber  );
        return jdbcTemplate.queryForObject(sql, paramMap3, Double.class);

    }
    public void transferfrom1(String accountNumber, Double balance){
        String sql1 = "UPDATE account SET balance = :balance WHERE account_number = :account_number";
        Map<String, Object> paramMap5 = new HashMap<>();
        paramMap5.put("balance", balance);
        jdbcTemplate.update(sql1, paramMap5);

    }
    public Double transferTo(String accountNumber, Double balance1){
        String sql2 = "SELECT balance From account Where account_number = :account_number";
        Map<String, Object> paramMap6 = new HashMap<>();
        paramMap6.put("account_number", accountNumber);
        return jdbcTemplate.queryForObject(sql2, paramMap6, Double.class);

    }
    public void transferTo1(String accountNumber, Double balance1){
        String sql3 = "UPDATE account SET balance =:balance1 WHERE account_number =:account_number";
        Map<String, Object> paramMap7 = new HashMap<>();
        paramMap7.put("balance1", balance1);
        jdbcTemplate.update(sql3, paramMap7);

    }



}
