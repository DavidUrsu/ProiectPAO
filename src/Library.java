import java.util.List;
import java.util.Scanner;

public class Library {
    private static Library instance;
    private final PersonService personService;
    private final BookService bookService;
    private final ClientService clientService;
    private final BorrowOperationService borrowOperationService;

    private Library() {
        personService = new PersonService(new PersonRepository());
        bookService = new BookService(new BookRepository());
        clientService = new ClientService(new ClientRepository());
        borrowOperationService = new BorrowOperationService(new BorrowOperationRepository(), new ClientRepository(), new BookRepository());
    }

    public static Library getInstance(){
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Read methods

    private int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private boolean readBoolean(Scanner scanner) {
        while (true) {
            try {
                return Boolean.parseBoolean(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid boolean.");
            }
        }
    }

    // Person methods

    public void createPerson(String name, int yearOfBirth) {
        personService.createPerson(name, yearOfBirth);
    }

    public void personCreate(Scanner scanner){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the year of birth:");
        int yearOfBirth = readInt(scanner);
        createPerson(name, yearOfBirth);
    }

    public void deletePerson(String name) {
        personService.deletePerson(name);
    }

    public void personDelete(Scanner scanner){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        deletePerson(name);
    }

    public void modifyPerson(int id, String newName, int newYearOfBirth) {
        personService.modifyPerson(id, newName, newYearOfBirth);
    }

    public void personModify(Scanner scanner){
        System.out.println("Enter the id:");
        int id = readInt(scanner);
        System.out.println("Enter the new name:");
        String newName = scanner.nextLine();
        System.out.println("Enter the new year of birth:");
        int newYearOfBirth = readInt(scanner);
        modifyPerson(id, newName, newYearOfBirth);
    }

    public void getPersonByName(String name) {
        Person person = personService.getPersonByName(name);
        StringBuilder builder = new StringBuilder();

        if (person == null) {
            builder.append("No person found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Person:\n");
        builder.append("-------------------------\n");
        builder.append("Name: ").append(person.getName()).append("\n");
        builder.append("Id: ").append(person.getId()).append("\n");
        builder.append("Year of birth: ").append(person.getYearOfBirth()).append("\n");

        System.out.println(builder.toString());
    }

    public void personGetbyname(Scanner scanner){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        getPersonByName(name);
    }

    public void getPersonById(int id) {
        Person person = personService.getPersonById(id);
        StringBuilder builder = new StringBuilder();

        if (person == null) {
            builder.append("No person found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Person:\n");
        builder.append("-------------------------\n");
        builder.append("Name: ").append(person.getName()).append("\n");
        builder.append("Id: ").append(person.getId()).append("\n");
        builder.append("Year of birth: ").append(person.getYearOfBirth()).append("\n");

        System.out.println(builder.toString());
    }

    public void personGetbyid(Scanner scanner){
        System.out.println("Enter the id:");
        int id = readInt(scanner);
        getPersonById(id);
    }

    public void showAllPeople() {
        List<Person> persons = personService.getAllPersons();
        StringBuilder builder = new StringBuilder();

        if (persons.isEmpty()) {
            builder.append("No persons found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Persons:\n");
        for (Person person : persons) {
            builder.append("-------------------------\n");
            builder.append("Name: ").append(person.getName()).append("\n");
            builder.append("Id: ").append(person.getId()).append("\n");
            builder.append("Year of birth: ").append(person.getYearOfBirth()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void personGetall(){
        showAllPeople();
    }

    // Book methods
    public void createBook(String title, int authorId) {
        bookService.createBook(title, authorId);
    }

    public void bookCreate(Scanner scanner){
        System.out.println("Enter the title:");
        String title = scanner.nextLine();
        System.out.println("Enter the author id:");
        int authorId = readInt(scanner);
        createBook(title, authorId);
    }

    public void destroyBook(int id) {
        bookService.destroyBook(id);
    }

    public void bookDestroy(Scanner scanner){
        System.out.println("Enter the book id:");
        int id = readInt(scanner);
        destroyBook(id);
    }

    public void modifyBook(int id, String newTitle, int newAuthorId) {
        bookService.modifyBook(id, newTitle, newAuthorId);
    }

    public void bookModify(Scanner scanner){
        System.out.println("Enter the book id:");
        int id = readInt(scanner);
        System.out.println("Enter the new title:");
        String newTitle = scanner.nextLine();
        System.out.println("Enter the new author id:");
        int newAuthorId = readInt(scanner);
        modifyBook(id, newTitle, newAuthorId);
    }

    public void showAllBooks() {
        List<Book> books = bookService.getAllBooks();
        StringBuilder builder = new StringBuilder();

        if (books.isEmpty()) {
            builder.append("No books found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Books:\n");
        for (Book book : books) {
            Person author = personService.getPersonById(book.getAuthorId());
            Boolean isBookBorrowed = borrowOperationService.isBookBorrowed(book.getId());

            builder.append("-------------------------\n");
            builder.append("Title: ").append(book.getTitle()).append("\n");
            builder.append("Book id: ").append(book.getId()).append("\n");
            builder.append("Author id: ").append(book.getAuthorId()).append("\n");
            builder.append("Author: ").append(author.getName()).append("\n");
            builder.append("Is book borrowed: ").append(isBookBorrowed).append("\n");
            builder.append("Is book destroyed: ").append(book.isDestroyed()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void bookGetall(){
        showAllBooks();
    }

    public void showAllBooksByAuthor(int authorId){
        List<Book> books = bookService.getAllBooksByAuthor(authorId);
        StringBuilder builder = new StringBuilder();

        if (books.isEmpty()) {
            builder.append("No books found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Books:\n");
        for (Book book : books) {
            Person author = personService.getPersonById(book.getAuthorId());
            Boolean isBookBorrowed = borrowOperationService.isBookBorrowed(book.getId());

            builder.append("-------------------------\n");
            builder.append("Title: ").append(book.getTitle()).append("\n");
            builder.append("Book id: ").append(book.getId()).append("\n");
            builder.append("Author id: ").append(book.getAuthorId()).append("\n");
            builder.append("Author: ").append(author.getName()).append("\n");
            builder.append("Is book borrowed: ").append(isBookBorrowed).append("\n");
            builder.append("Is book destroyed: ").append(book.isDestroyed()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void bookGetbyauthor(Scanner scanner){
        System.out.println("Enter the author id:");
        int authorId = readInt(scanner);
        showAllBooksByAuthor(authorId);
    }

    public void showAllBooksByTitle(String title) {
        List<Book> books = bookService.getAllBooksByTitle(title);
        StringBuilder builder = new StringBuilder();

        if (books.isEmpty()) {
            builder.append("No books found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Books:\n");
        for (Book book : books) {
            Person author = personService.getPersonById(book.getAuthorId());
            Boolean isBookBorrowed = borrowOperationService.isBookBorrowed(book.getId());

            builder.append("-------------------------\n");
            builder.append("Title: ").append(book.getTitle()).append("\n");
            builder.append("Book id: ").append(book.getId()).append("\n");
            builder.append("Author id: ").append(book.getAuthorId()).append("\n");
            builder.append("Author: ").append(author.getName()).append("\n");
            builder.append("Is book borrowed: ").append(isBookBorrowed).append("\n");
            builder.append("Is book destroyed: ").append(book.isDestroyed()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void bookGetbytitle(Scanner scanner){
        System.out.println("Enter the title:");
        String title = scanner.nextLine();
        showAllBooksByTitle(title);
    }

    // Client methods
    public void addClient(String name) {
        clientService.addClient(name);
    }

    public void clientAddclient(Scanner scanner){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        addClient(name);
    }

    public void setClientActiveByBadgeNumber(int badgeNumber, boolean isActive) {
        clientService.setClientActiveByBadgeNumber(badgeNumber, isActive);
    }

    public void clientSetactive(Scanner scanner){
        System.out.println("Enter the badge number:");
        int badgeNumber = readInt(scanner);
        System.out.println("Enter the active status (true/false):");
        boolean isActive = readBoolean(scanner);
        setClientActiveByBadgeNumber(badgeNumber, isActive);
    }

    public void showAllClients() {
        List<Client> clients = clientService.getAllClients();
        StringBuilder builder = new StringBuilder();

        if (clients.isEmpty()) {
            builder.append("No clients found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Clients:\n");
        for (Client client : clients) {
            builder.append("-------------------------\n");
            builder.append("Name: ").append(client.getName()).append("\n");
            builder.append("Person Id: ").append(client.getId()).append("\n");
            builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
            builder.append("Active: ").append(client.isActive()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void clientGetall() {
        showAllClients();
    }

    public void showClientByBadgeNumber(int badgeNumber) {
        Client client = clientService.getClientByBadgeNumber(badgeNumber);
        StringBuilder builder = new StringBuilder();

        if (client == null) {
            builder.append("No client found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Client:\n");
        builder.append("-------------------------\n");
        builder.append("Name: ").append(client.getName()).append("\n");
        builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
        builder.append("Active: ").append(client.isActive()).append("\n");

        System.out.println(builder.toString());
    }

    public void clientGetbybadgenumber(Scanner scanner){
        System.out.println("Enter the badge number:");
        int badgeNumber = readInt(scanner);
        showClientByBadgeNumber(badgeNumber);
    }

    public void showClientByName(String name) {
        Client client = clientService.getClientByName(name);
        StringBuilder builder = new StringBuilder();

        if (client == null) {
            builder.append("No client found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Client:\n");
        builder.append("-------------------------\n");
        builder.append("Name: ").append(client.getName()).append("\n");
        builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
        builder.append("Active: ").append(client.isActive()).append("\n");

        System.out.println(builder.toString());
    }

    public void clientGetbyname(Scanner scanner){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        showClientByName(name);
    }

    public void showClientsByYearOfBirth(int yearOfBirth) {
        List<Client> clients = clientService.getClientsByYearOfBirth(yearOfBirth);
        StringBuilder builder = new StringBuilder();

        if (clients.isEmpty()) {
            builder.append("No clients found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Clients:\n");
        for (Client client : clients) {
            builder.append("-------------------------\n");
            builder.append("Name: ").append(client.getName()).append("\n");
            builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
            builder.append("Active: ").append(client.isActive()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void clientGetbyyearofbirth(Scanner scanner){
        System.out.println("Enter the year of birth:");
        int yearOfBirth = readInt(scanner);
        showClientsByYearOfBirth(yearOfBirth);
    }

    public void clientGetbyactive(Scanner scanner){
        System.out.println("Enter the active status (true/false):");
        boolean isActive = readBoolean(scanner);
        showClientsByActive(isActive);
    }

    public void showClientsByActive(boolean isActive) {
        List<Client> clients = clientService.getClientsByActive(isActive);
        StringBuilder builder = new StringBuilder();

        if (clients.isEmpty()) {
            builder.append("No clients found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("Clients:\n");
        for (Client client : clients) {
            builder.append("-------------------------\n");
            builder.append("Name: ").append(client.getName()).append("\n");
            builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
            builder.append("Active: ").append(client.isActive()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    // BorrowOperation methods
    public void isBookBorrowed(int bookId) {
        Book book = bookService.getBookById(bookId);
        StringBuilder builder = new StringBuilder();

        if (book == null) {
            return ;
        }

        if (borrowOperationService.isBookBorrowed(bookId)) {
            builder.append("The book is borrowed.");
        } else {
            builder.append("The book is not borrowed.");
        }

        System.out.println(builder.toString());
    }

    public void borrowIsborrowed(Scanner scanner){
        System.out.println("Enter the book id:");
        int bookId = readInt(scanner);
        isBookBorrowed(bookId);
    }

    public void addBorrowOperation(int clientId, int bookId) {
        borrowOperationService.addBorrowOperation(clientId, bookId);
    }

    public void borrowAdd(Scanner scanner){
        System.out.println("Enter the client id:");
        int clientId = readInt(scanner);
        System.out.println("Enter the book id:");
        int bookId = readInt(scanner);
        addBorrowOperation(clientId, bookId);
    }

    public void showAllBorrowsByClientId(int clientId) {
        List<BorrowOperation> borrowOperations = borrowOperationService.getAllBorrowsByClientId(clientId);
        StringBuilder builder = new StringBuilder();

        // if the client has no borrows
        if (borrowOperations.isEmpty()) {
            builder.append("No borrows found for this client.");
            System.out.println(builder.toString());
            return;
        }

        Client client = clientService.getClientById(borrowOperations.getFirst().getClientId());

        builder.append("Client: ").append(client.getName()).append("\n");
        builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
        builder.append("Borrows:\n");
        builder.append("-------------------------\n");
        for (BorrowOperation borrowOperation : borrowOperations) {
            Book book = bookService.getBookById(borrowOperation.getBookId());

            builder.append("Book: ").append(book.getTitle()).append("\n");
            builder.append("Borrow date: ").append(borrowOperation.getBorrowDate()).append("\n");
            builder.append("Return date: ").append(borrowOperation.getReturnDate()).append("\n");
            builder.append("Borrow number: ").append(borrowOperation.getId()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void borrowGetallbyclient(Scanner scanner){
        System.out.println("Enter the client id:");
        int clientId = readInt(scanner);
        showAllBorrowsByClientId(clientId);
    }

    public void showClientOfBorrowByBookId(int bookId) {
        StringBuilder builder = new StringBuilder();
        int clientId = borrowOperationService.getIdOfClientOfBorrowByBookId(bookId);
        if (clientId == 0) {
            builder.append("No borrows found for this book.");
            System.out.println(builder.toString());
            return;
        }

        Client client = clientService.getClientById(clientId);

        builder.append("Client: ").append(client.getName()).append("\n");
        builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");

        System.out.println(builder.toString());
    }

    public void borrowGetclientbybook(Scanner scanner){
        System.out.println("Enter the book id:");
        int bookId = readInt(scanner);
        showClientOfBorrowByBookId(bookId);
    }

    public void setReturnDateOnBorrow(int borrowId) {
        borrowOperationService.setReturnDateOnBorrow(borrowId);
    }

    public void borrowSetreturndate(Scanner scanner){
        System.out.println("Enter the borrow number:");
        int borrowId = readInt(scanner);
        setReturnDateOnBorrow(borrowId);
    }

    public void showAllBorrows() {
        List<BorrowOperation> borrowOperations = borrowOperationService.getAllBorrows();
        StringBuilder builder = new StringBuilder();

        // if there are no borrows
        if (borrowOperations.isEmpty()) {
            builder.append("No borrows found.");
            System.out.println(builder.toString());
            return;
        }

        builder.append("All borrows:\n");
        for (BorrowOperation borrowOperation : borrowOperations) {
            Client client = clientService.getClientById(borrowOperation.getClientId());
            Book book = bookService.getBookById(borrowOperation.getBookId());

            builder.append("-------------------------\n");
            builder.append("Client: ").append(client.getName()).append("\n");
            builder.append("Badge number: ").append(client.getBadgeNumber()).append("\n");
            builder.append("Book: ").append(book.getTitle()).append("\n");
            builder.append("Book id: ").append(book.getId()).append("\n");
            builder.append("Borrow date: ").append(borrowOperation.getBorrowDate()).append("\n");
            builder.append("Return date: ").append(borrowOperation.getReturnDate()).append("\n");
            builder.append("Borrow number: ").append(borrowOperation.getId()).append("\n");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void borrowGetall(){
        showAllBorrows();
    }
}
