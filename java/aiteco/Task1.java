package aiteco;

import java.io.IOException;

/**
 * Created by vidok on 22.09.17.
 */
public class Task1 {
    public static void main(String[] args) {
        byte [] buffer = new byte[1000];
        double summ = 0;
        while(true){
            try {
                System.in.read(buffer);
                Double num = Double.parseDouble(new String(buffer));
                summ += num;
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println(summ);
        }

    }
}
