package com.plurasight.Deli.Ingredients;

public class Cheese extends Ingredients{
    public Cheese(String size, String type) {
        super(size.equalsIgnoreCase("4\"")? 0.75:size.equalsIgnoreCase("8\"")? 1.50: size.equalsIgnoreCase("12\"")? 2.25: 0.00,type);

    }
}
