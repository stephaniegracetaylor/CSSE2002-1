package mms.exceptions;

/**
 * Exception thrown when the program attempts to add an items in the incorrect
 * order.
 */
public class PackingOrderException extends PackingException {
    /**
     * Constructs a normal PackingOrderException with no detail message.
     */
    public PackingOrderException(){}

    /**
     * Constructs a PackingOrderException that contains a helpful detail message
     * explaining why the exception occurred.
     * @param message detail message
     */
    public PackingOrderException(String message) {
        super(message);
    }
}