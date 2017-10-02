package week3.lesson1.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServerOneThread {
    public static void main(String[] args) throws IOException {
        System.out.println("Please write to me...");
        while(true){
            ServerSocket serverSocket = new ServerSocket(5555);
            Socket clientSocket = new Socket("localhost", 5555);
            OutputStream outputStream = clientSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);

            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("We got client...");
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            /**
             * пишем
             */
            dos.writeUTF(message);
            dos.flush();
            /**
             * читаем
             */
            message = dis.readUTF();
            System.out.println(message);

            serverSocket.close();
            clientSocket.close();
        }
    }
}
