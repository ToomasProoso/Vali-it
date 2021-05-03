package ee.bcs.valiit.tasks.MyLessons.BankAccountSQL.transaction.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    TransactionEntity getByTransferId (Integer transferId);
    TransactionEntity getByFromAccount(String fromAccount);
    List<TransactionEntity> findAllByFromAccountContaining(String fromAccount);
}
