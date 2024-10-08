public class Owner {
    private String username;
    private String password;
    private int ID;
    private String fullName;
    private Car car;

    public Owner(String username, String password, int ID, String fullName, Car car) {
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.fullName = fullName;
        this.car = car;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
