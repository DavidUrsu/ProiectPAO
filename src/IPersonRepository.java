import java.sql.SQLException;
import java.util.List;

public interface IPersonRepository {
    void createPerson(String name, int yearOfBirth) throws SQLException;
    Person readPersonById(int id) throws SQLException;
    Person readPersonByName(String name) throws SQLException;
    void deletePerson(int id) throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePersonByName(String name) throws SQLException;
    List<Person> readAllPeople() throws SQLException;
}