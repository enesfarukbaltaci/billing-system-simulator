
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import exceptions.InvoiceNotFoundException;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BillingSystemRunner {

        private static Scanner scanner = new Scanner(System.in);
        private static InvoiceManager manager = new InvoiceManager();
        private static DecimalFormat df = new DecimalFormat("#.00");
        private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static void main(String[] args) {
            boolean running = true;

            while (running) {
                showMenu();
                String choice = getUserInput("Choose an option: ");

                switch (choice) {
                    case "1":
                        createInvoiceWithItem();  // Create invoice while adding an item
                        break;
                    case "2":
                        showInvoiceTotals();
                        break;
                    case "3":
                        generateSalesSummary();
                        break;
                    case "4":
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

    private static void showMenu() {
        System.out.println("\n----- Invoice System -----");
        System.out.println("1. Create Product or Service with Invoice");
        System.out.println("2. Show Invoice Totals");
        System.out.println("3. Generate Sales Summary");
        System.out.println("4. Exit");
    }

    // Unified method to get user input
    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Method to get numeric input and validate whether it's an integer or double
    private static Number getNumericInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        try {
            if (input.contains(".")) {
                // Try parsing as a double
                return Double.parseDouble(input);
            } else {
                // Try parsing as an integer
                return Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getNumericInput(prompt);  // Recursively call to get valid input
        }
    }

    // Method to get date input and validate the format (yyyy-MM-dd)
    private static LocalDate getDateInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        try {
            return LocalDate.parse(input, dateFormatter);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return getDateInput(prompt);  // Recursively call to get valid date input
        }
    }

    // Create Invoice and add Product/Service to it simultaneously
    private static void createInvoiceWithItem() {
        System.out.println("\n----- Create Invoice with Item -----");
        String invoiceNumber = getUserInput("Enter invoice number: ");
        LocalDate date = getDateInput("Enter invoice date (yyyy-MM-dd): ");

        // Create an invoice object
        Invoice invoice = new Invoice(invoiceNumber, date);

        boolean addMoreItems = true;

        while (addMoreItems) {
            System.out.println("Enter item details:");

            // Ask whether the item is a service or product
            System.out.print("Is this a service or product? (S/P): ");
            String type = scanner.nextLine().trim();

            if (type.equalsIgnoreCase("S")) {
                // Service creation
                try {
                    Number price = getNumericInput("Enter service price: ");
                    Number hoursWorked = getNumericInput("Enter hours worked: ");
                    if (price.doubleValue() <= 0) {
                        throw new InvalidPriceException("Price must be greater than zero.");
                    }
                    if (hoursWorked.doubleValue() <= 0) {
                        throw new InvalidQuantityException("Hours worked must be greater than zero.");
                    }
                    Service service = new Service("Service-" + invoiceNumber, price.doubleValue(), hoursWorked.doubleValue());
                    invoice.addItem(service); // Add service to invoice
                    System.out.println("Service added: " + service.getName());
                } catch (InvalidPriceException | InvalidQuantityException e) {
                    System.out.println(e.getMessage());
                }
            } else if (type.equalsIgnoreCase("P")) {
                // Product creation
                try {
                    Number price = getNumericInput("Enter product price: ");
                    Number quantity = getNumericInput("Enter quantity: ");
                    if (price.doubleValue() <= 0) {
                        throw new InvalidPriceException("Price must be greater than zero.");
                    }
                    if (quantity.intValue() <= 0) {
                        throw new InvalidQuantityException("Quantity must be greater than zero.");
                    }
                    Product product = new Product("Product-" + invoiceNumber, price.doubleValue(), quantity.intValue());
                    invoice.addItem(product); // Add product to invoice
                    System.out.println("Product added: " + product.getName());
                } catch (InvalidPriceException | InvalidQuantityException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid item type. Please enter 'S' for service or 'P' for product.");
            }

            // Ask if user wants to add another item
            System.out.print("Do you want to add another item? (Y/N): ");
            String addAnother = scanner.nextLine().trim();
            if (!addAnother.equalsIgnoreCase("Y")) {
                addMoreItems = false;
            }
        }

        // After adding items, print the invoice
        System.out.println("Invoice created: " + invoice.getInvoiceNumber() + " on " + invoice.getDate());
        System.out.println("Total invoice amount: " + df.format(invoice.calculateTotal()));

        // Add the created invoice to the manager (store it)
        manager.addInvoice(invoice);
    }

    // Show the totals of a specific invoice
    private static void showInvoiceTotals() {
        System.out.println("\n----- Show Invoice Totals -----");
        String invoiceNumber = getUserInput("Enter invoice number: ");

        try {
            Invoice invoice = manager.getInvoice(invoiceNumber);
            System.out.println("Invoice " + invoice.getInvoiceNumber() + " Total: " + df.format(invoice.calculateTotal()));
        } catch (InvoiceNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Generate sales summary for a specific period
    private static void generateSalesSummary() {
        System.out.println("\n----- Generate Sales Summary -----");
        String period = getUserInput("Enter period (yyyy-MM): ");
        manager.generateSalesSummary(period, df);
    }
    }


