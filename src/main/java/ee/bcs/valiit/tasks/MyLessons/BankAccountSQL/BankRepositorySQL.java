package ee.bcs.valiit.tasks.MyLessons.BankAccountSQL;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi.TransactionHistoryRowMapper;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.ObjectRowMapper;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import ee.bcs.valiit.tasks.MyLessons.BankAccountSQL.transaction.history.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
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


    public List<Account> getAll() {
        String sql = "SELECT*FROM account";
        return jdbcTemplate.query(sql, new HashMap<>(), new ObjectRowMapper());
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


    public List<TransactionEntity> findAll() {
        String sql = "SELECT * FROM transaction_history";
        Map<String, Object> transactionMap = new HashMap<>();
        List<TransactionEntity> rs = jdbcTemplate.query(sql, transactionMap, new TransactionHistoryRowMapper());
        return rs;
    }
}

//    public List<HistoryList> getHistory(){
//        String sql = "SELECT*FORM historyList";
//    }

//    public double getBalance(Double accountNr) {
//        String sql1 = "SELECT balance FROM account WHERE account_number = :account_number";
//        Map<String, Object> paramMap3 = new HashMap<>();
//        paramMap3.put("account_number" = accountNr);
//        return jdbcTemplate.queryForObject(sql1, paramMap3, Double.class);