package com.plurasight.Deli.Items;

public abstract class Item {
    protected String name;
    protected double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract String getDetails();

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

