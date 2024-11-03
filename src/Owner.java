import java.util.ArrayList;
import java.util.List;

public class Owner extends Account {
    private String fullName;
    private List<Car> cars = new ArrayList<>();
    private double balance;

    public Owner(String username, String password, int ID, String fullName, double balance, Car car) {
        super(username, password, ID);
        this.fullName = fullName;
        this.balance = balance;
        cars.add(car);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public boolean removeCar(Car car) {
        return cars.remove(car);
    }

    public void setCars(List<Car> car) {
        this.cars = cars;
    }

    public String toString() {
        return getUsername() + "\n" + getPassword() + "\n" + getID() + "\n" + getFullName() + "\n" + getBalance();
    }
}
