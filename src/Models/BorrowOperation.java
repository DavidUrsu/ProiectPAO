package Models;

import java.time.LocalDate;

public final class BorrowOperation extends AbstractEntity{
    private final LocalDate borrowDate;
    private final LocalDate returnDate;
    private final int clientId;
    private final int bookId;

    public BorrowOperation(int Id, LocalDate borrowDate, LocalDate returnDate, int clientId, int bookId) {
        super(Id);
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getClientId() {
        return clientId;
    }

    public int getBookId() {
        return bookId;
    }
}