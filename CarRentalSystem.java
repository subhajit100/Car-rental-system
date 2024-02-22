package carRentalSystem;

import java.util.*;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        //TODO:- see if any check is required wrt to parameter car before adding to list
        this.cars.add(car);
    }

    public void addCustomer(Customer customer){
        //TODO:- see if any check is required wrt to parameter customer before adding to list
        this.customers.add(customer);
    }

    private void addRental(Rental rental){
        this.rentals.add(rental);
    }

    private void rentCar(){

    }

    private void returnCar(){

    }

    public void menu(){
        while(true){
            System.out.println("--- Car Rental Service ---");
            System.out.println("Select among one of the choices:- ");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");

            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            if(choice.equals("1")){
                //  Rent a car here
                // check if no car is available for rent
                if(!atLeastOneCarAvailable()){
                    System.out.println("Sorry, no car is available for rent. Come back soon");
                    break;
                }
                // car is available, now we want to register the customer with details
                System.out.println("Please enter your name");
                String name = sc.nextLine();
                System.out.println("Please enter your age");
                // TODO:- put a condition to only allow customer with age>18
                int age = sc.nextInt();
                // this line is written to handle the enter after entering the integer age value
                sc.nextLine();
                String custId = "CUS" + (customers.size() + 1);

                // create a new customer
                Customer customer = new Customer(custId, name, age);
                this.customers.add(customer);

                // now go on showing him all the available cars for rent
                System.out.println("These are the cars available with us for now");
                System.out.println("Please select one of them by typing their id");

                // print list car details:- id, brand, model, dailyPrice
                cars.forEach(car -> {
                    if(car.isAvailable()){
                        System.out.println("id: "+ car.getCarId() + ", brand: " + car.getBrand() + ", model: " + car.getModel() + ", Daily Price: " + car.getPricePerDay());
                    }
                });

                // taking car id from customer
                String carId = sc.nextLine();
                Car car = getCarFromCarId(carId);
                // check if entered carId is among available cars
                if(Objects.nonNull(car)){
                    System.out.println("Enter the number of days you want to rent it out");
                    int days = sc.nextInt();
                    // create a new rental from customer, car and days and add it to rentals list
                    Rental rental = new Rental(car, customer, days);
                    rentals.add(rental);
                    // make that car unavailable
                    car.rentCar();

                    // show the full receipt to customer with all the details
                    System.out.println("Please have a look at the details for rent");
                    System.out.println("Customer id: " + customer.getCustId());
                    System.out.println("Customer name: " + customer.getName());
                    System.out.println("Customer age: " + customer.getAge());
                    System.out.println("Car Details: ");
                    System.out.println("id: "+ car.getCarId() + ", brand: " + car.getBrand() + ", model: " + car.getModel() + ", days: " + rental.getDays() +  ", Total Price: " + rental.getTotalPrice());
                }
                else{
                    System.out.println("Sorry, but the entered Car id doesn't exist");
                }
            }
            else if(choice.equals("2")){
                // return a rented car
                // first ask for his custId
                System.out.println("Please enter your customer id to proceed further");
                String custId = sc.nextLine();
                // I want to get all the cars the customer has booked with this custId
                List<Car> rentedCars = getRentalCarsFromCustomerId(custId);
                // show out all the cars he has in his rent list
                System.out.println("We have got all these cars under your rented list");
                rentedCars.forEach(rc -> {
                    System.out.println("id: "+ rc.getCarId() + ", brand: " + rc.getBrand() + ", model: " + rc.getModel() + ", Daily Price: " + rc.getPricePerDay());
                });

                // ask him to select the one with id to be returned
                System.out.println("\nPlease type the id of the car you want to return");
                String carId = sc.nextLine();

                // Find the rental containing this customer and the car with carId
                Rental rental = getRentalInfoFromCarAndCustomerInfo(carId, custId);
                if(rental!=null){
                    // make the car available to true.
                    rental.getCar().returnCar();
                    // Remove that rental from the rentals list
                    this.rentals.remove(rental);
                    System.out.println("Car returned successfully");
                }
                else{
                    System.out.println("We are not able to extract the rental information for custId: " + custId + " and carId: " + carId);
                }
            }
            else if(choice.equals("3")){
                System.out.println("Thanks a lot for using our service");
                break;
            }
            else{
                System.out.println("Please enter among the given choices");
            }
        }
    }

    private Rental getRentalInfoFromCarAndCustomerInfo(String carId, String custId) {
        Rental rental = null;
        for(Rental rent: this.rentals){
            if(rent.getCustomer().getCustId().equals(custId) && rent.getCar().getCarId().equals(carId)){
                rental = rent;
            }
        }
        return rental;
    }

    private List<Car> getRentalCarsFromCustomerId(String custId) {
        List<Car> cars = new ArrayList<>();
        this.rentals.forEach(rental -> {
            if(rental.getCustomer().getCustId().equals(custId)){
                cars.add(rental.getCar());
            }
        });
        return cars;
    }

    private Car getCarFromCarId(String carId) {
        Car car = null;
        for(Car cr: cars){
            if(cr.isAvailable() && cr.getCarId().equals(carId)){
                car = cr;
                break;
            }
        }
        return car;
    }

    private boolean atLeastOneCarAvailable() {
        return this.cars.stream().anyMatch(Car::isAvailable);
    }

}
