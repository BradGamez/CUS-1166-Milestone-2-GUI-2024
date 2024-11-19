import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Server {
	private static final Map<Integer, Socket> clientSockets = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is listening on port 8080");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Client connected");

                //Read client ID on connection
                String clientIDLine = in.readLine();
                int clientID = Integer.parseInt(clientIDLine.trim());
                clientSockets.put(clientID, socket);
                System.out.println("Registered Client ID: " + clientID);
                
                //Separate thread for handling client communication
                new Thread(() -> handleClient(socket, clientID)).start();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
    }
    
    private static void handleClient(Socket socket, int clientID) {
    	try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
    		String inputLine;
    		while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from Client " + clientID + ": " + inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void notifyClient(int clientID, String message) {
        Socket clientSocket = clientSockets.get(clientID);
        if (clientSocket != null) {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                System.err.println("Error notifying client " + clientID);
                e.printStackTrace();
            }
        }
    }
}
