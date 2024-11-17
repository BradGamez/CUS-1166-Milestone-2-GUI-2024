import java.sql.*;
import java.util.*;

public class MySQLManager {
    private static final String uri = "jdbc:mysql://localhost:3306/VCRTS?user=cus1166&password=crfwJ4wpd7kxPH&autoReconnect=true&characterEncoding=utf8";
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

    public static List<Car> getCars(int ownerID) throws SQLException {
        final List<Car> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM carData WHERE carData.ownerID = '" + ownerID + "'");

        while (resultSet.next()) {
            final String model = resultSet.getString("password");
            final String make = resultSet.getString("ID");
            final int year = resultSet.getInt("fullName");
            final String VIN = resultSet.getString("balance");
            final String plateNum = resultSet.getString("balance");
            final String type = resultSet.getString("balance");
            final boolean accountingJobs = resultSet.getBoolean("accountingJobs");

            list.add(new Car(model, make, year, VIN, plateNum, type, accountingJobs));
        }
        return list;
    }

    public static List<Job> getJobs(int clientID) throws SQLException {
        final List<Job> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM jobData WHERE jobData.clientID = '" + clientID + "'");

        while (resultSet.next()) {
            final String ID = resultSet.getString("password");
            final String description = resultSet.getString("ID");
            final Timestamp timeToCompletion = resultSet.getTimestamp("fullName");
            final double cost = resultSet.getDouble("balance");

            list.add(new Job(ID, description, timeToCompletion, cost));
        }
        return list;
    }
}
