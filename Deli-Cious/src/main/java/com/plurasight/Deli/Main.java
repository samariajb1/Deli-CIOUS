package com.plurasight.Deli;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Welcome to DELI-cious! ---");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    // Create an Order object and pass the scanner to it
                    Order order = new Order(scanner);
                    // Start the order process, which manages the order screen loop
                    order.startOrderProcess();
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Goodbye! Come back soon!");
        scanner.close(); // Close the scanner when done
    }
}