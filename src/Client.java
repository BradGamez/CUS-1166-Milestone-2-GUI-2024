import java.util.ArrayList;
import java.util.List;

public class Client extends Account {
    private String fullName;
    private double balance;
    private List<ClientSubmission> clientSubmissions = new ArrayList<>();

    public Client(String username, String password, int ID, String fullName, double balance) {
        super(username, password, ID);
        this.fullName = fullName;
        this.balance = balance;
    }

    public Client(String username, String password, int ID, String fullName, double balance, List<ClientSubmission> jobsList) {
        super(username, password, ID);
        this.fullName = fullName;
        this.balance = balance;
        this.clientSubmissions = jobsList;
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

    public List<ClientSubmission> getJobs() {
        return clientSubmissions;
    }

    public void requestJob(ClientSubmission clientSubmission) {
        clientSubmissions.add(clientSubmission);
    }

    public void setJobs(List<ClientSubmission> clientSubmissions) {
        this.clientSubmissions = clientSubmissions;
    }

    public String toString() {
        return getUsername() + "\n" + getPassword() + "\n" + getID() + "\n" + getFullName() + "\n" + getBalance();
    }
}
