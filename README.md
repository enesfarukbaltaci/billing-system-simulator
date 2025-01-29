# Billing System Simulator

This is a simple billing system simulator written in Java. You can create invoices with products or services, calculate totals, and generate sales summaries for specific periods.

## Features

- Add products or services to invoices.
- Calculate invoice totals (including subtotals and taxes).
- View sales summaries for daily or monthly periods.
- Simple menu-driven interface.
- Handles invalid inputs (like price, quantity, date) with clear error messages.

## How to Run

1. Clone or download the project.
2. Open it in your favorite IDE (like IntelliJ, Eclipse, etc.).
3. Run the `BillingSystemRunner` class to start the program.

## Usage

- You can choose to:
    - Create an invoice by adding products or services.
    - View the total of any invoice by entering the invoice number.
    - Get a sales summary by entering a specific date (e.g., monthly or daily).

## Example

- When creating an invoice, it will ask for the item type (product or service), price, quantity, and more.
- Invalid inputs will be handled by showing a relevant error message, like "Invalid price" or "Invalid date format."

## Notes

- The program uses `LocalDate` for dates and handles invalid date formats.
- Prices and quantities are validated to ensure they're positive.