import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private static final String INSERT_BOOK_SQL = "INSERT INTO Book (Title, AuthorId) VALUES (?, ?)";
    private static final String SELECT_BOOK_SQL = "SELECT * FROM Book WHERE Id = ?";
    private static final String DESTROY_BOOK_SQL = "UPDATE Book SET IsDestroyed = 1 WHERE Id = ?";
    private static final String UPDATE_BOOK_SQL = "UPDATE Book SET Title = ?, AuthorId = ? WHERE Id = ?";
    private static final String SELECT_ALL_BOOKS_SQL = "SELECT * FROM Book";
    private static final String SELECT_ALL_BOOKS_BY_AUTHOR_SQL = "SELECT * FROM Book WHERE AuthorId = ?";
    private static final String SELECT_ALL_BOOKS_BY_TITLE_SQL = "SELECT * FROM Book WHERE Title = ?";

    @Override
    public void insertBook(String title, int authorId) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, authorId);
            preparedStatement.executeUpdate();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book selectBook(int id) {
        Book book = null;
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());

            while (rs.next()) {
                int bookId = rs.getInt("Id");
                String title = rs.getString("Title");
                int authorId = rs.getInt("AuthorId");
                boolean isDestroyed = rs.getBoolean("IsDestroyed");
                book = new Book(bookId, title, authorId, isDestroyed);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void destroyBook(int id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DESTROY_BOOK_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(int id, int authorId, String title) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, authorId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> selectAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_SQL);
             ResultSet rs = preparedStatement.executeQuery()) {
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
            while (rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                int authorId = rs.getInt("AuthorId");
                boolean isDestroyed = rs.getBoolean("IsDestroyed");
                books.add(new Book(id, title, authorId, isDestroyed));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> selectAllBooksByAuthor(int authorId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_BY_AUTHOR_SQL)) {
            preparedStatement.setInt(1, authorId);
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());

            while (rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                boolean isDestroyed = rs.getBoolean("IsDestroyed");
                books.add(new Book(id, title, authorId, isDestroyed));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> selectAllBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_BY_TITLE_SQL)) {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());

            while (rs.next()) {
                int id = rs.getInt("Id");
                int authorId = rs.getInt("AuthorId");
                boolean isDestroyed = rs.getBoolean("IsDestroyed");
                books.add(new Book(id, title, authorId, isDestroyed));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }
}