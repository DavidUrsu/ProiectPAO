import java.util.List;

public interface IBookRepository {
    void insertBook(String title, int authorId);
    Book selectBook(int id);
    void destroyBook(int id);
    void updateBook(int id, int authorId, String title);
    List<Book> selectAllBooks();
    List<Book> selectAllBooksByAuthor(int authorId);
    List<Book> selectAllBooksByTitle(String title);
}
