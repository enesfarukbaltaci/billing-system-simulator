import exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private String invoiceNumber;
    private LocalDate date;
    private List<Item> items;

    public Invoice(String invoiceNumber, LocalDate date) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.items = new ArrayList<>();
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.calculateTotal();
        }
        return total;
    }
}
