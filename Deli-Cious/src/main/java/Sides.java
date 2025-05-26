import java.util.List;

public class Sides {
private String name;
private double price;

    public Sides(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return name + " - $" + price;

    }
}
