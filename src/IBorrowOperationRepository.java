import java.util.List;

public interface IBorrowOperationRepository {
    void addBorrowOperation(int clientId, int bookId);
    List<BorrowOperation> getAllBorrowsByClientId(int clientId);
    int getClientOfBorrowByBookId(int bookId);
    void setReturnDateOnBorrow(int borrowId);
    BorrowOperation getBorrowById(int borrowId);
    List<BorrowOperation> getAllBorrows();
}
