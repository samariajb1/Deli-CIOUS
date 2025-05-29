package com.plurasight.Deli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    public static void save(Order order) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String folderName = "receipts";
        String filename = folderName + "/" + timestamp + ".txt";
        System.out.println("Please review your receipt Via Email");


        // Create the receipts folder if it doesn't exist
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir(); // Creates the directory for the file
        }

        try (FileWriter writer = new FileWriter(filename)) {
            // Write a header
            writer.write("--- Sammmies Cafe Receipt ---\n");
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("--------------------------\n");

            // Get the order details from the Order object and write them
            writer.write(order.getOrderDetailsAsString());

            writer.write("Thank you for your order!\n");
            System.out.println("Receipt saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}