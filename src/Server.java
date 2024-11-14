import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is listening on port 8080");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    String inputLine;

                    System.out.println("Client connected");
                    out.println("Hello from server!");

                    while((inputLine = in.readLine()) != null){
                        System.out.println(inputLine);
                        out.println("Job received!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
