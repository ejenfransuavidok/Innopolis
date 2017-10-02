package week3.lesson1.sockets;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class DatagramClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();

        InetAddress ip = InetAddress.getByName("127.0.0.1");

        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), ip, 4444);

        ds.send(packet);
        ds.close();
    }
}
