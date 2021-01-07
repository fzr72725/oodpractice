package atm;

public class Account {
    private final String accountNumber;
    private int amountAvailable;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account(String accountNumber, int amountAvailable) {
        this(accountNumber);
        this.amountAvailable = amountAvailable;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void deposit(int depositAmount) {
        if (depositAmount <= 0) {
            throw new IllegalArgumentException("Deposit amount cannot be 0 or negative!");
        }
        amountAvailable += depositAmount;
    }

    public int withdraw(int withdrawAmount) {
        if (withdrawAmount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be 0 or negative!");
        }
        if (amountAvailable < withdrawAmount) {
            throw new IllegalArgumentException("Overdraft is not allowed!");
        }
        amountAvailable -= withdrawAmount;
        return withdrawAmount;
    }

    public int checkBalance() {
        return amountAvailable;
    }

    public int transfer(Account destAccount, int transferAmount) {
        int sourceWithdrawal = withdraw(transferAmount);
        destAccount.deposit(sourceWithdrawal);
        return transferAmount;
    }
}

