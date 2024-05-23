import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> books;
    private List<User> users;
    private List<Book> destroyedBooks;

    private Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Book> getDestroyedBooks() {
        return destroyedBooks;
    }
}
