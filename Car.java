package carRentalSystem;

public class Car {
    private String carId;
    private String model;
    private String brand;
    private float pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String model, String brand, float pricePerDay) {
        this.carId = carId;
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rentCar(){
        this.isAvailable = false;
    }

    public void returnCar(){
        this.isAvailable = true;
    }
}
