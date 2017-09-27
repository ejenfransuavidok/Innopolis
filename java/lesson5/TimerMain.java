package lesson5;

/**
 * Created by vidok on 22.09.17.
 */
public class TimerMain {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Counter counter5 = new Counter(5);
        Counter counter7 = new Counter(7);
        Counter counter15 = new Counter(15);
        Counter counter17 = new Counter(17);
//        counter5.start();
//        counter7.start();
//        counter15.start();
//        counter17.start();
//        timer.start();
        System.out.println(new Integer(2).getClass());
    }
}

class Timer extends Thread {
    @Override
    public void run() {
        while(true){
            System.out.println(this.getName() + " before synchronized...");
            try { Thread.sleep(1000); }
            catch (InterruptedException e) {}
            synchronized (Counter.counter){
                System.out.println(this.getName() + " before wait...");
//                   Counter.counter.wait(1000);
                Counter.counter.setValue(Counter.counter.getValue() + 1);
                System.out.println(this.getName() + " before notifyAll...");
                Counter.counter.notifyAll();
                System.out.println(this.getName() + " after notifyAll...");
            }
            System.out.println(this.getName() + " exit synchronized...");
        }
    }
}

class Counter extends Thread {

    public static final Incrementor counter = new Incrementor(0);
    private Integer interval;

    public Counter(Integer interval){
        this.interval = interval;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println(this.getName() + " before synchronized...");
                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                synchronized (Counter.counter){
                    System.out.println(this.getName() + " before wait...");
                    Counter.counter.wait();
                    System.out.println(this.getName() + " after wait...");
                    if(Counter.counter.getValue() % interval == 0) {
                        System.out.println("Time : + " + interval + " seconds...");
                    }
                }
                System.out.println(this.getName() + " exit synchronized...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Incrementor{
    int value = 0;

    public Incrementor(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}