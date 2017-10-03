package week3.lesson1.n2nchat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class N2NServer {

    public static void main(String[] args) throws IOException {
        ArrayList<N2NClientThread> clients = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true){
            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("client have come...");
            N2NClientThread n2nclientThread = new N2NClientThread(socket);
            clients.add(n2nclientThread);
            n2nclientThread.start();
        }
    }

}
