package sale;

import product.Product;

public class Order {
    private String transactionRef;
    private Product product;
    private Integer payment;
    private OrderState currentState;

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
    }

    private enum OrderState {
        PREPROCESSING, PROCESSING, CANCELLED, COMPLETED
    }


}
