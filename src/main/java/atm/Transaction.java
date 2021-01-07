package atm;

public class Transaction {
    private static final Transaction transaction = new Transaction();

    private Transaction() {}

    public static Transaction getInstance() {
        return transaction;
    }

    public int inquiry(Account account) {
        return account.checkBalance();
    }

    public void deposit(Account account, int depositAmount) {
        account.deposit(depositAmount);
    }

    public int withdraw(Account account, int withdrawAmount) {
        return account.withdraw(withdrawAmount);
    }

    public int transfer(Account sourceAccount, Account destAccount, int transferAmount) {
        return sourceAccount.transfer(destAccount, transferAmount);
    }
}

