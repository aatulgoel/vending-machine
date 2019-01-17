package money;

public class Coin implements USCurrency {
    private String coinName;
    private int pennyValue;

    public Coin(String coinName, int pennyValue) {
        this.coinName = coinName;
        this.pennyValue = pennyValue;
    }

    public String currencyName() {
        return coinName;
    }

    public int pennyValue() {
        return pennyValue;
    }
}
