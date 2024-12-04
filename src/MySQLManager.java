import java.sql.*;
import java.util.*;

public class MySQLManager {
    private static final String uri = "jdbc:mysql://home.ddns.bradpersaud.xyz:3305/VCRTS?user=cus1166&password=&autoReconnect=true&characterEncoding=utf8&useSSL=false";
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

        return new Client(username, password, ID, fullName, balance, getClientSubmissions(ID));
    }

    public static void setClient(Client client) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("INSERT INTO VCRTS.accountData (username, password, ID) VALUES ('" + client.getUsername() +"', '" + client.getPassword() + "', '" + client.getID() +"');");
        statement.executeUpdate("INSERT INTO VCRTS.clientData (ID, fullName, balance) VALUES ('" + client.getID() +"', '" + client.getFullName() + "', '" + client.getBalance() +"');");
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
        statement.executeUpdate("INSERT INTO VCRTS.accountData (username, password, ID) VALUES ('" + owner.getUsername() +"', '" + owner.getPassword() + "', '" + owner.getID() +"');");
        statement.executeUpdate("INSERT INTO VCRTS.ownerData (ID, fullName, balance) VALUES ('" + owner.getID() +"', '" + owner.getFullName() + "', '" + owner.getBalance() +"');");
    }

    public static int getNextAccountID() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM VCRTS.accountData;");
        resultSet.next();
        int count = resultSet.getInt("count(*)");
        return count;
    }

    public static String getUsername(int ID) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM accountData WHERE accountData.ID = '" + ID + "';");
        resultSet.next();
        String name = resultSet.getString("username");
        return name;
    }
    public static List<Car> getCars(int ownerID) throws SQLException {
        final List<Car> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM carData WHERE carData.ownerID = '" + ownerID + "';");

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
        statement.executeUpdate("INSERT INTO VCRTS.carData (ownerID, model, make, year, VIN, plateNum, type, acceptingJobs) VALUES ('" + owner.getID() + "', '" + car.getModel() + "', '" + car.getMake() + "', '" + car.getYear() + "', '" + car.getVIN() + "', '" + car.getPlateNum() + "', '" + car.getType() + "', '" + car.isAcceptingJobs() +"');");
    }

    public static List<ClientSubmission> getClientSubmissions(int creatorID) throws SQLException {
        final List<ClientSubmission> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clientSubmissions WHERE clientSubmissions.creatorID = '" + creatorID + "';");

        while (resultSet.next()) {
            final String ID = resultSet.getString("ID");
            final String duration = resultSet.getString("duration");
            final String deadline = resultSet.getString("deadline");
            final String timestamp = resultSet.getString("timestamp");

            list.add(new ClientSubmission(ID, duration, deadline, timestamp));
        }
        return list;
    }

    public static void setClientSubmissions(ClientSubmission clientSubmission, int creatorID) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("INSERT INTO VCRTS.clientSubmissions (ID, duration, deadline, timestamp, creatorID) VALUES ('" + clientSubmission.getID() + "', '" + clientSubmission.getDuration() + "', '" + clientSubmission.getDeadline() + "', '" + clientSubmission.getTimestamp() + "', '" + creatorID + "');");
    }

    public static List<OwnerSubmission> getOwnerSubmissions(int creatorID) throws SQLException {
        final List<OwnerSubmission> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ownerSubmissions WHERE ownerSubmissions.creatorID = '" + creatorID + "';");

        while (resultSet.next()) {
            final String ID = resultSet.getString("ID");
            final String residencyTime = resultSet.getString("residencyTime");
            final String vin = resultSet.getString("vin");
            final String timestamp = resultSet.getString("timestamp");

            list.add(new OwnerSubmission(ID, vin, residencyTime, timestamp));
        }
        return list;
    }

    public static void setOwnerSubmissions(OwnerSubmission ownerSubmission, int creatorID) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("INSERT INTO VCRTS.ownerSubmissions (ID, vin, residencyTime, timestamp, creatorID) VALUES ('" + ownerSubmission.getID() + "', '" + ownerSubmission.getVin() + "', '" + ownerSubmission.getResidencyTime() + "', '" + ownerSubmission.getTimestamp() + "', '" + creatorID + "');");
    }
}
