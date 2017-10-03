package week3.lesson1.n2nchat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class N2NClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 4444);

        OutputStream outputStream = clientSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);
        InputStream inputStream = clientSocket.getInputStream();
        DataInputStream dis = new DataInputStream(inputStream);
        InputStreamReader inputStreamConsole = new InputStreamReader(System.in);
        BufferedReader bufferedInputStreamConsole = new BufferedReader(inputStreamConsole);
        while (true) {
            if (dis.available() > 0) {
                System.out.println("<<<< : " + dis.readUTF());
            }
            else if(bufferedInputStreamConsole.ready()){
                String message = bufferedInputStreamConsole.readLine();
                dos.writeUTF(message);
                System.out.println(">>>> : " + message);
            }
        }
    }
}
