import java.sql.*;
import java.util.*;

public class MySQLManager {
    private static final String uri = "jdbc:mysql://home.ddns.bradpersaud.xyz:3306/VCRTS?user=cus1166&password=crfwJ4wpd7kxPH&autoReconnect=true&characterEncoding=utf8";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) connection = DriverManager.getConnection(uri);

        return connection;
    }

    public static Client getClient(String username) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT accountData.username, accountData.password, accountData.ID, clientData.fullName, clientData.balance FROM accountData, clientData WHERE accountData.username = '" + username + "' AND accountData.ID = clientData.ID;");

        if (!resultSet.next()) return null;

        final String password = resultSet.getString("password");
        final int ID = resultSet.getInt("ID");
        final String fullName = resultSet.getString("fullName");
        final double balance = resultSet.getDouble("balance");

        return new Client(username, password, ID, fullName, balance, getJobs(ID));
    }

    public static void setClient(Client client) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSetAccountData = statement.executeQuery("INSERT INTO VCRTS.accountData (username, password, ID) VALUES ('" + client.getUsername() +"', '" + client.getPassword() + "', '" + client.getID() +"')");
        ResultSet resultSetClientData = statement.executeQuery("INSERT INTO VCRTS.clientData (ID, fullName, balance) VALUES ('" + client.getID() +"', '" + client.getFullName() + "', '" + client.getBalance() +"')");
    }

    public static Owner getOwner(String username) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT accountData.username, accountData.password, accountData.ID, ownerData.fullName, ownerData.balance FROM accountData, ownerData WHERE accountData.username = '" + username + "' AND accountData.ID = ownerData.ID;");

        if (!resultSet.next()) return null;

        final String password = resultSet.getString("password");
        final int ID = resultSet.getInt("ID");
        final String fullName = resultSet.getString("fullName");
        final double balance = resultSet.getDouble("balance");

        return new Owner(username, password, ID, fullName, balance, getCars(ID));
    }

    public static void setOwner(Owner owner) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSetAccountData = statement.executeQuery("INSERT INTO VCRTS.accountData (username, password, ID) VALUES ('" + owner.getUsername() +"', '" + owner.getPassword() + "', '" + owner.getID() +"')");
        ResultSet resultSetClientData = statement.executeQuery("INSERT INTO VCRTS.ownerData (ID, fullName, balance) VALUES ('" + owner.getID() +"', '" + owner.getFullName() + "', '" + owner.getBalance() +"')");
    }

    public static List<Car> getCars(int ownerID) throws SQLException {
        final List<Car> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM carData WHERE carData.ownerID = '" + ownerID + "'");

        while (resultSet.next()) {
            final String model = resultSet.getString("model");
            final String make = resultSet.getString("make");
            final int year = resultSet.getInt("year");
            final String VIN = resultSet.getString("VIN");
            final String plateNum = resultSet.getString("plateNum");
            final String type = resultSet.getString("type");
            final boolean acceptingJobs = resultSet.getBoolean("acceptingJobs");

            list.add(new Car(model, make, year, VIN, plateNum, type, acceptingJobs));
        }
        return list;
    }

    public static void setCar(Car car, Owner owner) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSetAccountData = statement.executeQuery("INSERT INTO VCRTS.carData (ownerID, model, make, year, VIN, plateNum, type, acceptingJobs) VALUES ('" + owner.getID() + "', '" + car.getModel() + "', '" + car.getMake() + "', '" + car.getYear() + "', '" + car.getVIN() + "', '" + car.getPlateNum() + "', '" + car.getType() + "', '" + car.isAcceptingJobs() +"')");
    }

    public static List<Job> getJobs(int clientID) throws SQLException {
        final List<Job> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM jobData WHERE jobData.clientID = '" + clientID + "'");

        while (resultSet.next()) {
            final String ID = resultSet.getString("ID");
            final String description = resultSet.getString("description");
            final Timestamp timeToCompletion = resultSet.getTimestamp("timeToCompletion");
            final double cost = resultSet.getDouble("cost");

            list.add(new Job(ID, description, timeToCompletion, cost));
        }
        return list;
    }

    public static void setJob(Job job, Client client) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSetAccountData = statement.executeQuery("INSERT INTO VCRTS.jobData (clientID, ID, description, timeToCompletion, cost) VALUES ('" + client.getID() + "', '" + job.getID() + "', '" + job.getDescription() + "', '" + job.getTimeToCompletion() + "', '" + job.getCost() + "')");
    }
}
