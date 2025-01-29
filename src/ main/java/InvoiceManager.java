import exceptions.InvoiceNotFoundException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class InvoiceManager {
    private Map<String, Invoice> invoices;

    public InvoiceManager() {
        this.invoices = new HashMap<>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.put(invoice.getInvoiceNumber(), invoice);
    }

    public Invoice getInvoice(String invoiceNumber) throws InvoiceNotFoundException {
        Invoice invoice = invoices.get(invoiceNumber);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice with number " + invoiceNumber + " not found.");
        }
        return invoice;
    }

    public void generateSalesSummary(String period, DecimalFormat df) {
        String[] parts = period.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        double totalSales = invoices.values().stream()
                .filter(invoice -> invoice.getDate().getYear() == year && invoice.getDate().getMonthValue() == month)
                .mapToDouble(Invoice::calculateTotal)
                .sum();

        System.out.println("Total Sales for " + period + ": " + df.format(totalSales));
    }
}

