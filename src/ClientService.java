import java.sql.SQLException;
import java.util.List;

public class ClientService implements IClientService{
    private final IClientRepository clientRepository;
    private final IPersonRepository personRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = new PersonRepository();
    }

    @Override
    public void addClient(String name) {
        try {
            // check if the person exists
            if (personRepository.readPersonByName(name) == null) {
                System.out.println("Person does not exist.");
                throw new InvalidClientException("Person does not exist.");
            }
            // check is the person is a client
            if (clientRepository.getClientByName(name) != null) {
                System.out.println("Person is already a client.");
                throw new InvalidClientException("Person is already a client.");
            }
            clientRepository.addClient(personRepository.readPersonByName(name));
        } catch (InvalidClientException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setClientActiveByBadgeNumber(int badgeNumber, boolean isActive) {
        // check if the client exists
        try {
            if (clientRepository.getClientByBadgeNumber(badgeNumber) == null) {
                System.out.println("Client does not exist.");
                throw new InvalidClientException("Client does not exist.");
            }
            clientRepository.setClientActiveByBadgeNumber(badgeNumber, isActive);
        } catch (InvalidClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    @Override
    public Client getClientByBadgeNumber(int badgeNumber) {
        // check if the client exists
        try {
            if (clientRepository.getClientByBadgeNumber(badgeNumber) == null) {
                System.out.println("Client does not exist.");
                throw new InvalidClientException("Client does not exist.");
            }
            return clientRepository.getClientByBadgeNumber(badgeNumber);
        } catch (InvalidClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client getClientByName(String name) {
        // check if the client exists
        try {
            if (clientRepository.getClientByName(name) == null) {
                System.out.println("Client does not exist.");
                throw new InvalidClientException("Client does not exist.");
            }
            return clientRepository.getClientByName(name);
        } catch (InvalidClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getClientsByYearOfBirth(int yearOfBirth) {
        return clientRepository.getClientsByYearOfBirth(yearOfBirth);
    }

    @Override
    public List<Client> getClientsByActive(boolean isActive) {
        return clientRepository.getClientsByActive(isActive);
    }

    @Override
    public Client getClientById(int id) {
        // check if the client exists
        try {
            if (clientRepository.getClientById(id) == null) {
                System.out.println("Client does not exist.");
                throw new InvalidClientException("Client does not exist.");
            }
            return clientRepository.getClientById(id);
        } catch (InvalidClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
