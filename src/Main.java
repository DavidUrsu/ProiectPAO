import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();

        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("Welcome to the Library Management System!");
            System.out.println("Enter command:");
            System.out.println("Person - to manage persons");
            System.out.println("Book - to manage books");
            System.out.println("Exit - to exit the program");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("Exit")) {
                break;
            } else if (command.equalsIgnoreCase("Person")) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (create, delete, modify, getAll):");
                String action = scanner.nextLine();

                try {
                    switch (action.toLowerCase()) {
                        case "create":
                            System.out.println("Enter name:");
                            String name = scanner.nextLine();

                            System.out.println("Enter year of birth:");
                            String stringYearOfBirth = scanner.nextLine();
                            // check if the year of birth is a number
                            if (!stringYearOfBirth.matches("\\d+")) {
                                System.out.println("Invalid year of birth. Try again.");
                                break;
                            }
                            int yearOfBirth = Integer.parseInt(stringYearOfBirth);

                            library.createPerson(name, yearOfBirth);
                            break;
                        case "delete":
                            System.out.println("Enter name:");
                            String deleteName = scanner.nextLine();
                            library.deletePerson(deleteName);
                            break;
                        case "modify":
                            System.out.println("Enter id:");
                            String stringId = scanner.nextLine();

                            // check if the id is a number
                            if (!stringId.matches("\\d+")) {
                                System.out.println("Invalid id. Try again.");
                                break;
                            }
                            int id = Integer.parseInt(stringId);

                            System.out.println("Enter new name:");
                            String newName = scanner.nextLine();

                            System.out.println("Enter new year of birth:");
                            String stringNewYearOfBirth = scanner.nextLine();
                            // check if the year of birth is a number
                            if (!stringNewYearOfBirth.matches("\\d+")) {
                                System.out.println("Invalid year of birth. Try again.");
                                break;
                            }
                            int newYearOfBirth = Integer.parseInt(stringNewYearOfBirth);

                            library.modifyPerson(id, newName, newYearOfBirth);
                            break;
                        case "getall":
                            System.out.println("All persons:");
                            for (Person person : library.getAllPersons()) {
                                System.out.println(person.getId() + "." + person.getName() + ", " + person.getYearOfBirth());
                            }
                            break;
                        default:
                            System.out.println("Invalid action. Try again.");
                            break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (command.equalsIgnoreCase("Book")) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (create, delete, modify, getAll, getByAuthor, getByTitle):");
                String action = scanner.nextLine();

                switch (action.toLowerCase()) {
                    case "create":
                        System.out.println("Enter title:");
                        String title = scanner.nextLine();

                        System.out.println("Enter author id:");
                        String stringAuthorId = scanner.nextLine();
                        // check if the author id is a number
                        if (!stringAuthorId.matches("\\d+")) {
                            System.out.println("Invalid author id. Try again.");
                            break;
                        }
                        int authorId = Integer.parseInt(stringAuthorId);

                        library.createBook(title, authorId);
                        break;
                    case "delete":
                        System.out.println("Enter id:");
                        String stringId = scanner.nextLine();
                        // check if the id is a number
                        if (!stringId.matches("\\d+")) {
                            System.out.println("Invalid id. Try again.");
                            break;
                        }
                        int id = Integer.parseInt(stringId);
                        library.deleteBook(id);
                        break;
                    case "modify":
                        System.out.println("Enter id:");
                        String stringBookId = scanner.nextLine();

                        // check if the id is a number
                        if (!stringBookId.matches("\\d+")) {
                            System.out.println("Invalid id. Try again.");
                            break;
                        }
                        int bookId = Integer.parseInt(stringBookId);

                        System.out.println("Enter new title:");
                        String newTitle = scanner.nextLine();

                        System.out.println("Enter new author id:");
                        String stringNewAuthorId = scanner.nextLine();
                        // check if the author id is a number
                        if (!stringNewAuthorId.matches("\\d+")) {
                            System.out.println("Invalid author id. Try again.");
                            break;
                        }
                        int newAuthorId = Integer.parseInt(stringNewAuthorId);

                        library.modifyBook(bookId, newTitle, newAuthorId);
                        break;
                    case "getall":
                        System.out.println("All books:");
                        for (Book book : library.getAllBooks()) {
                            System.out.println(book.getId() + "." + book.getTitle() + ", " + book.getAuthorId());
                        }
                        break;
                    case "getbyauthor":
                        System.out.println("Enter author id:");
                        String stringAuthorIdForSearch = scanner.nextLine();
                        // check if the author id is a number
                        if (!stringAuthorIdForSearch.matches("\\d+")) {
                            System.out.println("Invalid author id. Try again.");
                            break;
                        }
                        int authorIdForSearch = Integer.parseInt(stringAuthorIdForSearch);

                        System.out.println("Books by author:");
                        for (Book book : library.getAllBooksByAuthor(authorIdForSearch)) {
                            System.out.println(book.getId() + "." + book.getTitle() + ", " + book.getAuthorId());
                        }
                        break;
                    case "getbytitle":
                        System.out.println("Enter title:");
                        String titleForSearch = scanner.nextLine();

                        System.out.println("Books with title:");
                        for (Book book : library.getAllBooksByTitle(titleForSearch)) {
                            System.out.println(book.getId() + "." + book.getTitle() + ", " + book.getAuthorId());
                        }
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }

        scanner.close();
    }
}