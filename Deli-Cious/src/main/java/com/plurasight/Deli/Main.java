package com.plurasight.Deli;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Only one scanner for the whole application
        boolean running = true;

        while (running) {
            System.out.println("\n--- Welcome to SAMMIES CAFE! <3 ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    // Create an Order object, passing the *same* scanner to it
                    Order order = new Order(scanner);
                    // Call the method that manages the entire order process
                    order.startOrderProcess();
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Goodbye! Come back soon!");
        scanner.close();
    }
}
