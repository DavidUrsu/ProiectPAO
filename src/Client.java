import java.time.LocalDate;
import java.util.List;

public class Client extends Person{
    private static final int MAX_BORROWED_BOOKS = 5;

    private List<Book> borrowedBooks;
    private List<Book> borrowedBooksHistory;
    private final int badgeNumber;
    private boolean isActive;

    public Client(int id, String name, int yearOfBirth, boolean isActive, int badgeNumber) {
        super(id, name, yearOfBirth);
        this.badgeNumber = badgeNumber;
        this.isActive = isActive;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<Book> getBorrowedBooksHistory() {
        return borrowedBooksHistory;
    }

    public void setBorrowedBooksHistory(List<Book> borrowedBooksHistory) {
        this.borrowedBooksHistory = borrowedBooksHistory;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void borrowBook(Book book) {
        if (this.borrowedBooks.size() >= MAX_BORROWED_BOOKS) {
            System.out.println("Borrow limit reached. Return a book before borrowing another one.");
            return;
        }
        this.borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        this.borrowedBooks.remove(book);
        this.borrowedBooksHistory.add(book);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
