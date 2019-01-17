package money;

public class Bill implements USCurrency {
    private String billName;
    private int billValue;
    private int pennyValue;

    public Bill(String billName, int billValue, int pennyValue) {
        this.billName = billName;
        this.billValue = billValue;
        this.pennyValue = pennyValue;

    }

    public int getBillValue() {
        return billValue;
    }

    public String currencyName() {
        return billName;
    }

    public int pennyValue() {
        return pennyValue;
    }


}
