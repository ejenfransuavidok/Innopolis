package week2.Lesson1.WarAndPeace;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by vidok on 25.09.17.
 */
public class Producer extends Thread {

    final static public BlockingQueue<String> data = new LinkedBlockingQueue<>();
    public static volatile boolean stop = false;

    public void run(){
        try(
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(new FileInputStream("book1.txt"), "WINDOWS-1251" ))
            ) {
            String temp;
            while((temp = reader.readLine()) != null){
                data.add(new String(temp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stop = true;
        System.out.println("Producer stop...");
    }

}
