package product;

public class Water implements Product {
    private String name = "Water";
    private Integer price = 150;

    public String name() {
        return name;
    }

    public Integer price() {
        return price;
    }
}
