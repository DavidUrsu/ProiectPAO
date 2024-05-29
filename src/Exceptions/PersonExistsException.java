package Exceptions;

public class PersonExistsException extends Exception {
    public PersonExistsException(String message) {
        super(message);
    }
}