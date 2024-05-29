package Services;

import Models.Book;

import java.util.List;

public interface IBookService {
    void createBook(String title, int authorId);
    void destroyBook(int id);
    void modifyBook(int id, String newTitle, int newAuthorId);
    List<Book> getAllBooks();
    List<Book> getAllBooksByAuthor(int authorId);
    List<Book> getAllBooksByTitle(String title);
    Book getBookById(int id);
}
