package mms.exceptions;

/**
 * Exception thrown when an error occurs during the packing of Storage class.
 */
public class PackingException extends Exception {
    /**
     * Constructs a normal PackingException with no detail message.
     */
    public PackingException(){}

    /**
     * Constructs a PackingException that contains a helpful detail message
     * explaining why the exception occurred.
     * @param message detail message
     */
    public PackingException(String message) {
        super(message);
    }
}