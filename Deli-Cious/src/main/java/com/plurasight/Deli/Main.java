package com.plurasight.Deli;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n--- Welcome to SAMMIES CAFE! <3 ---");
                System.out.println("1) New Order");
                System.out.println("0) Exit");
                System.out.print("\nEnter your choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        Order currentOrder = new Order(scanner);
                        currentOrder.processOrder();
                        if (currentOrder.isCheckoutConfirmed()) {
                            System.out.println("\nThank you for choosing Sammies Cafe! Restarting for new customer. <3");

                        }
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            System.out.println("\nGoodbye! Come back soon! <3");
            scanner.close();
        }
}
