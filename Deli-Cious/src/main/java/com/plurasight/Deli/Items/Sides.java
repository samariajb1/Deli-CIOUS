package com.plurasight.Deli.Items;
public class Sides extends Item {
    public Sides(String type) {
        super(type, type.equalsIgnoreCase("chips") ? 1.50 : 0.0);
    }

    @Override
    public String getDetails() {
        return name + " - $" + String.format("%.2f", price);
    }
}