package week2.Lesson1;

/**
 * Created by vidok on 25.09.17.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Summator s1 = new Summator(10,0,5);
        Summator s2 = new Summator(0,10,20);
        Summator s3 = new Summator(100,1,10);
        Summator s4 = new Summator(230,0,1);
        Summator s5 = new Summator(1,440,550);
        Summator s6 = new Summator(1,450,120);
        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s5.start();
        s6.start();
/*
        s1.join();
        s2.join();
        s3.join();
        s4.join();
        s5.join();
        s6.join();
*/
        System.out.println("END");
    }
}
