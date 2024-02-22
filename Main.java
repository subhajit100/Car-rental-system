package carRentalSystem;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem crs = new CarRentalSystem();
        crs.addCar(new Car("C001", "Thar", "Mahindra", 100));
        crs.addCar(new Car("C002", "Nexon", "Tata", 70));
        crs.addCar(new Car("C003", "Baleno", "Maruti", 80));
        crs.menu();
    }
}
