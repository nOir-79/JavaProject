import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345, 0, null)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                while (true) {
                    System.out.println(reader.readLine());
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
