package ee.bcs.valiit.solution.hibernate;

import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
