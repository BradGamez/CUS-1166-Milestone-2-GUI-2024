import java.util.List;
import java.util.Map;

public class CloudController {
    private int redundancyLevel;
    private List<ClientSubmission> assignedClientSubmissions;
    private List<ClientSubmission> completedClientSubmission;
    private int maxVehicleCapacity;
    private Map<String, Car> vehicleCheckPoint;
    private Map<String, Car> vehiclePool;

    public CloudController(int redundancyLevel, List<ClientSubmission> assignedClientSubmissions, List<ClientSubmission> completedClientSubmission, int maxVehicleCapacity, Map<String, Car> vehicleCheckPoint, Map<String, Car> vehiclePool) {
        this.redundancyLevel = redundancyLevel;
        this.assignedClientSubmissions = assignedClientSubmissions;
        this.completedClientSubmission = completedClientSubmission;
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

    public List<ClientSubmission> getAssignedJobs() {
        return assignedClientSubmissions;
    }

    public boolean assignJob(ClientSubmission clientSubmission) {
        return assignedClientSubmissions.add(clientSubmission);
    }

    public void setAssignedJobs(List<ClientSubmission> assignedClientSubmissions) {
        this.assignedClientSubmissions = assignedClientSubmissions;
    }

    public List<ClientSubmission> getCompletedJob() {
        return completedClientSubmission;
    }

    public void setCompletedJob(List<ClientSubmission> completedClientSubmission) {
        this.completedClientSubmission = completedClientSubmission;
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
