package Services;

import Models.BorrowOperation;

import java.util.List;

public interface IBorrowOperationService {
    boolean isBookBorrowed(int bookId);
    void addBorrowOperation(int clientId, int bookId);
    List<BorrowOperation> getAllBorrowsByClientId(int clientId);
    int getIdOfClientOfBorrowByBookId(int bookId);
    void setReturnDateOnBorrow(int borrowId);
    List<BorrowOperation> getAllBorrows();
}
