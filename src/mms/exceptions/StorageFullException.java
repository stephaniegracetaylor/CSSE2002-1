package mms.exceptions;

/**
 * Exception thrown when the program attempts to add more items to storage than
 * that particular storage unit can handle.
 */
public class StorageFullException extends PackingException {
    /**
     * Constructs a normal StorageFullException with no detail message.
     */
    public StorageFullException(){}

    /**
     * Constructs a StorageFullException that contains a helpful detail message
     * explaining why the exception occurred.
     * @param message detail message
     */
    public StorageFullException(String message) {
        super(message);
    }
}