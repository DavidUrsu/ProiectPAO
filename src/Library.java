import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private PersonService personService;
    private BookService bookService;

    private Library() {
        personService = new PersonService(new PersonRepository());
        bookService = new BookService(new BookRepository());
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void createPerson(String name, int yearOfBirth) {
        personService.createPerson(name, yearOfBirth);
    }

    public void deletePerson(String name) {
        personService.deletePerson(name);
    }

    public void modifyPerson(int id, String newName, int newYearOfBirth) {
        personService.modifyPerson(id, newName, newYearOfBirth);
    }

    public List<Person> getAllPersons() throws SQLException {
        return personService.getAllPersons();
    }

    // Book methods
    public void createBook(String title, int authorId) {
        bookService.createBook(title, authorId);
    }

    public void deleteBook(int id) {
        bookService.deleteBook(id);
    }

    public void modifyBook(int id, String newTitle, int newAuthorId) {
        bookService.modifyBook(id, newTitle, newAuthorId);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public List<Book> getAllBooksByAuthor(int authorId) {
        return bookService.getAllBooksByAuthor(authorId);
    }

    public List<Book> getAllBooksByTitle(String title) {
        return bookService.getAllBooksByTitle(title);
    }
}
