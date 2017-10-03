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
                //System.out.println("pred synchronized : " + this.getId());
                synchronized (message) {
                    //System.out.println("in up synchronized : " + this.getId());
                    if(message.getHaveNewMessage() != 0){
                        Integer tmp = message.getHaveNewMessage() - 1;
                        message.setHaveNewMessage(tmp);
                        /**
                         * сами себе не отправляем
                         */
                        if(message.getId() != this.getId()) {
                            System.out.println(">>>> : " + this.getId());
                            dos.writeUTF(message.getMessage());
                            dos.flush();
                        }
                    }
                    else if (dis.available() > 0) {
                        String msg = dis.readUTF();
                        message.setMessage(msg);
                        message.setHaveNewMessage(clientsCounter);
                        message.setId(this.getId());
                    }
                    //System.out.println("in down synchronized : " + this.getId());
                    message.wait(100);
                }
                //System.out.println("after synchronized : " + this.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
