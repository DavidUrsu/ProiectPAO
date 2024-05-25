import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final IBookRepository bookRepository;
    private final IPersonRepository personRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = new PersonRepository();
    }

    public void createBook(String title, int authorId) {
        bookRepository.insertBook(title, authorId);
    }

    public void deleteBook(int id) {
        bookRepository.deleteBook(id);
    }

    public void modifyBook(int id, String newTitle, int newAuthorId) {
        // check if book exists
        try {
            Book book = bookRepository.selectBook(id);
            if (book == null) {
                throw new InvalidBookException("Book does not exist");
            }
            bookRepository.updateBook(new Book(id, newTitle, newAuthorId));
        } catch(InvalidBookException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.selectAllBooks();
    }

    public List<Book> getAllBooksByAuthor(int authorId) {
        try{
            Person author = personRepository.readPersonById(authorId);
            if (author == null) {
                throw new InvalidPersonException("Author does not exist");
            }
            return bookRepository.selectAllBooksByAuthor(authorId);
        } catch (InvalidPersonException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Book> getAllBooksByTitle(String title) {
        return bookRepository.selectAllBooksByTitle(title);
    }
}
