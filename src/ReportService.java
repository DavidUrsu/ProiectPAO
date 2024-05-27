import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportService {
    private static ReportService instance;
    private static String fileName = "report.txt";

    private ReportService() {
    }

    private static void updateFILE_NAME(String newFileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORT_");
        sb.append(newFileName);
        // get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentDateTime.format(formatter);
        // append the current date and time to the file name
        sb.append(formattedDateTime);
        // append the file extension
        sb.append(".txt");

        fileName = sb.toString();
    }

    public static ReportService getInstance() {
        if (instance == null) {
            instance = new ReportService();
        }
        return instance;
    }

    public void databaseAudit(String operation) {
        updateFILE_NAME("database_audit");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(LocalDateTime.now() + " - " + operation);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the report file.");
            System.out.println();
            e.printStackTrace();
        }
    }

    public void bookDatabase(){
        updateFILE_NAME("book_database");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Library library = Library.getInstance();

            List<Book> books = library.getBookService().getAllBooks();
            StringBuilder builder = new StringBuilder();

            // sort the books based on title
            books.sort(new BookTitleComparator());
            builder.append("Books:\n");
            for (Book book : books) {
                Person author = library.getPersonService().getPersonById(book.getAuthorId());
                Boolean isBookBorrowed = library.getBorrowOperationService().isBookBorrowed(book.getId());

                builder.append("-------------------------\n");
                builder.append("Title: ").append(book.getTitle()).append("\n");
                builder.append("Book id: ").append(book.getId()).append("\n");
                builder.append("Author id: ").append(book.getAuthorId()).append("\n");
                builder.append("Author: ").append(author.getName()).append("\n");
                builder.append("Is book borrowed: ").append(isBookBorrowed).append("\n");
                builder.append("Is book destroyed: ").append(book.isDestroyed()).append("\n");
                builder.append("\n");
            }
            writer.write(builder.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the report file.");
            System.out.println();
            e.printStackTrace();
        }
    }
}