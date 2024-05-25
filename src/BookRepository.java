import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String INSERT_BOOK_SQL = "INSERT INTO Book (Id, Title, AuthorId) VALUES (?, ?, ?)";
    private static final String SELECT_BOOK_SQL = "SELECT * FROM Book WHERE Id = ?";
    private static final String DELETE_BOOK_SQL = "DELETE FROM Book WHERE Id = ?";


}