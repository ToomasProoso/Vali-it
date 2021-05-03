package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

import ee.bcs.valiit.tasks.MyLessons.BankAccountSQL.transaction.history.TransactionEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransactionHistoryRowMapper implements RowMapper<TransactionEntity> {
    @Override
    public TransactionEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionEntity response = new TransactionEntity();
        response.setTransferId(resultSet.getInt("transfer_id"));
        response.setFromAccount(resultSet.getString("deposit"));
        response.setToAccount(resultSet.getString("withdraw"));
        response.setTransfer(resultSet.getDouble("transfer"));
        response.setDeduction(resultSet.getDouble("deduction"));
        response.setDateTime((LocalDateTime) resultSet.getObject("time"));

        return response;
    }
}
