package com.plurasight.Deli;

import com.plurasight.Deli.Items.Item;

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

    public String getOrderDetailsAsString() {
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("--- Your Current Order ---\n");
        if (items.isEmpty()) {
            detailsBuilder.append("No items in your order yet.\n");
            return detailsBuilder.toString();
        }
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            detailsBuilder.append(String.format("%d. %s%n", (i + 1), item.getDetails()));
            total += item.getPrice();
        }
        detailsBuilder.append("--------------------------\n");
        detailsBuilder.append(String.format("Order Total: $%.2f%n", total));
        detailsBuilder.append("--------------------------\n");
        return detailsBuilder.toString();
    }
    private void displayOrderDetails() {
        System.out.println(getOrderDetailsAsString()); // Now just prints the string
    }
    private void checkout() {
        displayOrderDetails(); // Display to console first
        System.out.print("Confirm order? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {

            Receipt.save(this); // Fix: Call your new Receipt.save() method
            System.out.println("Order completed and receipt generated. Returning to home screen.");
            items.clear();
        } else {
            System.out.println("Order not confirmed. Returning to home screen.");
            items.clear();
        }
    }

    private void cancelOrder() {
        System.out.println("Order cancelled. Returning to home screen.");
        items.clear();
    }


    public void startOrderProcess() {
    }
}

