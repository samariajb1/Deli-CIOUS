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

    public Order(Scanner scanner) {
        this.items = new ArrayList<>();
        this.scanner = scanner;
    }

    // This method is called from Main.java
    public void startOrderProcess() {
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n--- Welcome to Sammies Cafe! <3 ---"); // Added <3
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("\nEnter your choice: "); // Added \n
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    Order order = new Order(scanner);
                    order.startOrderProcessInner(); // Call inner method
                    // After inner process, decide if main loop continues
                    if (order.items.isEmpty() && !order.isCheckoutConfirmed()) {
                        // Order was effectively cancelled or nothing was added
                        // Loop continues to main menu
                    } else {
                        ordering = false; // Order completed (checked out or cancelled explicitly from inner loop)
                    }
                }
                case "0" -> ordering = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // New method to encapsulate the order process loop
    private boolean checkoutConfirmed = false; // Flag to track if checkout was confirmed

    private void startOrderProcessInner() {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- Order Screen <3 ---"); // Added <3
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("\nEnter your choice: "); // Added \n
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addSandwich();
                case "2" -> addDrink();
                case "3" -> addChips();
                case "4" -> {
                    checkout();
                    ordering = false; // Exit order process after checkout (or if user cancels from checkout)
                }
                case "0" -> {
                    cancelOrder();
                    ordering = false; // Exit order process after cancel
                }
                default -> System.out.println("Invalid choice. Please try again. :(");
            }
        }
    }

    public boolean isCheckoutConfirmed() {
        return checkoutConfirmed;
    }


    private void addSandwich() {
        System.out.println("\n--- Building Your Custom Sandwich <3 ---"); // Added <3
        CustomSandwich sandwich = new CustomSandwich();
        sandwich.buildSandwich(scanner);

        System.out.print("\nWould you like the sandwich toasted? (yes/no): "); // Added \n
        sandwich.setToasted(scanner.nextLine().equalsIgnoreCase("yes"));

        items.add(sandwich);
        System.out.println("\nSandwich added to your order!"); // Added \n and improved message

        try {
            System.out.println("Proceeding to next step...");
            Thread.sleep(1500); // Pause for 1.5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println("Pause interrupted.");
        }

        System.out.print("\nWould you like to make this a combo for $3.50? (yes/no): "); // Added \n
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\n--- Combo Options <3 ---"); // Added <3
            System.out.print("\nChoose your drink flavor: "); // Added \n
            String drinkFlavor = scanner.nextLine();
            System.out.print("\nChoose your drink size (Small, Medium, Large): "); // Added \n
            String drinkSize = scanner.nextLine();
            Drink comboDrink = new Drink(drinkSize, drinkFlavor);

            System.out.print("\nChoose your chips type: "); // Added \n
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
            System.out.println("\nCombo Meal added for $3.50!"); // Added \n
        }

        System.out.print("\nWould you like to checkout now? (yes/no): "); // Added \n
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            checkout();
        } else {
            System.out.println("\nReturning to order screen."); // Added \n
        }
    }

    private void addDrink() {
        System.out.println("\n--- Adding a Drink <3 ---"); // Added <3
        System.out.print("\nEnter drink flavor: "); // Added \n
        String flavor = scanner.nextLine();
        System.out.print("\nEnter drink size (Small, Medium, Large): "); // Added \n
        String size = scanner.nextLine();
        Drink drink = new Drink(size, flavor);
        items.add(drink);
        System.out.println("\n" + drink.getName() + " added to your order!"); // Added \n and improved message
    }

    private void addChips() {
        System.out.println("\n--- Adding Chips <3 ---"); // Added <3
        System.out.print("\nEnter chip type: "); // Added \n
        String type = scanner.nextLine();
        Sides chips = new Sides(type);
        items.add(chips);
        System.out.println("\n" + chips.getName() + " added to your order!"); // Added \n and improved message
    }

    private void checkout() {
        System.out.println("\n--- Proceeding to Checkout <3 ---"); // Added <3
        displayOrderDetails();
        System.out.print("\nConfirm order? (yes/no): "); // Added \n
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            Receipt.save(this);
            System.out.println("\nOrder finalized!"); // Added \n
            System.out.println("Thanks for ordering! Goodbye. <3"); // Added <3
            items.clear();
            this.checkoutConfirmed = true; // Set flag
        } else {
            System.out.println("\nCheckout cancelled. Returning to order screen."); // Added \n
        }
    }

    private void cancelOrder() {
        System.out.println("\nOrder cancelled. Returning to main menu."); // Added \n
        items.clear();
        this.checkoutConfirmed = false; // Ensure flag is false on cancel
    }

    public String getOrderDetailsAsString() {
        StringBuilder details = new StringBuilder();
        details.append("\n--- Your Current Order <3 ---\n"); // Added <3
        if (items.isEmpty()) {
            details.append("No items in your order yet.\n");
            return details.toString();
        }
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
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


