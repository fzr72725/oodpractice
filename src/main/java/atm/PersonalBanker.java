package atm;

import java.util.Objects;

public class PersonalBanker extends Person{
    private final String employeeId;
    private Database db = Database.getInstance();

    public PersonalBanker(String name, String idNumber, String employeeId) {
        super(name, idNumber);
        this.employeeId = employeeId;
    }

    public String registerCustomer(String name, String idNumber) {
        String newCustomerId = generateCustomerId();
        Customer newCustomer = new Customer(name, idNumber, newCustomerId);
        db.addCustomer(newCustomer);
        return newCustomerId;
    }

    public String openAccount(Customer customer) {
        String newAccountNumber = generateAccountNumber();
        Account newAccount = new Account(newAccountNumber);
        db.addAccount(newAccount);
        customer.addAccount(newAccountNumber);
        return newAccountNumber;
    }

    private String generateAccountNumber() {
        return "TODO";
    }

    private String generateCustomerId() {
        return "TODO";
    }

    @Override
    public boolean equals(Object anotherObj) {
        if (!(anotherObj instanceof PersonalBanker)) {
            return false;
        }
        PersonalBanker anotherPersonalBanker = (PersonalBanker) anotherObj;
        return super.equals(anotherObj) && Objects.equals(employeeId, anotherPersonalBanker.employeeId);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(employeeId);
    }
}

