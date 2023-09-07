import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RestaurantClientThread implements Runnable {

    private Socket clientSocket;
    private String id;

    public ObjectOutputStream oos;
    public ObjectInputStream ois;

    RestaurantClientThread(Socket socket, String id) {
        clientSocket = socket;
        this.id = id;
        try {
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        RestaurantClient restaurantClient = new RestaurantClient();
    }
}
