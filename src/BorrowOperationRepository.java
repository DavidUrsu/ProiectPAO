import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowOperationRepository implements  IBorrowOperationRepository{
    private static final String INSERT_BORROW_OPERATION_SQL = "INSERT INTO BorrowOperation (BorrowDate, ClientId, BookId) VALUES (CURDATE(), ?, ?)";
    private static final String SELECT_ALL_BORROWS_BY_CLIENT_ID_SQL = "SELECT * FROM BorrowOperation WHERE ClientId = ?";;
    private static final String SELECT_USER_OF_BORROW_BY_BOOK_ID_SQL = "SELECT * FROM BorrowOperation WHERE BookId = ?";
    private static final String UPDATE_RETURN_DATE_SQL = "UPDATE BorrowOperation SET ReturnDate = CURDATE() WHERE Id = ?";
    private static final String SELECT_BORROW_BY_ID_SQL = "SELECT * FROM BorrowOperation WHERE Id = ?";

    @Override
    public void addBorrowOperation(int clientId, int bookId) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROW_OPERATION_SQL)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowOperation> getAllBorrowsByClientId(int clientId) {
        List<BorrowOperation> borrows = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BORROWS_BY_CLIENT_ID_SQL)) {
            preparedStatement.setInt(1, clientId);
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
            while (rs.next()) {
                int borrowId = rs.getInt("Id");
                LocalDate borrowDate = rs.getDate("BorrowDate").toLocalDate();
                LocalDate returnDate = rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null;
                int bookId = rs.getInt("BookId");

                borrows.add(new BorrowOperation(borrowId, borrowDate, returnDate, clientId, bookId));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public int getClientOfBorrowByBookId(int bookId){
        int clientId = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_OF_BORROW_BY_BOOK_ID_SQL)) {
            preparedStatement.setInt(1, bookId);
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
            while (rs.next()) {
                if (rs.getDate("ReturnDate") == null) {
                    clientId = rs.getInt("ClientId");
                    break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientId;
    }

    @Override
    public void setReturnDateOnBorrow(int borrowId) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RETURN_DATE_SQL)) {
            preparedStatement.setInt(1, borrowId);
            preparedStatement.executeUpdate();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BorrowOperation getBorrowById(int borrowId) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROW_BY_ID_SQL)) {
            preparedStatement.setInt(1, borrowId);
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
            if (rs.next()) {
                LocalDate borrowDate = rs.getDate("BorrowDate").toLocalDate();
                LocalDate returnDate = rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null;
                int clientId = rs.getInt("ClientId");
                int bookId = rs.getInt("BookId");

                return new BorrowOperation(borrowId, borrowDate, returnDate, clientId, bookId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BorrowOperation> getAllBorrows() {
        List<BorrowOperation> borrows = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BorrowOperation")) {
            ResultSet rs = preparedStatement.executeQuery();
            ReportService.getInstance().databaseAudit(preparedStatement.toString());
            while (rs.next()) {
                int borrowId = rs.getInt("Id");
                LocalDate borrowDate = rs.getDate("BorrowDate").toLocalDate();
                LocalDate returnDate = rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null;
                int clientId = rs.getInt("ClientId");
                int bookId = rs.getInt("BookId");

                borrows.add(new BorrowOperation(borrowId, borrowDate, returnDate, clientId, bookId));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return borrows;
    }
}
