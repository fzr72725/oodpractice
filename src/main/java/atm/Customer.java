package atm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Customer extends Person {
    /**
     * Balance inquiry: the user can view his/her account balance.
     * Cash withdrawal: the user can withdraw a certain amount of cash.
     * Deposit funds: the user can deposit cash or checks.
     * Transfer funds: the user can transfer funds to other accounts.
     */
    // classes:
    // Customer/ Transaction / Account
    // transaction obj (singleton) create customer obj that does the 4 ops
    // customer has account
    // account has number, amount
    // inquiry(accountNumber);
    // withdraw(accountNumber, amount);
    // deposit(accountNumber, amount)
    // transfer(accountNumber, destAccountNumber, amount)
    private Map<String, Account> accounts;
    private final Map<String, Account> accountsImmutable = Collections.unmodifiableMap(accounts);

    public Customer(String name, String idNumber) {
        super(name, idNumber);
        accounts = new HashMap<>();
    }

    public void addAccount(Account newAccount) {
        accounts.put(newAccount.getAccountNumber(), newAccount);
    }

    public void deposit(String accountNumber, int depositAmount) {
        if (!cannotOperate(accountNumber)) {
            Account account = accounts.get(accountNumber);
            Transaction.getInstance().deposit(account, depositAmount);
        }
    }

    public int withdraw(String accountNumber, int withdrawAmount) {
        if (!cannotOperate(accountNumber)) {
            Account account = accounts.get(accountNumber);
            Transaction.getInstance().withdraw(account, withdrawAmount);
            return withdrawAmount;
        }
        return -1;
    }

    public int inquiry(String accountNumber) {
        if (!cannotOperate(accountNumber)) {
            Account account = accounts.get(accountNumber);
            return Transaction.getInstance().inquiry(account);
        }
        return -1;
    }

    public int transfer(String accountNumber, Account destAccount, int transferAmount) {
        if (!cannotOperate(accountNumber)) {
            Account sourceAccount = accounts.get(accountNumber);
            return Transaction.getInstance().transfer(sourceAccount, destAccount, transferAmount);
        }
        return -1;
    }

    private boolean cannotOperate(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid account number! ");
        }
        if (accountNotExist(accountNumber)) {
            throw new IllegalArgumentException("Account number does not exist!");
        }
        return true;
    }

    private boolean accountNotExist(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    @Override
    public boolean equals(Object anotherObj) {
        if (!(anotherObj instanceof Customer)) {
            return false;
        }
        Customer anotherCustomer= (Customer) anotherObj;
        return super.equals(anotherObj) && Objects.equals(accounts, anotherCustomer.accounts);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(accounts);
    }

}
