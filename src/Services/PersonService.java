package Services;

import Exceptions.InvalidPersonException;
import Exceptions.PersonExistsException;
import Models.Person;
import Repositories.PersonRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonService implements IPersonService{
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void createPerson(String name, int yearOfBirth) {
        try {
            // check if the person already exists
            if (personRepository.readPersonByName(name) != null) {
                throw new PersonExistsException("Models.Person with name " + name + " already exists");
            }
            personRepository.createPerson(name, yearOfBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch ( PersonExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePerson(String name) {
        try {
            Person person = personRepository.readPersonByName(name);
            if (person == null) {
                throw new InvalidPersonException("Models.Person with name " + name + " not found");
            }

            personRepository.deletePerson(person.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch ( InvalidPersonException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyPerson(int id, String newName, int newYearOfBirth) {
        try {
            Person person = personRepository.readPersonById(id);
            if (person == null) {
                throw new InvalidPersonException("Models.Person with id " + id + " not found");
            }

            person.setName(newName);
            person.setYearOfBirth(newYearOfBirth);
            personRepository.updatePerson(person);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch ( InvalidPersonException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> getAllPersons() {
        return personRepository.readAllPeople();
    }

    @Override
    public Person getPersonById(int id) {
        try {
            return personRepository.readPersonById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Person getPersonByName(String name) {
        try {
            return personRepository.readPersonByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
