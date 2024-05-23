public class LibraryManagement {
    private Library library;

    public LibraryManagement() {
        this.library = Library.getInstance();
    }

    public void addBook(String title, Person author) {
        Book book = new Book(title, author);
        library.getBooks().add(book);
    }

    public void addUser(String name, int yearOfBirth) {
        User user = new User(name, yearOfBirth);
        library.getUsers().add(user);
    }

    public void borrowBook(User user, Book book) {
        user.borrowBook(book);
    }

    public void returnBook(User user, Book book) {
        user.returnBook(book);
    }

    public void destroyBook(Book book) {
        library.getBooks().remove(book);
        library.getDestroyedBooks().add(book);
    }
}