import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IClientRepository {

    @Override
    public void addClient(Person person) {
        String sql = "INSERT INTO Client (PersonId, IsActive) VALUES (?, TRUE)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, person.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setClientActiveByBadgeNumber(int badgeNumber, boolean isActive) {
        String sql = "UPDATE Client SET IsActive = ? WHERE BadgeNumber = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isActive);
            statement.setInt(2, badgeNumber);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client INNER JOIN Person ON Client.PersonId = Person.Id;";
        try (Connection connection = Database.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("PersonId"), resultSet.getString("Name"), resultSet.getInt("YearOfBirth"), resultSet.getBoolean("IsActive"), resultSet.getInt("BadgeNumber"));
                clients.add(client);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getClientByBadgeNumber(int badgeNumber) {
        String sql = "SELECT * FROM Client INNER JOIN Person ON Client.PersonId = Person.Id WHERE Client.BadgeNumber = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, badgeNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(resultSet.getInt("PersonId"), resultSet.getString("Name"), resultSet.getInt("YearOfBirth"), resultSet.getBoolean("IsActive"), resultSet.getInt("BadgeNumber"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getClientByName(String name) {
        Client client = null;
        String sqlGetThePerson = "SELECT * FROM Person WHERE Name = ?";
        String sql = "SELECT * FROM Client WHERE PersonId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlGetThePerson)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                try (PreparedStatement statement2 = connection.prepareStatement(sql)) {
                    statement2.setInt(1, resultSet.getInt("Id"));
                    ResultSet resultSet2 = statement2.executeQuery();
                    if (resultSet2.next()) {
                        client = new Client(resultSet2.getInt("PersonId"), resultSet.getString("Name"), resultSet.getInt("YearOfBirth"), resultSet2.getBoolean("IsActive"), resultSet2.getInt("BadgeNumber"));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client getClientById(int id) {
        String sql = "SELECT * FROM Client INNER JOIN Person ON Client.PersonId = Person.Id WHERE Client.PersonId = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(resultSet.getInt("PersonId"), resultSet.getString("Name"), resultSet.getInt("YearOfBirth"), resultSet.getBoolean("IsActive"), resultSet.getInt("BadgeNumber"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getClientsByYearOfBirth(int yearOfBirth) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client INNER JOIN Person ON Client.PersonId = Person.Id WHERE Person.YearOfBirth = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, yearOfBirth);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("PersonId"), resultSet.getString("Name"), resultSet.getInt("YearOfBirth"), resultSet.getBoolean("IsActive"), resultSet.getInt("BadgeNumber"));
                clients.add(client);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public List<Client> getClientsByActive(boolean isActive) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client INNER JOIN Person ON Client.PersonId = Person.Id WHERE Client.IsActive = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isActive);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt("PersonId"), resultSet.getString("Name"), resultSet.getInt("YearOfBirth"), resultSet.getBoolean("IsActive"), resultSet.getInt("BadgeNumber"));
                client.setActive(resultSet.getBoolean("IsActive"));
                clients.add(client);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }
}