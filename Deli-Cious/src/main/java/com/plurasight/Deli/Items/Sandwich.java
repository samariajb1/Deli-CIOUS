package com.plurasight.Deli.Items;

import com.plurasight.Deli.Ingredients.Ingredients;

import java.util.ArrayList;

public abstract class Sandwich extends Item {
    protected String size; // "4", "8", "12" (or "4\"", "8\"", "12\"" depending on consistency)
    protected boolean toasted; // Declared here, but value set in CustomSandwich
    protected ArrayList<Ingredients> ingredients; // Correctly storing a list of Ingredients

    public Sandwich(String size, String name) {
        super(name, 0); // Price initialized to 0, will be calculated from ingredients
        this.size = size;
        this.ingredients = new ArrayList<>(); // CRITICAL: Correctly initialized!
    }

    @Override
    public double getPrice() {
        // Sums the price of all ingredients.
        // This relies on the Bread ingredient being added to the 'ingredients' list.
        return ingredients.stream().mapToDouble(Ingredients::getPrice).sum();
    }

    @Override
    public String getDetails() {
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("Size: ").append(size).append(" ").append(name); // Add size to details
        detailsBuilder.append("Ingredients:\n");
        if (ingredients.isEmpty()) {
            detailsBuilder.append("  (No ingredients added yet)\n");
        } else {
            for (Ingredients i : ingredients) {
                detailsBuilder.append("  - ").append(i.getName()).append(" ($").append(String.format("%.2f", i.getPrice())).append(")\n");
            }
        }
        detailsBuilder.append("Sandwich Total Ingredient Price: $").append(String.format("%.2f", getPrice()));
        return detailsBuilder.toString();
    }

    // Correctly added method to add ingredients
    public void addIngredient(Ingredients ingredient) {
        this.ingredients.add(ingredient);
    }
}


