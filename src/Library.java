import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private PersonRepository personRepository;
    private BookRepository bookRepository;

    private Library() {
        personRepository = new PersonRepository();
        bookRepository = new BookRepository();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void createPerson(String name, int yearOfBirth) {
        try {
            // check if the person already exists
            if (personRepository.readPersonByName(name) != null) {
                throw new PersonExistsException("Person with name " + name + " already exists");
            }
            personRepository.createPerson(name, yearOfBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersonExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePerson(String name) {
        try {
            Person person = personRepository.readPersonByName(name);
            if (person == null) {
                throw new InvalidUserException("Person with name " + name + " not found");
            }

            personRepository.deletePerson(person.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidUserException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyPerson(int id, String newName, int newYearOfBirth) {
        try {
            Person person = personRepository.readPersonById(id);
            if (person == null) {
                throw new InvalidUserException("Person with id " + id + " not found");
            }

            person.setName(newName);
            person.setYearOfBirth(newYearOfBirth);
            personRepository.updatePerson(person);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidUserException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> getAllPersons() throws SQLException {
        return personRepository.readAllPeople();
    }
}
