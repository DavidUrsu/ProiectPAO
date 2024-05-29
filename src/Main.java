import Models.Library;
import Services.ReportService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();

        while ( true ) {
            System.out.println("----------------------------------------");
            System.out.println("Welcome to the Library Management System!");
            System.out.println("Enter command:");
            System.out.println("Person - to manage persons");
            System.out.println("Book - to manage books");
            System.out.println("Client - to manage clients");
            System.out.println("Borrow - to manage borrow operations");
            System.out.println("Logs - to create logs");
            System.out.println("Exit - to exit the program");
            String command = scanner.nextLine();

            if ( command.equalsIgnoreCase("Exit") ) {
                break;
            } else if ( command.equalsIgnoreCase("Person") ) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (create, delete, modify, getById, getByName, getAll):");
                String action = scanner.nextLine();

                switch ( action.toLowerCase() ) {
                    case "create":
                        library.personCreate(scanner);
                        break;
                    case "delete":
                        library.personDelete(scanner);
                        break;
                    case "modify":
                        library.personModify(scanner);
                        break;
                    case "getbyid":
                        library.personGetbyid(scanner);
                        break;
                    case "getbyname":
                        library.personGetbyname(scanner);
                        break;
                    case "getall":
                        library.personGetall();
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } else if ( command.equalsIgnoreCase("Book") ) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (create, destroy, modify, getAll, getByAuthor, getByTitle):");
                String action = scanner.nextLine();

                switch ( action.toLowerCase() ) {
                    case "create":
                        library.bookCreate(scanner);
                        break;
                    case "destroy":
                        library.bookDestroy(scanner);
                        break;
                    case "modify":
                        library.bookModify(scanner);
                        break;
                    case "getall":
                        library.bookGetall();
                        break;
                    case "getbyauthor":
                        library.bookGetbyauthor(scanner);
                        break;
                    case "getbytitle":
                        library.bookGetbytitle(scanner);
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } else if ( command.equalsIgnoreCase("Client") ) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (create, setActive, getAll, getByBadgeNumber, getByName, getByYearOfBirth, getByActive):");
                String action = scanner.nextLine();

                switch ( action.toLowerCase() ) {
                    case "create":
                        library.clientAddclient(scanner);
                        break;
                    case "setactive":
                        library.clientSetactive(scanner);
                        break;
                    case "getall":
                        library.clientGetall();
                        break;
                    case "getbybadgenumber":
                        library.clientGetbybadgenumber(scanner);
                        break;
                    case "getbyname":
                        library.clientGetbyname(scanner);
                        break;
                    case "getbyyearofbirth":
                        library.clientGetbyyearofbirth(scanner);
                        break;
                    case "getbyactive":
                        library.clientGetbyactive(scanner);
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } else if ( command.equalsIgnoreCase("Borrow") ) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (isBorrowed, add, getAllByClient, getClientByBook, setReturnDate, getAll):");
                String action = scanner.nextLine();

                switch ( action.toLowerCase() ) {
                    case "isborrowed":
                        library.borrowIsborrowed(scanner);
                        break;
                    case "add":
                        library.borrowAdd(scanner);
                        break;
                    case "getallbyclient":
                        library.borrowGetallbyclient(scanner);
                        break;
                    case "getclientbybook":
                        library.borrowGetclientbybook(scanner);
                        break;
                    case "setreturndate":
                        library.borrowSetreturndate(scanner);
                        break;
                    case "getall":
                        library.borrowGetall();
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } else if ( command.equalsIgnoreCase("Logs") ) {
                System.out.println("----------------------------------------");
                System.out.println("Enter action (booksLog):");
                String action = scanner.nextLine();

                switch ( action.toLowerCase() ) {
                    case "bookslog":
                        ReportService.getInstance().bookDatabase();
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } else {
                System.out.println("Invalid command. Try again.");
            }
            // wait for one enter press
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }
        scanner.close();
    }
}