import java.util.List;
import java.util.Map;

public class CloudController {
    private int redundancyLevel;
    private List<Job> assignedJobs;
    private List<Job> completedJob;
    private int maxVehicleCapacity;
    private Map<String, Car> vehicleCheckPoint;
    private Map<String, Car> vehiclePool;

    public CloudController(int redundancyLevel, List<Job> assignedJobs, List<Job> completedJob, int maxVehicleCapacity, Map<String, Car> vehicleCheckPoint, Map<String, Car> vehiclePool) {
        this.redundancyLevel = redundancyLevel;
        this.assignedJobs = assignedJobs;
        this.completedJob = completedJob;
        this.maxVehicleCapacity = maxVehicleCapacity;
        this.vehicleCheckPoint = vehicleCheckPoint;
        this.vehiclePool = vehiclePool;
    }

    public int getRedundancyLevel() {
        return redundancyLevel;
    }

    public void setRedundancyLevel(int redundancyLevel) {
        this.redundancyLevel = redundancyLevel;
    }

    public List<Job> getAssignedJobs() {
        return assignedJobs;
    }

    public boolean assignJob(Job job) {
        return assignedJobs.add(job);
    }

    public void setAssignedJobs(List<Job> assignedJobs) {
        this.assignedJobs = assignedJobs;
    }

    public List<Job> getCompletedJob() {
        return completedJob;
    }

    public void setCompletedJob(List<Job> completedJob) {
        this.completedJob = completedJob;
    }

    public int getMaxVehicleCapacity() {
        return maxVehicleCapacity;
    }

    public void setMaxVehicleCapacity(int maxVechileCapacity) {
        this.maxVehicleCapacity = maxVechileCapacity;
    }

    public Map<String, Car> getVehicleCheckPoint() {
        return vehicleCheckPoint;
    }

    public boolean releaseVehicle(String vin) {
        if (vehicleCheckPoint.containsKey(vin)) {
            vehicleCheckPoint.remove(vin);
            return true;
        }
        return false;
    }

    public void setVehicleCheckPoint(Map<String, Car> vehicleCheckPoint) {
        this.vehicleCheckPoint = vehicleCheckPoint;
    }

    public Map<String, Car> getVehiclePool() {
        return vehiclePool;
    }

    public void setVehiclePool(Map<String, Car> vehiclePool) {
        this.vehiclePool = vehiclePool;
    }
}
