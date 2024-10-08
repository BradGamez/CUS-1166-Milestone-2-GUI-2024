public class Client {
    private String username;
    private String password;
    private int ID;
    private String fullName;
    private double balance;

    public Client(String username, String password, int ID, String fullName, double balance) {
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.fullName = fullName;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return getUsername() + "\n" + getPassword() + "\n" + getID() + "\n" + getFullName() + "\n" + getBalance();
    }
}
