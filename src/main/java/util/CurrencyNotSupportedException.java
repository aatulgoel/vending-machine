package util;

public class CurrencyNotSupportedException extends RuntimeException {

    public CurrencyNotSupportedException(String transactionRef) {
        super(transactionRef);
    }

}
