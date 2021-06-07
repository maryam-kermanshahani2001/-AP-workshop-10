import java.io.*;
import java.net.Socket;

/**
 * The type Handler.
 */
public class Handler implements Runnable{

    private Socket connectionSocket;
    private int clientNum;

    /**
     * Instantiates a new Handler.
     *
     * @param connectionSocket the connection socket
     * @param clientNum        the client num
     */
    public Handler(Socket connectionSocket, int clientNum) {
        this.connectionSocket = connectionSocket;
        this.clientNum = clientNum;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            String str;
            String response = "";
            do {
                str = dataInputStream.readUTF();
                System.out.println(str);
                response += str + " || ";
                System.out.println(response);
                dataOutputStream.writeUTF(response);
            }while (!str.equals("over"));
            System.out.print("All messages sent.\nClosing client ... ");
            dataOutputStream.close();
            dataInputStream.close();
            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
