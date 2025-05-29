package com.plurasight.Deli.Items;

public class Drink extends Item {
    private String size; // "Small", "Medium", "Large"
    private String flavor;

    public Drink(String size, String flavor) {
        super(size + " " + flavor + " Drink", 0.0);
        this.size = size;
        this.flavor = flavor;
        setPrice();
    }

    private void setPrice() {
        switch (size.toLowerCase()) {
            case "small" -> this.price = 2.00;
            case "medium" -> this.price = 2.50;
            case "large" -> this.price = 3.00;
        }
    }

    @Override
    public String getDetails() {
        return size + " " + flavor + " Drink - $" + String.format("%.2f", price);
    }
}
