package week3.lesson1.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which room do you want (port number) ?");
        Integer port = Integer.valueOf(scanner.next());

        Thread tServer = new Thread(){
            public void run() {
                ServerSocket consumer = null;
                Socket socket = null;
                try {
                    consumer = new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting for client...");
                try {
                    socket = consumer.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("We got client...");
                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DataInputStream dis = new DataInputStream(inputStream);
                while(true){
                    String message = null;
                    try {
                        message = dis.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(message);
                }
            }
        };

        tServer.start();

        Thread tClient = new Thread(){
            public void run(){
                Socket socket = null;
                try {
                    socket = new Socket("localhost", port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OutputStream outputStream = null;
                try {
                    outputStream = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DataOutputStream dos = new DataOutputStream(outputStream);

                while(true){
                    Scanner scanner = new Scanner(System.in);
                    String message = scanner.next();
                    try {
                        dos.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        tClient.start();
    }

}
