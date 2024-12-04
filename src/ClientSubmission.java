public class ClientSubmission {
    private String ID;
    private String duration;
    private String deadline;
    private String timestamp;

    public ClientSubmission(String ID, String duration, String deadline, String timestamp) {
        this.ID = ID;
        this.duration = duration;
        this.deadline = deadline;
        this.timestamp = timestamp;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDuration() {
        return duration;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String toString() {
        return "Client ID: "+ getID() + "; Job Duration: " + getDuration() + "; Job Deadline: " + getDeadline() + "; Timestamp: " + getTimestamp() + "\n";
    }
}
