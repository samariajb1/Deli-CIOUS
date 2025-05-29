package com.plurasight.Deli.Ingredients;

public abstract class Ingredients {
    protected double price;
    protected String name;

    public Ingredients(double price, String type) {
        this.price = price;
        name = type;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name - " + name + " price - " + price;

    }
}
