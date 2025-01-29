public class Service extends Product{

    private double hoursWorked;

    public Service(String name, double price, double hoursWorked) {
        super(name, price);
        this.hoursWorked = hoursWorked;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSubtotal(int quantity) {
        return getPrice() * hoursWorked;
    }
}
