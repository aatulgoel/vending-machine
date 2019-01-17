package product;

public class Coke implements Product {
    private String name = "Coke";
    private Integer price = 65;

    public String name() {
        return name;
    }

    public Integer price() {
        return price;
    }
}
