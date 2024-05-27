import java.util.List;

public interface IClientService {
    void addClient(String name);
    void setClientActiveByBadgeNumber(int badgeNumber, boolean isActive);
    List<Client> getAllClients();
    Client getClientByBadgeNumber(int badgeNumber);
    Client getClientByName(String name);
    List<Client> getClientsByYearOfBirth(int yearOfBirth);
    List<Client> getClientsByActive(boolean isActive);
    Client getClientById(int id);
}
