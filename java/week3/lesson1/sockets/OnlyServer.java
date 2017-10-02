package week3.lesson1.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OnlyServer {
    public static void main(String[] args) {
        new Thread(()->{
            ServerSocket consumer = null;
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                consumer = new ServerSocket(4444);
                socket = consumer.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataInputStream dis = new DataInputStream(inputStream);
            DataOutputStream dos = new DataOutputStream(outputStream);
            String message = null;
            while(true){
                Scanner scanner = new Scanner(System.in);
                try {
                    if(dis.available() > 0) {
                        message = dis.readUTF();
                        System.out.println(message);
                    }
                    else if(scanner.hasNext()) {
                        dos.writeUTF(message);
                        dos.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
