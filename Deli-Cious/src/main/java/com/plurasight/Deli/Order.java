package com.plurasight.Deli;

import com.plurasight.Deli.Items.CustomSandwich;
import com.plurasight.Deli.Items.Drink;
import com.plurasight.Deli.Items.Item;
import com.plurasight.Deli.Items.Sides;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private List<Item> items;
    private Scanner scanner;
    private boolean checkoutConfirmed = false; // to track if checkout was confirmed

    public Order(Scanner scanner) {
        this.items = new ArrayList<>();
        this.scanner = scanner;
    }

    // This method is called from Main.java

    public void processOrder() {
        boolean addingItems = true;

        while (addingItems) {
            System.out.println("\n--- Order Screen <3 ---");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addSandwich();
                case "2" -> addDrink();
                case "3" -> addChips();
                case "4" -> {
                    checkout();
                    addingItems = false; // Exit this loop after checkout (or if user cancels from checkout)
                }
                case "0" -> {
                    cancelOrder();
                    addingItems = false; // Exit this loop after cancel
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public boolean isCheckoutConfirmed() {
        return checkoutConfirmed;
    }



    private void addSandwich() {
        System.out.println("\n--- Building Your Custom Sandwich <3 ---");
        CustomSandwich sandwich = new CustomSandwich();
        sandwich.buildSandwich(scanner);

        System.out.print("\nWould you like the sandwich toasted? (yes/no): ");
        sandwich.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));

        items.add(sandwich);
        System.out.println("\nSandwich added to your order!");

        try {
            System.out.println("Proceeding to next step...");
            Thread.sleep(1500); // Pause for 1.5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println("Pause interrupted.");
        }

        System.out.print("\nWould you like to make this a combo for $3.50? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\n--- Combo Options <3 ---");
            System.out.print("\nChoose your drink flavor: ");
            String drinkFlavor = scanner.nextLine();
            System.out.print("\nChoose your drink size (Small, Medium, Large): ");
            String drinkSize = scanner.nextLine();
            Drink comboDrink = new Drink(drinkSize, drinkFlavor);

            System.out.print("\nChoose your chips type: ");
            String chipsType = scanner.nextLine();
            Sides comboChips = new Sides(chipsType);

            items.add(new Item("Combo Meal", 3.50) {
                @Override
                public String getDetails() {
                    return String.format("Combo Meal ($%.2f): %s and %s",
                            getPrice(),
                            comboDrink.getName(),
                            comboChips.getName());
                }
            });
            System.out.println("\nCombo Meal added for $3.50!");
        }

        System.out.print("\nWould you like to checkout now? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            checkout();
        } else {
            System.out.println("\nReturning to order screen.");
        }
    }

    private void addDrink() {
        System.out.println("\n--- Adding a Drink <3 ---");
        System.out.print("\nEnter drink flavor: ");
        String flavor = scanner.nextLine();
        System.out.print("\nEnter drink size (Small, Medium, Large): ");
        String size = scanner.nextLine();
        Drink drink = new Drink(size, flavor);
        items.add(drink);
        System.out.println("\n" + drink.getName() + " added to your order!");
    }

    private void addChips() {
        System.out.println("\n--- Adding Chips <3 ---");
        System.out.print("\nEnter chip type: ");
        String type = scanner.nextLine();
        Sides chips = new Sides(type);
        items.add(chips);
        System.out.println("\n" + chips.getName() + " added to your order!");
    }

    private void checkout() {
        System.out.println("\n--- Proceeding to Checkout <3 ---");
        displayOrderDetails();
        System.out.print("\nConfirm order? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            Receipt.save(this);
            System.out.println("\nOrder finalized!");
            System.out.println("Thanks for ordering! Goodbye. <3");
            items.clear();
            this.checkoutConfirmed = true;
        } else {
            System.out.println("\nCheckout cancelled. Returning to order screen.");
            this.checkoutConfirmed = false;
        }
    }

    private void cancelOrder() {
        System.out.println("\nOrder cancelled. Returning to main menu.");
        items.clear();
        this.checkoutConfirmed = false; // Ensure flag is false
    }

    public String getOrderDetailsAsString() {
        StringBuilder details = new StringBuilder();
        details.append("\n--- Your Current Order <3 ---\n");
        if (items.isEmpty()) {
            details.append("No items in your order yet.\n");
            return details.toString();
        }
        double total = 0;
        for (int i = 0; i < items.size(); i++) { //Explain
            Item item = items.get(i);
            details.append(String.format("%d. %s%n", (i + 1), item.getDetails()));
            total += item.getPrice();
        }
        details.append("--------------------------\n");
        details.append(String.format("Order Total: $%.2f%n", total));
        details.append("--------------------------\n");
        return details.toString();
    }

    private void displayOrderDetails() {
        System.out.println(getOrderDetailsAsString());
    }
}



