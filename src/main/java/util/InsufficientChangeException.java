package util;

public class InsufficientChangeException extends RuntimeException {
    public InsufficientChangeException(String transactionRef) {
        super(transactionRef);
    }

}
