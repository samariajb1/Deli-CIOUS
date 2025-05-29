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
    private Scanner scanner; // The scanner passed from Main

    public Order(Scanner scanner) { // Constructor MUST accept and store the scanner
        this.items = new ArrayList<>();
        this.scanner = scanner; // Initialize the scanner here
    }

    // This is the method that controls the 'Order Screen' flow
    public void startOrderProcess() {
        boolean ordering = true; // Flag to keep the order process loop running

        while (ordering) {
            System.out.println("\n--- Order Screen ---");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order"); // This should go back to Main Menu
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine(); // Read user input for the order screen

            switch (choice) {
                case "1" -> addSandwich(); // Call method to add a sandwich
                case "2" -> addDrink();    // Call method to add a drink
                case "3" -> addChips();    // Call method to add chips
                case "4" -> {
                    checkout();      // Call checkout process
                    ordering = false; // Exit this loop after checkout (or if user cancels from checkout)
                }
                case "0" -> {
                    cancelOrder();   // Call cancel order process
                    ordering = false; // Exit this loop after cancellation
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // --- Placeholder/Example Methods (You'll expand these) ---
    private void addSandwich() {
        System.out.println("Adding a sandwich...");
        CustomSandwich sandwich = new CustomSandwich();
        // You'll need to pass the scanner to buildSandwich
        sandwich.buildSandwich(scanner); // Make sure buildSandwich accepts Scanner
        items.add(sandwich);
        System.out.println("Sandwich added!");

        


    }

    private void addDrink() {
        System.out.println("Adding a drink...");
        // Example:
        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter drink size (Small, Medium, Large): ");
        String size = scanner.nextLine();
        Drink drink = new Drink(size, flavor);
        items.add(drink);
        System.out.println(drink.getName() + " added!");
    }

    private void addChips() {
        System.out.println("Adding chips...");
        // Example:
        System.out.print("Enter chip type: ");
        String type = scanner.nextLine();
        Sides chips = new Sides(type);
        items.add(chips);
        System.out.println(chips.getName() + " added!");
    }

    private void checkout() {
        System.out.println("Proceeding to checkout...");
        // Call displayOrderDetails() here
        displayOrderDetails();
        System.out.print("Confirm order? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            Receipt.save(this); // Assuming Receipt.save() is static and takes Order object
            System.out.println("Order finalized!");
            items.clear(); // Clear items after successful checkout
        } else {
            System.out.println("Checkout cancelled. Returning to order screen.");
            // Don't clear items here if the user chose not to confirm, they might want to change it.
            // However, your requirement for 'Cancel' after 'Checkout' means the order is gone either way.
            // So clearing is fine if that's the desired behavior.
            items.clear(); // If "cancel" at checkout means full order cancellation.
        }
    }

    private void cancelOrder() {
        System.out.println("Order cancelled. Returning to main menu.");
        items.clear(); // Clear all items if the order is cancelled
    }

    // This method is now public for the Receipt class and returns a String
    public String getOrderDetailsAsString() {
        StringBuilder details = new StringBuilder();
        details.append("\n--- Your Current Order ---\n");
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

