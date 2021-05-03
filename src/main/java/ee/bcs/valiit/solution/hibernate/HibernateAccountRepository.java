package ee.bcs.valiit.solution.hibernate;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HibernateAccountRepository extends JpaRepository<HibernateAccount, String> {
    List<HibernateAccountRepository> findAllByAccountNumber(String accountNumber);
    //String = primariKey
}

