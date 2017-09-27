package lesson5ex;

/**
 * Created by vidok on 22.09.17.
 */
public class ThreadsTest {

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }

}

class Producer extends Thread {

    public static Integer counter = new Integer(0);

    public void run() {
        while(true) {
            counter++;
            try {
                sleep(100);
                //System.out.println("Producer inc counter = " + Producer.counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer extends Thread {

    public void run() {
        while(true) {
            try {
                sleep(100);
                System.out.println("Consumer read counter = " + Producer.counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}