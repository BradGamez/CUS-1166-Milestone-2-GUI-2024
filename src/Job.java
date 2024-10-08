import java.sql.Timestamp;

public class Job {
    private String ID;
    private String description;
    private Timestamp timeToCompletion;
    private double cost;

    public Job(String ID, String description, Timestamp timeToCompletion, double cost) {
        this.ID = ID;
        this.description = description;
        this.timeToCompletion = timeToCompletion;
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimeToCompletion() {
        return timeToCompletion;
    }

    public void setTimeToCompletion(Timestamp timeToCompletion) {
        this.timeToCompletion = timeToCompletion;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toString() {
        return getID() + "\n" + getDescription() + "\n" + getTimeToCompletion() + "\n" + getCost();
    }
}
