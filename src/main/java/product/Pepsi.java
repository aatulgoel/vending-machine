package product;

public class Pepsi implements Product {
    private String name = "Pepsi";
    private Integer price = 120;

    public String name() {
        return name;
    }

    public Integer price() {
        return price;
    }
}
