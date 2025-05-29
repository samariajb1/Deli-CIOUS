package com.plurasight.Deli.Items;

import com.plurasight.Deli.Ingredients.Ingredients;

import java.util.ArrayList;

public abstract class Sandwich extends Item {
    protected String size;
    protected boolean toasted;
    protected ArrayList<Ingredients> ingredients;

    public Sandwich(String size, String name) {
        super(name, 0);
        this.size = size;
        this.ingredients = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        return ingredients.stream().mapToDouble(Ingredients::getPrice).sum();
    }

    @Override
    public String getDetails() {
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("Size: ").append(size).append(" ").append(name).append("\n");
        detailsBuilder.append("  Toasted: ").append(toasted ? "Yes" : "No").append("\n"); // Add toasted status here
        detailsBuilder.append("Ingredients:\n");
        if (ingredients.isEmpty()) {
            detailsBuilder.append("    (No ingredients added yet)\n");
        } else {
            for (Ingredients i : ingredients) {
                detailsBuilder.append("    - ").append(i.getName()).append(" ($").append(String.format("%.2f", i.getPrice())).append(")");
            }
            detailsBuilder.append("\n");
        }
        detailsBuilder.append("Sandwich Total Price: $").append(String.format("%.2f", getPrice()));
        return detailsBuilder.toString();
    }

    public void addIngredient(Ingredients ingredient) {
        this.ingredients.add(ingredient);
    }
}


