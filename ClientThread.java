import java.net.Socket;

public class ClientThread {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 12345)) {
            Client client = new Client();
            client.Run(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
