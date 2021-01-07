package atm;

import java.util.Objects;

public class PersonalBanker extends Person{
    private final String employeeId;

    public PersonalBanker(String name, String idNumber, String employeeId) {
        super(name, idNumber);
        this.employeeId = employeeId;
    }

    public void openAccount(Customer customer) {
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(accountNumber);
        customer.addAccount(newAccount);
    }

    private String generateAccountNumber() {
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

