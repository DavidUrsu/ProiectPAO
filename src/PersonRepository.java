import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements IPersonRepository {
    private static final String INSERT_PERSON_SQL = "INSERT INTO Person (Name, YearOfBirth) VALUES (?, ?)";
    private static final String SELECT_PERSON_SQL = "SELECT * FROM Person WHERE Id = ?";
    private static final String DELETE_PERSON_SQL = "DELETE FROM Person WHERE Id = ?";
    private static final String UPDATE_PERSON_SQL = "UPDATE Person SET Name = ?, YearOfBirth = ? WHERE Id = ?";
    private static final String SQL_DELETE_PERSON_BY_NAME = "DELETE FROM Person WHERE Name = ?";
    private static final String SQL_SELECT_ALL_PERSONS = "SELECT * FROM Person";

    public void createPerson(String name, int yearOfBirth) throws SQLException {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON_SQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, yearOfBirth);

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Person readPersonById(int id) throws SQLException {
        Person person = null;

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSON_SQL)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                int yearOfBirth = resultSet.getInt("YearOfBirth");

                person = new Person(id, name, yearOfBirth);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return person;
    }

    public Person readPersonByName(String name) throws SQLException {
        Person person = null;

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE Name = ?")) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int yearOfBirth = resultSet.getInt("YearOfBirth");

                person = new Person(id, name, yearOfBirth);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void deletePerson(int id) throws SQLException {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERSON_SQL)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person) {
        // Update the person in the database
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSON_SQL)) {

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getYearOfBirth());
            preparedStatement.setInt(3, person.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deletePersonByName(String name) {
        // Delete the person from the database
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PERSON_BY_NAME)) {

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Person> readAllPeople() throws SQLException {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PERSONS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                int yearOfBirth = resultSet.getInt("YearOfBirth");

                Person person = new Person(id, name, yearOfBirth);
                persons.add(person);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return persons;
    }
}