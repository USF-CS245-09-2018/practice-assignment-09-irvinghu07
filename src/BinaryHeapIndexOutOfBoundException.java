/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-09
 * Date: 2018-11-07
 */
public class BinaryHeapIndexOutOfBoundException extends RuntimeException {

    private static final String DEFAULT_ERROR_INSTRUCTION = "The binary heap instance is empty now, cannot operate remove()";

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BinaryHeapIndexOutOfBoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BinaryHeapIndexOutOfBoundException() {
        super(DEFAULT_ERROR_INSTRUCTION);
    }
}
