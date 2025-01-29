import java.util.HashMap;
import java.util.Map;

public class InvoiceManager {

    private Map<String, Invoice> invoices = new HashMap<>();

    public void addInvoice(Invoice invoice){
        invoices.put(invoice.getInvoiceNumber(),invoice);
    }

    public Invoice getInvoice(String invoiceNumber){
        return invoices.get(invoiceNumber);
    }

    public void generateSalesSummary(String period){
        double totalSales = invoices.values().stream()
                .filter(invoice -> invoice.getDate().contains(period))
                .mapToDouble(Invoice::calculateTotal)
                .sum();

        System.out.println("Total Sales for " + period + ": " + totalSales);
    }




}

