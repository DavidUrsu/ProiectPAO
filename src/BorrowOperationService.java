import java.util.List;

public class BorrowOperationService implements IBorrowOperationService{
    private final IBorrowOperationRepository borrowOperationRepository;
    private final IClientRepository clientRepository;
    private final IBookRepository bookRepository;

    public BorrowOperationService(IBorrowOperationRepository borrowOperationRepository, IClientRepository clientRepository, IBookRepository bookRepository) {
        this.borrowOperationRepository = borrowOperationRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isBookBorrowed(int bookId) {
        return borrowOperationRepository.getClientOfBorrowByBookId(bookId) != 0;
    }

    @Override
    public void addBorrowOperation(int clientId, int bookId) {
        try{
            Client client = clientRepository.getClientById(clientId);
            if (client == null) {
                throw new InvalidClientException("Client does not exist");
            }
            Book book = bookRepository.selectBook(bookId);
            if (book == null) {
                throw new InvalidBookException("Book does not exist");
            }
            if (isBookBorrowed(bookId)) {
                throw new InvalidBookException("Book is already borrowed");
            }
            borrowOperationRepository.addBorrowOperation(clientId, bookId);
        } catch(InvalidClientException | InvalidBookException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<BorrowOperation> getAllBorrowsByClientId(int clientId) {
        try {
            if (clientRepository.getClientById(clientId) == null) {
                throw new InvalidClientException("Client does not exist");
            }
        } catch (InvalidClientException e) {
            System.out.println(e.getMessage());
        }
        return borrowOperationRepository.getAllBorrowsByClientId(clientId);
    }

    @Override
    public int getIdOfClientOfBorrowByBookId(int bookId) {
        try {
            if (bookRepository.selectBook(bookId) == null) {
                throw new InvalidBookException("Book does not exist");
            }
        } catch (InvalidBookException e) {
            System.out.println(e.getMessage());
        }
        return borrowOperationRepository.getClientOfBorrowByBookId(bookId);
    }

    @Override
    public void setReturnDateOnBorrow(int borrowId) {
        try {
            if (borrowOperationRepository.getBorrowById(borrowId) == null) {
                throw new InvalidBorrowOperationException("Borrow does not exist");
            }
            borrowOperationRepository.setReturnDateOnBorrow(borrowId);
        } catch (InvalidBorrowOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<BorrowOperation> getAllBorrows() {
        return borrowOperationRepository.getAllBorrows();
    }
}
