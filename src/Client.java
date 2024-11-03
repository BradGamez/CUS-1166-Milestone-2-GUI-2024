import java.util.ArrayList;
import java.util.List;

public class Client extends Account {
    private String fullName;
    private double balance;
    private List<Job> jobs = new ArrayList<>();

    public Client(String username, String password, int ID, String fullName, double balance) {
        super(username, password, ID);
        this.fullName = fullName;
        this.balance = balance;
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

    public List<Job> getJobs() {
        return jobs;
    }

    public void requestJob(Job job) {
        jobs.add(job);
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String toString() {
        return getUsername() + "\n" + getPassword() + "\n" + getID() + "\n" + getFullName() + "\n" + getBalance();
    }
}
