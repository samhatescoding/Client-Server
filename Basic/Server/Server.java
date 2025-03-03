package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /** Error message for incorrect initialization arguments. */
    public static final String INIT_ERROR = "Server should be initialized with -p <port>";
    
    private Socket cs;
    private ServerSocket ss;
    private int port;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


    public Server(int port) {
        this.port = port;
        try{ this.ss = new ServerSocket(port); }
        catch (Exception e) {}

        while(true) {
            try{
                cs = ss.accept();
                this.dataInputStream = new DataInputStream(cs.getInputStream());
                this.dataOutputStream = new DataOutputStream(cs.getOutputStream());
                System.out.println("Server connected to client.");

                while(true){
                    String received = dataInputStream.readUTF();
                    System.out.println("Received: " + received);
                    this.dataOutputStream.writeUTF("Server received: " + received);
                }
            } catch (Exception e) {}
        }
    }


    public static void main(String[] args) {

        int port = Integer.parseInt(args[1]);
        Server server = new Server(port);
    }
}
