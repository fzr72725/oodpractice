package atm;

import java.util.*;

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
    private String customerId;
    private Set<String> accountNumbers;
    private Database db = Database.getInstance();
    //private List<String> accountsImmutable = Collections.unmodifiableMap(accounts);

    public Customer(String name, String idNumber, String customerId) {
        super(name, idNumber);
        this.customerId = customerId;
        accountNumbers = new HashSet<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void addAccount(String newAccountNumber) {
        accountNumbers.add(newAccountNumber);
    }

    protected boolean accountNotExist(String accountNumber) {
        return accountNumbers.contains(accountNumber);
    }

    @Override
    public boolean equals(Object anotherObj) {
        if (!(anotherObj instanceof Customer)) {
            return false;
        }
        Customer anotherCustomer= (Customer) anotherObj;
        return super.equals(anotherObj) &&
                Objects.equals(accountNumbers, anotherCustomer.accountNumbers) &&
                Objects.equals(customerId, anotherCustomer.customerId);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(accountNumbers, customerId);
    }

}
