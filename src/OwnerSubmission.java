public class OwnerSubmission {
    private String ID;
    private String Vin;
    private String residencyTime;
    private String timestamp;

    public OwnerSubmission(String ID, String vin, String residencyTime, String timestamp) {
        this.ID = ID;
        Vin = vin;
        this.residencyTime = residencyTime;
        this.timestamp = timestamp;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public String getResidencyTime() {
        return residencyTime;
    }

    public void setResidencyTime(String residencyTime) {
        this.residencyTime = residencyTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "Owner ID: " + getID() + "; Vin: " + getVin() + "; Residency Time: " + getResidencyTime() + "; Timestamp: " + getTimestamp() +"\n";
    }
}
