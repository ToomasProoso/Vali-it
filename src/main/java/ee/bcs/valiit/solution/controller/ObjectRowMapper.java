package ee.bcs.valiit.solution.controller;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectRowMapper implements  org.springframework.jdbc.core.RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountNumber(resultSet.getString("account_number"));
        account.setBalance(resultSet.getDouble("balance"));
        account.setLocked(resultSet.getBoolean("locked"));
        account.setAccointId(resultSet.getInt("account_id"));
        account.setOwnerName(resultSet.getString("owner_name"));
       return account;
    }
}
