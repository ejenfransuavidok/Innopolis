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
                System.out.println("socket accepted...");
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataInputStream dis = new DataInputStream(inputStream);
            DataOutputStream dos = new DataOutputStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String message = null;
            while(true){
                try {
                    if(dis.available() > 0) {
                        message = dis.readUTF();
                        System.out.println(message);
                    }
                    else if(br.ready()) {
                        message = br.readLine();
                        dos.writeUTF(message);
                        dos.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
