package ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi;

public class WithdrawMoneyRequest {
    private String accountNumber;
    private Double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
