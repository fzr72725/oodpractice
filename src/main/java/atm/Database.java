package atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static final Database db = new Database();
    private Map<String, Account> accounts;
    private List<Customer> customers;

    private Database() {
        accounts = new HashMap<>();
        accounts.put("testAccount1", new Account("testAccount1"));
        accounts.put("testAccount2", new Account("testAccount2"));
        accounts.put("testAccount3", new Account("testAccount3"));
        customers = new ArrayList<>();
        customers.add(new Customer("customer1", "testCustomer1", "testCustomerId1"));
        customers.add(new Customer("customer2", "testCustomer2", "testCustomerId2"));
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static Database getInstance() {
        return db;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public Customer getCustomer(String customerId) {
        Customer res = null;
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                res = c;
            }
        }
        return res;
    }
}
