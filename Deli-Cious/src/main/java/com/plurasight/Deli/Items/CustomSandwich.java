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

    public void setToasted(boolean toasted){
        this.toasted = toasted;
    }

    @Override
    public String getDetails() {
        return super.getDetails();
    }

    public void buildSandwich(Scanner scanner) {
        System.out.print("\n--- Choose Your Sandwich Base <3 ---\n"); // Added <3
        System.out.print("\nEnter sandwich size (4, 8, 12): "); // Added \n
        String chosenSize = scanner.nextLine();
        this.size = chosenSize + "\"";

        System.out.print("\nEnter bread type (white, wheat, rye, wrap): "); // Added \n
        String breadType = scanner.nextLine();
        addIngredient(new Bread(this.size, breadType)); // Corrected to use this.size

        System.out.print("\n--- Add Meats (type 'none' to skip) ---\n");
        while (true) {
            System.out.print("\nAdd meat (steak, ham, salami, roast beef, chicken, bacon): "); // Added \n
            String meatType = scanner.nextLine();
            if (meatType.equalsIgnoreCase("none")) break;
            addIngredient(new Meat(this.size, meatType));
            System.out.print("\nAdd extra " + meatType + "? (yes/no): "); // Added \n
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                addIngredient(new Meat(this.size, meatType + " (Extra)"));
            }
        }

        System.out.print("\n--- Add Cheeses (type 'none' to skip) ---\n");
        while (true) {
            System.out.print("\nAdd cheese (american, provolone, cheddar, swiss): "); // Added \n
            String cheeseType = scanner.nextLine();
            if (cheeseType.equalsIgnoreCase("none")) break;
            addIngredient(new Cheese(this.size, cheeseType));
            System.out.print("\nAdd extra " + cheeseType + "? (yes/no): "); // Added \n
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                addIngredient(new Cheese(this.size, cheeseType + " (Extra)"));
            }
        }

        System.out.print("\n--- Add Regular Toppings (type 'none' to skip) ---\n");
        while (true) {
            System.out.print("\nAdd topping (lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms): "); // Added \n
            String toppingType = scanner.nextLine();
            if (toppingType.equalsIgnoreCase("none")) break;
            addIngredient(new RegularTopping(toppingType));
        }

        System.out.print("\n--- Add Sauces (type 'none' to skip) ---\n");
        while (true) {
            System.out.print("\nAdd sauce (mayo, mustard, ketchup, ranch, thousand islands, vinaigrette): "); // Added \n
            String sauceType = scanner.nextLine();
            if (sauceType.equalsIgnoreCase("none")) break;
            addIngredient(new RegularTopping(sauceType + " Sauce"));
        }

        System.out.print("\n--- Add Sides for Sandwich (type 'none' to skip) ---\n");
        while (true) {
            System.out.print("\nAdd Au Jus or Sauce (au jus, sauce): "); // Added \n
            String sideType = scanner.nextLine();
            if (sideType.equalsIgnoreCase("none")) break;
            addIngredient(new RegularTopping(sideType));
        }
    }
}
