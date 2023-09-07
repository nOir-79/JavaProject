import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static RestaurantManager restaurantManager;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                restaurantManager = new RestaurantManager();
                System.out.println("Before Client");
                Socket clientSocket = serverSocket.accept();
                System.out.println("After Client");
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                oos.writeObject(restaurantManager);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
