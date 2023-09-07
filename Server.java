import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static RestaurantManager restaurantManager;
    public static List<RestaurantClientThread> activeRestaurants = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                String client;
                String id;
                restaurantManager = new RestaurantManager();
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                client = (String) ois.readObject();
                id = (String) ois.readObject();
                oos.writeObject(restaurantManager);
                if (client.equals("customer")) {

                } else if (client.equals("restaurant")) {
                    RestaurantClientThread restaurantClient = new RestaurantClientThread(clientSocket, id);
                    activeRestaurants.add(restaurantClient);
                    Thread restaurantThread = new Thread(restaurantClient);
                    restaurantThread.start();
                } else if (client.equals("admin")) {

                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
