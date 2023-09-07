import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RestaurantClient {
    ObjectOutputStream oos;
    ObjectInputStream ois;
    RestaurantManager manager;
    Restaurant restaurant;
    Scanner scanner = new Scanner(System.in);

    public RestaurantClient() {
        try (Socket socket = new Socket("localhost", 12345)) {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject("restaurant");
            manager = (RestaurantManager) ois.readObject();
            System.out.println("Name of the Restaurant:");
            String name = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();
            for (int i = 0; i < manager.restaurants.size(); i++) {
                if (manager.restaurants.get(i).name.equals(name)) {
                    restaurant = manager.restaurants.get(i);
                    break;
                }
            }

            List<String> request;
            List<String> temp;
            request = (List<String>) ois.readObject();
            String restaurantName = request.get(0);
            for (String key : restaurant.requests.keySet()) {
                if (key.equals(restaurantName)) {
                    temp = restaurant.requests.get(key);
                    for (int i = 1; i < request.size(); i++) {
                        temp.add(request.get(i));
                    }
                    restaurant.requests.put(key, temp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
