import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;

public class StorageManager {

    public static void setCar(Car car) {
        try {
            File file = new File("./cars/" + car.getVIN() + ".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./cars/" + car.getVIN() + ".txt");
            fileWriter.write(car.toString());
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    public static Car getCar(String vin) {
        try {
            LinkedList list = new LinkedList();
            File file = new File("./cars/" + vin + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            return new Car(list.get(0).toString(), list.get(1).toString(), Integer.parseInt(list.get(2).toString()), list.get(3).toString(), list.get(4).toString(), list.get(5).toString(), true);
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            return null;
        }
    }

    public static void setClient(Client client) {
        try {
            File file = new File("./clients/" + client.getID() + ".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./clients/" + client.getID() + ".txt");
            fileWriter.write(client.toString());
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    public static Client getClient(String clientID) {
        try {
            LinkedList list = new LinkedList();
            File file = new File("./clients/" + clientID + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            return new Client(list.get(0).toString(), list.get(1).toString(), Integer.parseInt(list.get(2).toString()), list.get(3).toString(), Double.valueOf(list.get(4).toString()));
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            return null;
        }
    }

    public static void setOwner(Owner owner) {
        try {
            File file = new File("./owners/" + owner.getID() + ".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./owners/" + owner.getID() + ".txt");
            fileWriter.write(owner.toString());
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    public static Owner getOwner(String ownerID, String carVIN) {
        try {
            LinkedList list = new LinkedList();
            File file = new File("./owners/" + ownerID + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            return new Owner(list.get(0).toString(), list.get(1).toString(), Integer.parseInt(list.get(2).toString()), list.get(3).toString(), Double.valueOf(list.get(4).toString()), getCar(carVIN));
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            return null;
        }
    }

    public static void setJob(Job job) {
        try {
            File file = new File("./jobs/" + job.getID() + ".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./jobs/" + job.getID() + ".txt");
            fileWriter.write(job.toString());
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    public static Job getJob(String jobID) {
        try {
            LinkedList list = new LinkedList();
            File file = new File("./jobs/" + jobID + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            return new Job(list.get(0).toString(), list.get(1).toString(), Timestamp.valueOf(list.get(2).toString()), Double.valueOf(list.get(3).toString()));
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            return null;
        }
    }
}
