import java.util.List;

public interface IBookRepository {
    void insertBook(String title, int authorId);
    Book selectBook(int id);
    void deleteBook(int id);
    void updateBook(Book book);
    List<Book> selectAllBooks();
    List<Book> selectAllBooksByAuthor(int authorId);
    List<Book> selectAllBooksByTitle(String title);
}
