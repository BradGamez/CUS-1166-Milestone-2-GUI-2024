import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocketManager extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ClientSocketManager(int clientID) throws IOException {
        socket = new Socket("localhost", 8080);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        //Send the client ID to the server
        out.println(clientID);
        System.out.println("Connected to the server as ID: " + clientID);
    }
    
    @Override
    public void run() {
        try {
            String inputLine;
            //Continuously listen for server notifications
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Notification from server: " + inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading server messages");
            throw new RuntimeException(e); 
            }
        }

    public void writeJob(String text) throws IOException {
        out.println(text);

        System.out.println("Job sent");
    }

    public void writeCar(String text) throws IOException {
        out.println(text);

        System.out.println("Car sent");
    }
    
    public void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Connection to server closed.");
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
