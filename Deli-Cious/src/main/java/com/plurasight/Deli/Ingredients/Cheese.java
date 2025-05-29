package com.plurasight.Deli.Ingredients;

public class Cheese {
    public Cheese(String size, String type) {
        // Price logic based on project description:
        // 4" = $0.75
        // 8" = $1.50
        // 12" = $2.25
        super(size.equalsIgnoreCase("4\"") ? 0.75 :
    }
}
