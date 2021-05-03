package ee.bcs.valiit.tasks.MyLessons.BankAccountSQL;


import ee.bcs.valiit.solution.exception.SampleApplicationException;
import ee.bcs.valiit.solution.hibernate.HibernateAccountRepository;
import ee.bcs.valiit.solution.hibernate.HibernateAccount;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.WithdrawMoneyRequest;
import ee.bcs.valiit.tasks.MyLessons.BankAccountSQL.transaction.history.TransactionEntity;
import ee.bcs.valiit.tasks.MyLessons.BankAccountSQL.transaction.history.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankServiceSQL {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BankRepositorySQL bankRepositorySQL;

    @Autowired
    private HibernateAccountRepository hibernateRepository;

    @Autowired
    private TransactionRepository transactionRepository;




    public void account(String accountNr, Double balance, Boolean locked, Integer accountId, String ownerName) {
        bankRepositorySQL.getAccount(accountNr, balance, locked, accountId, ownerName);
    }

    public Double getBalance(String accountNr) {
        return bankRepositorySQL.getAccount(accountNr);
    }


    public String deposit(Account depositReq) {
        if (depositReq.getBalance() < 0) {
            throw new SampleApplicationException("The amount cannot be negative");
        }
        HibernateAccount account = hibernateRepository.getOne(depositReq.getAccountNumber());
        Double newBalance = depositReq.getBalance() + (double) account.getBalance();
        account.setBalance(newBalance);
        hibernateRepository.save(account);
        TransactionEntity entity = new TransactionEntity();
        entity.setDateTime(LocalDateTime.now());
        entity.setDeduction(depositReq.getBalance());
        entity.setFromAccount(depositReq.getAccountNumber());
        transactionRepository.save(entity);
        return "Added " + depositReq.getBalance() + " to " + depositReq.getAccountNumber() + " new balance is " + newBalance;

    }

    public String withdraw(WithdrawMoneyRequest withdrawReq) {
        HibernateAccount account = hibernateRepository.getOne(withdrawReq.getAccountNumber());
        if (withdrawReq.getBalance() > account.getBalance()) {
            throw new SampleApplicationException("You can't withdraw that much.");
        }

        Double newBalance = (double) account.getBalance() - withdrawReq.getBalance();
        account.setBalance(newBalance);
        hibernateRepository.save(account);
        return "withdraw " + withdrawReq.getBalance() + withdrawReq.getAccountNumber() + " new balace in = " + newBalance;

    }
@Transactional
//transfer meetodil saab @Transactional viitega hibernateRepository.save(account);ära kustutada,
//kui panna classi ette siis võib kõigil meetoditel savei ära kustutada

    public String transfer(Account transferReq) {
        HibernateAccount account = hibernateRepository.getOne(transferReq.getAccountNumber());
        Double balance = (double)account.getBalance() - transferReq.getBalance();
        account.setBalance(balance);
        if (balance < 0) {
            throw new SampleApplicationException("You can't transfer that much.");
        }
        HibernateAccount account1 = hibernateRepository.getOne(transferReq.getAccountNumber1());
        Double balance1 = (double) account1.getBalance() + transferReq.getBalance();
        account1.setBalance(balance1);
        return "You transfer form account " + transferReq.getAccountNumber() + " new balance is: "+ balance +
                " and account " + transferReq.getAccountNumber1() + " new balance is " + balance1;

    }

    public List<Account> getAll() {
        return bankRepositorySQL.getAll();
    }

    public String delete(Account deleteReq) {
        return null;
    }

    public List<TransactionEntity> getAllHistory() {
        return bankRepositorySQL.findAll();
    }

    public TransactionEntity getOneHistory(String fromAccount) {
        return transactionRepository.getByFromAccount(fromAccount);
    }

    public List<TransactionEntity> getSearchHistory(String fromAccount) {
        return transactionRepository.findAllByFromAccountContaining(fromAccount);
    }


//        String transferForm = "SELECT balance From account Where account_number = :account_number";
//       Map<String, Object> paramMap3 = new HashMap<>();
//        paramMap3.put("account_number", transferReq.getAccountNumber());
//        Double balance = jdbcTemplate.queryForObject(transferForm, paramMap3, Double.class);
//        balance = balance - transferReq.getBalance();
//        if (balance >= 0) {
//            String transferForm1 = "UPDATE account SET balance = :balance WHERE account_number = :account_number";
//            paramMap3.put("balance", balance);
//            jdbcTemplate.update(transferForm1, paramMap3);
//
//            String transferTo = "SELECT balance From account Where account_number = :account_number";
//            paramMap3.put("account_number", transferReq.getAccountNumber1());
//            Double balance1 = jdbcTemplate.queryForObject(transferTo, paramMap3, Double.class);
//            balance1 = balance1 + transferReq.getBalance();
//            String transferTo1 = "UPDATE account SET balance =:balance1 WHERE account_number =:account_number";
//            paramMap3.put("balance1", balance1);
//            jdbcTemplate.update(transferTo1, paramMap3);
//            return " New balance is " + balance1;
//        }
//        return " ";


}
