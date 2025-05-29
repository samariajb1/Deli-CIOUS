package com.plurasight.Deli.Items;
import com.plurasight.Deli.Ingredients.Bread;
import com.plurasight.Deli.Ingredients.Cheese;
import com.plurasight.Deli.Ingredients.Meat;
import com.plurasight.Deli.Ingredients.RegularTopping;

import java.util.Scanner;

public class CustomSandwich extends Sandwich {

    public CustomSandwich() {
        super("", "Custom Sandwich");
    }

    @Override
    public String getDetails() {
        String baseDetails = super.getDetails();
        return baseDetails + (this.toasted ? " (Toasted)" : " (Not Toasted)");
    }
    public void buildSandwich(Scanner scanner) {
        // 1. Select Size (will determine bread price later)
        System.out.print("Choose sandwich size (4, 8, 12): ");
        String chosenSize = scanner.nextLine();
        this.size = chosenSize + "\""; // Store the chosen size in the inherited 'size' field

        // 2. Select Bread
        System.out.print("Choose bread (white, wheat, rye, wrap): ");
        String breadType = scanner.nextLine();
        // Instantiate and add Bread based on chosen size
        addIngredient(new Bread(chosenSize, breadType)); // Use chosenSize here

        // 3. Add Meats (Loop for multiple)
        while (true) {
            System.out.print("Add meat? (steak, ham, salami, roast beef, chicken, bacon, none to skip): ");
            String meatType = scanner.nextLine();
            if (meatType.equalsIgnoreCase("none")) break;
            addIngredient(new Meat(chosenSize, meatType)); // Add meat
            System.out.print("Add extra " + meatType + "? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                addIngredient(new Meat(chosenSize, meatType + " (Extra)")); // Add another for extra charge
            }
        }

        // 4. Add Cheeses (Loop for multiple)
        while (true) {
            System.out.print("Add cheese? (american, provolone, cheddar, swiss, none to skip): ");
            String cheeseType = scanner.nextLine();
            if (cheeseType.equalsIgnoreCase("none")) break;
            addIngredient(new Cheese(chosenSize, cheeseType)); // Add cheese
            System.out.print("Add extra " + cheeseType + "? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                addIngredient(new Cheese(chosenSize, cheeseType + " (Extra)")); // Add another for extra charge
            }
        }

        // 5. Add Regular Toppings (Loop for multiple)
        while (true) {
            System.out.print("Add regular topping? (lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms, none to skip): ");
            String toppingType = scanner.nextLine();
            if (toppingType.equalsIgnoreCase("none")) break;
            addIngredient(new RegularTopping(toppingType)); // Regular topping (price 0)
        }

        // 6. Add Sauces (Loop for multiple)
        while (true) {
            System.out.print("Add sauce? (mayo, mustard, ketchup, ranch, thousand islands, vinaigrette, none to skip): ");
            String sauceType = scanner.nextLine();
            if (sauceType.equalsIgnoreCase("none")) break;
            addIngredient(new RegularTopping(sauceType + " Sauce")); // Using RegularTopping for sauces too (price 0)
        }

        // 7. Add Sides (Au Jus, Sauce - these are different from sandwich sauces)
        // Project lists 'au jus' and 'sauce' under 'Sides' for sandwiches.
        // If these are *additional* sides that aren't part of the sandwich but apply to it,
        // you might add them here as well or in the Order class itself as separate items.
        // For now, let's assume they are regular toppings/sauces for the sandwich.
        while (true) {
            System.out.print("Add Au Jus or Sauce? (au jus, sauce, none to skip): ");
            String sideType = scanner.nextLine();
            if (sideType.equalsIgnoreCase("none")) break;
            addIngredient(new RegularTopping(sideType)); // Price 0
        }

        // 8. Toasted
        System.out.print("Would you like the sandwich toasted? (yes/no): ");
        this.toasted = scanner.nextLine().equalsIgnoreCase("yes");
    }
}
}

