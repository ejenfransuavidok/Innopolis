package week2.Lesson1.WarAndPeace;

/**
 * Created by vidok on 25.09.17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
        while(Consumer.stop == false) {}
        System.out.println("countOf(страдание) = " + Consumer.counter);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
