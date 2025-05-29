package com.plurasight.Deli.Items;

import com.plurasight.Deli.Ingredients.Ingredients;

import java.util.ArrayList;

public abstract class Sandwich extends Item {
    protected String size; // "4", "8", "12"
    protected boolean toasted;
    protected ArrayList<Ingredients>ingredients;

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
        String details = "";
        for (Ingredients i : ingredients){
            details += i.toString();

        }

      return details + "  total price -  " + getPrice(); //Todo; Do formatting
    }
    public void addIngredient(Ingredients ingredient) { this.ingredients.add(ingredient); }

    }