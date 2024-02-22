package carRentalSystem;

public class Customer {
    private String custId;
    private String name;
    private int age;

    public Customer(String custId, String name, int age) {
        this.custId = custId;
        this.name = name;
        this.age = age;
    }

    public String getCustId() {
        return custId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
