import java.util.List;

public class Invoice {

    private String invoiceNumber;
    private List<Product> items;
    private String date;

    public Invoice(String invoiceNumber, List<Product> items, String date) {
        this.invoiceNumber = invoiceNumber;
        this.items = items;
        this.date = date;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double calculateSubtotal(){
        return items.stream().mapToDouble(item -> item.calculateSubtotal(1)).sum();
    }

    public double calculateTaxes(){
        return calculateSubtotal() * 0.15;
    }

    public double calculateTotal(){
        return calculateSubtotal() + calculateTaxes();
    }
}
