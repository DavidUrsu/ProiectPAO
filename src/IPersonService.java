import java.sql.SQLException;
import java.util.List;

public interface IPersonService {
    void createPerson(String name, int yearOfBirth);
    void deletePerson(String name);
    void modifyPerson(int id, String newName, int newYearOfBirth);
    List<Person> getAllPersons() throws SQLException;
    Person getPersonById(int id);
    Person getPersonByName(String name);
}