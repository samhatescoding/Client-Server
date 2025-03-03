import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * Client class to establish a connection to a server and manage communication.
 */
public class Client {

    public static final String INIT_ERROR = "Client should be initialized with -h <host> -p <port>";
    Socket socket;
    String host;
    int port;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try{
            this.socket = new Socket(this.host, this.port);
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client connected to server.");
        } catch (Exception e) { }
    }


    public static void main(String[] args) {
        
        int port = Integer.parseInt(args[3]);
        String host = args[1]; 
        Client client = new Client(host, port);

        /* Writes to the Server */
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            if (input == "") break;
            try{
                client.dataOutputStream.writeUTF(input);
                System.out.println(client.dataInputStream.readUTF());
            } catch (Exception e) {}
        }
        scanner.close();
    }
}
