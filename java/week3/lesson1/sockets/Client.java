package week3.lesson1.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        /**
         * сервер
         */
        new Thread(()->{
            ServerSocket consumer = null;
            Socket socket = null;
            InputStream inputStream = null;
            try {
                consumer = new ServerSocket(4444);
                socket = consumer.accept();
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataInputStream dis = new DataInputStream(inputStream);
            String message = null;
            while(true){
                try {
                    message = dis.readUTF();
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /**
         * клиент
         */
        new Thread(()->{
            Socket clientSocket = null;
            OutputStream outputStream = null;
            DataOutputStream dos = null;
            while(true) {
                try {
                    clientSocket = new Socket("10.243.1.47", 4444);
                    outputStream = clientSocket.getOutputStream();
                    dos = new DataOutputStream(outputStream);
                    break;
                } catch (Exception e) {
                    System.out.println("socket refused...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            while(true){
                Scanner scanner = new Scanner(System.in);
                String message = scanner.next();
                try {
                    dos.writeUTF(message);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
