package ee.bcs.valiit.tasks.MyLessons.BankAccountSQL;


import ee.bcs.valiit.solution.exception.SampleApplicationException;
import ee.bcs.valiit.solution.hibernate.HibernateAccountRepository;
import ee.bcs.valiit.solution.hibernate.HibernateAccount;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.Account;
import ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi.WithdrawMoneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankServiseSQL {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BankRepositorySQL bankRepositorySQL;

    @Autowired
    private HibernateAccountRepository hibernateRepository;



    public void account(String accountNr, Double balance, Boolean locked, Integer accountId, String ownerName) {
        bankRepositorySQL.getAccount(accountNr, balance, locked, accountId, ownerName);
    }

    public String deposit(Account depositReq) {
        if (depositReq.getBalance() < 0) {
            throw new SampleApplicationException("The amount cannot be negative");
        }
        HibernateAccount account = hibernateRepository.getOne(depositReq.getAccountNumber());
//        Double oldBalance = bankRepositorySQL.getAccount(depositReq.getAccountNumber());
        Double newBalance = depositReq.getBalance() + (double) account.getBalance();
        account.setBalance(newBalance);
        hibernateRepository.save(account);
//        bankRepositorySQL.updateBalance(depositReq.getAccountNumber(), newBalance);
        return "Added " + depositReq.getBalance() + " to " + depositReq.getAccountNumber() + " new balance is " + newBalance;

    }

    public String withdraw(WithdrawMoneyRequest withdrawReq) {
        HibernateAccount account = hibernateRepository.getOne(withdrawReq.getAccountNumber());
        if (withdrawReq.getBalance() > account.getBalance()) {
            throw new SampleApplicationException("You can't withdraw that much.");
        }
//        Double oldBalance = bankRepositorySQL.getAccount(withdrawReq.getAccountNumber());
        Double newBalance = (double) account.getBalance() - withdrawReq.getBalance();
        account.setBalance(newBalance);
        hibernateRepository.save(account);
//        bankRepositorySQL.updateBalance(withdrawReq.getAccountNumber(), newBalance);
        return "withdraw " + withdrawReq.getBalance() + withdrawReq.getAccountNumber() + " new balace in = " + newBalance;

    }
@Transactional
//transfer meetodil saab @Transactional viitega hibernateRepository.save(account);ära kustutada,
//kui panna classi ette siis võib kõigil meetoditel savei ära kustutada

    public String transfer(Account transferReq) {
        HibernateAccount account = hibernateRepository.getOne(transferReq.getAccountNumber());
//        Double balance = bankRepositorySQL.getAccount(transferReq.getAccountNumber());
        Double balance = (double)account.getBalance() - transferReq.getBalance();
        account.setBalance(balance);
        if (balance < 0) {
            throw new SampleApplicationException("You can't transfer that much.");
        }
//        hibernateRepository.save(account);
//        bankRepositorySQL.updateBalance(transferReq.getAccountNumber(), balance);
        HibernateAccount account1 = hibernateRepository.getOne(transferReq.getAccountNumber1());
//        Double balance1 = bankRepositorySQL.getAccount(transferReq.getAccountNumber1());
        Double balance1 = (double) account1.getBalance() + transferReq.getBalance();
        account1.setBalance(balance1);
//        hibernateRepository.save(account1);
//        bankRepositorySQL.updateBalance(transferReq.getAccountNumber1(), balance1);
        return "You transfer form account " + transferReq.getAccountNumber() + " new balance is: "+ balance +
                " and account " + transferReq.getAccountNumber1() + " new balance is " + balance1;

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
