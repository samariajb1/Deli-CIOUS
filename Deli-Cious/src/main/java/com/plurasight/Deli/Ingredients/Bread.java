package com.plurasight.Deli.Ingredients;

public class Bread extends Ingredients{

    public Bread(String size, String type) {
        super(size.equalsIgnoreCase("4") ? 5.50 : size.equalsIgnoreCase("8") ? 7.50 : size.equalsIgnoreCase("12") ? 8.50 : 0, type);


    }
    }


