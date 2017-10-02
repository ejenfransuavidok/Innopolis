package week3.lesson1.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import static consts.ServerConsts.PORT;

public class DatagramServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(PORT);

        byte[] packetMas = new byte[1024];
        DatagramPacket packet = new DatagramPacket(packetMas, 1024);
        ds.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println(message);
        ds.close();
    }
}
