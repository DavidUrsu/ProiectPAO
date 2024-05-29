package Models;

public class Person extends AbstractEntity {
    protected String name;
    protected int yearOfBirth;

    public Person(int id, String name, int yearOfBirth) {
        super(id);
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
