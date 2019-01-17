package product;

import util.OutOfStockException;

import java.util.HashMap;
import java.util.Map;

public class ProductManager extends HashMap<Product, Integer> {

    private static ProductManager productMap;

    private ProductManager() {
        initialize();
    }

    public static synchronized ProductManager getInstance() {
        if (productMap == null) {
            productMap = new ProductManager();
        }
        return productMap;
    }

    public static Map<Product, Integer> getProductMap() {
        return productMap;
    }

    private void initialize() {
        this.put(new Coke(), 100);
        this.put(new Water(), 100);
        this.put(new Pepsi(), 100);

    }

    public String replenishStock(HashMap<Product, Integer> stockToReplenish, String transactionRef) {
        if (productMap == null) {
            getInstance();
        }
        synchronized (this) {
            for (Entry<Product, Integer> entry : stockToReplenish.entrySet()) {
                productMap.put(entry.getKey(), productMap.get(entry.getKey()) + stockToReplenish.get(entry.getKey()));
            }
            persistTransactionRef(transactionRef);
        }
        return "Success";
    }


    public Product issueStock(Product requestedProduct, String transactionRef) throws OutOfStockException {
        synchronized (this) {

            if (productMap.get(requestedProduct) > 0) {
                productMap.put(requestedProduct, productMap.get(requestedProduct) - 1);
                persistTransactionRef(transactionRef);
            } else {
                logTransactionFailed(transactionRef);
                throw new OutOfStockException(transactionRef);
            }
        }
        return requestedProduct;
    }


    private void persistTransactionRef(String transactionRef) {
        System.out.println(transactionRef);
    }

    private void logTransactionFailed(String transactionRef) {
        System.out.println(transactionRef);
    }
}
