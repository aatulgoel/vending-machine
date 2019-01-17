package sale;

import money.MoneyManager;
import money.USCurrency;
import product.Product;
import product.ProductManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VendingMachine {

    private Map<String, Order> activeTransactionMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Product> availableProduct = listAvailableProducts();


        System.out.println("Choose Product");
        for (Product product : availableProduct) {
            System.out.print(product.name());
        }

        String s = br.readLine();
        System.out.print("Enter Integer:");
        try {
            int i = Integer.parseInt(br.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        }
    }


    public static List<Product> listAvailableProducts() {
        Map<Product, Integer> productMap = ProductManager.getProductMap();
        List<Product> availableProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> product : productMap.entrySet()) {
            if (product.getValue() > 0) {
                availableProducts.add(product.getKey());
            }
        }
        return availableProducts;

    }

    private Integer convertCurrencyToAmount(Map<USCurrency, Integer> currency) {
        return 0;
    }

    private void persistPayment(String transactionRef, Map<USCurrency, Integer> currency) {
        MoneyManager.getInstance().addToWallet(currency, transactionRef);
    }

    public void registerProductChoice(String transactionRef, Product product) {
        Order clientOrder;
        if (activeTransactionMap.containsKey(transactionRef)) {
            clientOrder = activeTransactionMap.get(transactionRef);
        } else {
            clientOrder = new Order();
        }
        clientOrder.setProduct(product);
        activeTransactionMap.put(transactionRef, clientOrder);
    }

    private boolean isSufficetMoneyPaidForProduct(Order order) {
        //TODO Check for null pointers one will be thrown here

        return order.getProduct() != null && order.getPayment() != null
                && order.getProduct().price() <= order.getPayment();
    }

    private boolean hasClientOverpaid(Order order) {
        //TODO Check for null pointers one will be thrown here
        return order.getProduct() != null && order.getPayment() != null
                && order.getProduct().price() > order.getPayment();
    }

    private void vend(Product product) {
        System.out.println("Product Delivered");
    }

    private void requestClientToAddFunds(String transactionRef) {
        System.out.println("Please add more money to get product");
    }

    private void refundExcessPayment(Order order) {
        Map<USCurrency, Integer> fundsToRefund = MoneyManager.getInstance().withdrawFromWallet(order.getPayment() - order.getProduct().price(), order.getTransactionRef());
        refundClientPayment(fundsToRefund);
    }

    private void refundClientPayment(Map<USCurrency, Integer> funds) {
        System.out.println("Funds Refunded");
    }

    public String processClientRequest(String transactionRef, Map<USCurrency, Integer> funds, Product product) {
        try {
            Order clientOrder;
            if (activeTransactionMap.containsKey(transactionRef)) {
                clientOrder = activeTransactionMap.get(transactionRef);
            } else {
                clientOrder = new Order();
                clientOrder.setTransactionRef(transactionRef);
            }
            persistPayment(transactionRef, funds);
            clientOrder.setPayment(convertCurrencyToAmount(funds));
            clientOrder.setProduct(product);
            if (isSufficetMoneyPaidForProduct(clientOrder)) {
                ProductManager.getInstance().get(product);
                if (hasClientOverpaid(clientOrder)) {
                    refundExcessPayment(clientOrder);
                }
                vend(product);

            } else {
                requestClientToAddFunds(transactionRef);
            }
            return "request processed";
        } finally {
            refundClientPayment(funds);
        }
    }

}
