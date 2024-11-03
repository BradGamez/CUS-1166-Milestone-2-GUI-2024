public class Car {
    private String Model;
    private String Make;
    private int Year;
    private String VIN;
    private String plateNum;
    private String type;
    private boolean acceptingJobs;

    public Car(String model, String make, int year, String VIN, String plateNum, String type, boolean acceptingJobs) {
        Model = model;
        Make = make;
        Year = year;
        this.VIN = VIN;
        this.plateNum = plateNum;
        this.type = type;
        this.acceptingJobs = acceptingJobs;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAcceptingJobs() {
        return acceptingJobs;
    }

    public void setAcceptingJobs(boolean acceptingJobs) {
        this.acceptingJobs = acceptingJobs;
    }

    public String toString() {
        return getModel() + "\n" + getMake() + "\n" + getYear() +  "\n" + getVIN() + "\n" + getPlateNum() + "\n" + getType();
    }
}
