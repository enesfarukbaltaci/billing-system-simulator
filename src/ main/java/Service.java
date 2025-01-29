import exceptions.InvalidPriceException;

public class Service implements Item{
    private String name;
    private double pricePerHour;
    private double hoursWorked;

    public Service(String name, double pricePerHour, double hoursWorked) {
        this.name = name;
        this.pricePerHour = pricePerHour;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateTotal() {
        return pricePerHour * hoursWorked;
    }

    public String getName() {
        return name;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
}
