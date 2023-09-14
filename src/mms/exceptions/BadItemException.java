package mms.exceptions;

/**
 * Exception thrown when the program attempts to add an item of the incorrect
 * implementing class of Packable to a Storage class.
 */
public class BadItemException extends PackingException {
    /**
     * Constructs a normal BadItemException with no detail message.
     */
    public BadItemException(){}

    /**
     * Constructs a BadItemException that contains a helpful detail message
     * explaining why the exception occurred.
     * @param message detail message
     */
    public BadItemException(String message) {
        super(message);
    }
}