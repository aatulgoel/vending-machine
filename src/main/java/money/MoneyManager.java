package money;


import javafx.util.Pair;
import util.CurrencyNotSupportedException;
import util.InsufficientFundsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyManager extends HashMap<USCurrency, Integer> {

    private static MoneyManager moneyWallet;

    private MoneyManager() {
        initialize();
    }

    public static synchronized MoneyManager getInstance() {
        if (moneyWallet == null) {
            moneyWallet = new MoneyManager();
        }
        return moneyWallet;
    }

    private void initialize() {
        this.put(Money.PENNY, 10000);
        this.put(Money.NICKEL, 10000);
        this.put(Money.DIME, 10000);
        this.put(Money.QUARTER, 5000);
        this.put(Money.DOLLAR, 100);
        this.put(Money.TWO_DOLLAR, 100);
        this.put(Money.FIVE_DOLLAR, 50);
        this.put(Money.TEN_DOLLAR, 20);
        this.put(Money.TWENTY_DOLLAR, 10);
        this.put(Money.FIFTY_DOLLAR, 0);
        this.put(Money.HUNDRED_DOLLAR, 0);
    }


    public Pair<String, String> addToWallet(Map<USCurrency, Integer> moneyFromSale, String transactionRef) throws CurrencyNotSupportedException {
        Pair<String, String> result;
        for (USCurrency key : moneyFromSale.keySet()) {
            if (!moneyWallet.containsKey(key)) {
                throw new CurrencyNotSupportedException(transactionRef);
            }
        }

        synchronized (this) {
            for (Entry<USCurrency, Integer> entry : moneyFromSale.entrySet()) {
                moneyWallet.put(entry.getKey(), moneyWallet.get(entry.getKey()) + entry.getValue());
            }
            persistTransactionRef(transactionRef);
        }
        result = new Pair<>(transactionRef, "Success");
        return result;
    }

    public Map<USCurrency, Integer> withdrawFromWallet(int moneyToRefundInPennies, String transactionRef) throws InsufficientFundsException {
        List currencyInDescOrder = Money.moneyInDescOrder;
        Map<USCurrency, Integer> unitsToWithdraw = new HashMap<>();

        if (moneyWallet == null) {
            getInstance();
        }
        int moneyToRefund = moneyToRefundInPennies;
        synchronized (this) {
            while (moneyToRefund > 0) {
                for (Object currency : currencyInDescOrder) {
                    USCurrency currency1 = (USCurrency) currency;
                    if (currency1.pennyValue() < moneyToRefund && checkCashExists(currency1)) {
                        unitsToWithdraw.put(currency1,
                                unitsToWithdraw.containsKey(currency1) ?
                                        unitsToWithdraw.get(currency1) + 1 :
                                        1);
                        moneyToRefund = moneyToRefund - currency1.pennyValue();
                        break;
                    }
                    if (currency1.equals(Money.PENNY) && !checkCashExists(Money.PENNY)) {
                        logTransactionFailed(transactionRef);
                        throw new InsufficientFundsException(transactionRef);
                    }
                }
            }

            for (Entry<USCurrency, Integer> entry : unitsToWithdraw.entrySet()) {
                moneyWallet.put(entry.getKey(), moneyWallet.get(entry.getKey()) - unitsToWithdraw.get(entry.getKey()));
            }
            persistTransactionRef(transactionRef);
        }
        return unitsToWithdraw;
    }

    private void persistTransactionRef(String transactionRef) {
        System.out.println(transactionRef);
    }

    private void logTransactionFailed(String transactionRef) {
        System.out.println(transactionRef);
    }


    private boolean checkCashExists(USCurrency usCurrency) {
        return (moneyWallet.get(usCurrency) > 0);
    }
}
