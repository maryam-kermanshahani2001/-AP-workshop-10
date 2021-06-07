import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Server.
 */
public class Server {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        int count = 0;
        try {
            ServerSocket welcomingSocket = new ServerSocket(7660);
            System.out.println("server started. \n waiting for an client");
            while (count < 3) {
                Socket connectionSocket = welcomingSocket.accept();
                count++;
                System.out.println("client accepted");
                pool.execute(new Handler(connectionSocket, count));
            }
            pool.shutdown();
            System.out.println("closing server");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Run.
     */
    public void run() {

    }
}


