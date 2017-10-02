package week3.lesson1.sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.240.22.194", 4444);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);

        while(true){
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            dos.writeUTF(message);
            dos.flush();
        }
    }
}
