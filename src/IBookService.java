import java.util.List;

public interface IBookService {
    void createBook(String title, int authorId);
    void deleteBook(int id);
    void modifyBook(int id, String newTitle, int newAuthorId);
    List<Book> getAllBooks();
    List<Book> getAllBooksByAuthor(int authorId);
    List<Book> getAllBooksByTitle(String title);
}
