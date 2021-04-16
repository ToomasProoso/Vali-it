package ee.bcs.valiit.tasks.MyLessons.BankAccount.EsimeneWebi;

public class CreateAccount {
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private String accountNumber;
    private Double amount;
}
