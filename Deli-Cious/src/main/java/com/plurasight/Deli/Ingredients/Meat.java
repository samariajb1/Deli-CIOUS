package com.plurasight.Deli.Ingredients;

public class Meat extends Ingredients {

    public Meat(String size, String type) {
        super(size.equalsIgnoreCase("4\"") ? 1.00 : size.equalsIgnoreCase("8\"") ? 2.00 : size.equalsIgnoreCase("12\"") ? 3.00 : 0, type);


    }
}