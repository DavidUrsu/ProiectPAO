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
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }

        scanner.close();
    }
}