package ee.bcs.valiit.tasks.MyLessons.BankAccount.TeineWebi;

public class Account {
    private String ownerName;
    private String accountNumber;
    private Double balance;
    private boolean locked;
    private int accointId;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getAccointId() {
        return accointId;
    }

    public void setAccointId(int accointId) {
        this.accointId = accointId;
    }
}
