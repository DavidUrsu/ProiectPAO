import java.util.List;

public interface IClientRepository {
    void addClient(Person person);
    void setClientActiveByBadgeNumber(int badgeNumber, boolean isActive);
    List<Client> getAllClients();
    Client getClientByBadgeNumber(int badgeNumber);
    Client getClientByName(String name);
    List<Client> getClientsByYearOfBirth(int yearOfBirth);
    List<Client> getClientsByActive(boolean isActive);
}
