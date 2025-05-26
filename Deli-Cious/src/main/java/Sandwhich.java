import java.util.List;

public abstract class  Sandwhich {
    private String breadType,name;
    private List<String>ingredients;
    private double price;

    public Sandwhich(String breadType, String name, List<String> ingredients, double price) {
        this.breadType = breadType;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }
    @Override
    public String toString(){
        return name + "on" + breadType + "with" + ingredients + " - $" + price;
    }


}
