import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocketManager extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ClientSocketManager() throws IOException {
        socket = new Socket("localhost", 8080);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Errored");
            throw new RuntimeException(e);
        }
    }

    public void write(String text) throws IOException {
        out.println(text);

        System.out.println("Job sent");
    }
}