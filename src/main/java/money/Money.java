package money;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Money {


    final static Coin PENNY = new Coin("Penny", 1);
    final static Coin NICKEL = new Coin("Nickel", 5);
    final static Coin DIME = new Coin("Dime", 10);
    final static Coin QUARTER = new Coin("Quarter", 25);
    final static Bill DOLLAR = new Bill("Dollar", 1, 100);
    final static Bill TWO_DOLLAR = new Bill("TwoDollar", 1, 100);
    final static Bill FIVE_DOLLAR = new Bill("FiveDollar", 2, 200);
    final static Bill TEN_DOLLAR = new Bill("TenDollar", 5, 500);
    final static Bill TWENTY_DOLLAR = new Bill("TwentyDollar", 20, 2000);
    final static Bill FIFTY_DOLLAR = new Bill("FiftyDollar", 50, 5000);
    final static Bill HUNDRED_DOLLAR = new Bill("HundredDollar", 100, 10000);

    final static List<USCurrency> moneyInDescOrder = new ArrayList<>(Arrays.asList(HUNDRED_DOLLAR, FIFTY_DOLLAR,
            TWENTY_DOLLAR, TEN_DOLLAR, FIVE_DOLLAR, DOLLAR, QUARTER, DIME, NICKEL, PENNY));
}
