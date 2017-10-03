package week3.lesson1.n2nchat;

import java.io.*;
import java.net.Socket;

public class N2NClientThread extends Thread {

    private final Socket socket;
    public static final Message message = new Message();
    public static Integer clientsCounter = 0;

    N2NClientThread(Socket socket){
        super();
        this.socket = socket;
        clientsCounter++;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);
            while(true){
                synchronized (message) {
                    if(message.getHaveNewMessage() != 0){
                        Integer tmp = message.getHaveNewMessage() - 1;
                        message.setHaveNewMessage(tmp);
                        dos.writeUTF(message.getMessage());
                        dos.flush();
                    }
                    else if (dis.available() > 0) {
                        String msg = dis.readUTF();
                        message.setMessage(msg);
                        message.setHaveNewMessage(clientsCounter);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
