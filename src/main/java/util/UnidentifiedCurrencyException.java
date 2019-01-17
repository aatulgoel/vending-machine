package util;

public class UnidentifiedCurrencyException extends RuntimeException {
    public UnidentifiedCurrencyException(String transactionRef) {
        super(transactionRef);
    }

}
