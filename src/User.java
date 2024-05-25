import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private List<Book> borrowedBooks;

    public User(int id, String name, int yearOfBirth) {
        super(id, name, yearOfBirth);
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
