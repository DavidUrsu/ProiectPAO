import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/LibraryDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}