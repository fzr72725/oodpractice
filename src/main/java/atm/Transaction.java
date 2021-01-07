package atm;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Transaction {
    private static final Transaction transaction = new Transaction();
    private Database db = Database.getInstance();

    private Transaction() {}

    public static Transaction getInstance() {
        return transaction;
    }

    private Optional<Customer> getCustomer(String customerId) {
        return Optional.ofNullable(db.getCustomer(customerId));
    }

    private Optional<Account> getAccount(String accountNumber) {
        return Optional.ofNullable(db.getAccount(accountNumber));
    }

    private boolean isInputValid(String customerId, String accountNumber) {
        if (accountNumber == null ||
                accountNumber.isEmpty() ||
                customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Account number or customerId cannot be blank! ");
        }
        if (!getCustomer(customerId).isPresent()) {
            throw new NoSuchElementException("This customer does not exist!");
        }
        Customer customer = getCustomer(customerId).get();
        if (customer.accountNotExist(accountNumber)) {
            throw new IllegalArgumentException("Customer is not associated with this account!");
        }
        if (!getAccount(accountNumber).isPresent()) {
            throw new NoSuchElementException("This account does not exist!");
        }
        return true;
    }

    public int inquiry(String customerId, String accountNumber) {
        if (isInputValid(customerId, accountNumber)) {
            Account account = getAccount(accountNumber).get();
            return account.checkBalance();
        }
        return -1;
    }

    public void deposit(String customerId, String accountNumber, int depositAmount) {
        if (isInputValid(customerId, accountNumber)) {
            Account account = getAccount(accountNumber).get();
            account.deposit(depositAmount);
        }
    }

    public int withdraw(String customerId, String accountNumber, int withdrawAmount) {
        if (isInputValid(customerId, accountNumber)) {
            Account account = getAccount(accountNumber).get();
            return account.withdraw(withdrawAmount);
        }
        return -1;
    }

    public int transfer(String customerId, String accountNumber, String destAccountNumber, int transferAmount) {
        Optional<Account> optDestAccount = getAccount(destAccountNumber);
        if (isInputValid(customerId, accountNumber) && optDestAccount.isPresent()) {
            Account account = getAccount(accountNumber).get();
            Account destAccount = optDestAccount.get();
            return account.transfer(destAccount, transferAmount);
        }
        return -1;
    }
}

