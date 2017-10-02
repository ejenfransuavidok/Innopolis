package week3.lesson1.sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class OnlyClient {

    public static void main(String[] args) throws IOException {

        new Thread(()->{
            Socket clientSocket;
            OutputStream outputStream;
            DataOutputStream dos = null;
            InputStream inputStream;
            DataInputStream dis = null;
            while(true) {
                try {
                    clientSocket = new Socket("127.0.0.1", 4444);
                    outputStream = clientSocket.getOutputStream();
                    dos = new DataOutputStream(outputStream);
                    inputStream = clientSocket.getInputStream();
                    dis = new DataInputStream(inputStream);
                    break;
                } catch (IOException e) {
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
                String message = null;
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
