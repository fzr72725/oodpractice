package atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private String idNumber;

    public Person(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    @Override
    public boolean equals(Object anotherObj) {
        if (this == anotherObj) return true;
        if (!(anotherObj instanceof Person)) return false;
        Person anotherPerson = (Person) anotherObj;
        return this.name.equals(anotherPerson.name) &&
                this.idNumber.equals(anotherPerson.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, idNumber);
    }
}

