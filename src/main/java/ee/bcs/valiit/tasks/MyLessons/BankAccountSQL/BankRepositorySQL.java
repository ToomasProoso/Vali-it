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

    public void getAccount(String accountNr, Double balance, Boolean locked, Integer accountId, String ownerName) {
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

    public double getAccount(String accountNr) {
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


}
