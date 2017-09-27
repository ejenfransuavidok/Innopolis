package Test;

import java.util.ArrayList;

public class App {
    static /*volatile*/ int counter = 0;
    public static void main(String[] args) throws InterruptedException{

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
//                        try {
//                            Thread.sleep(Math.round(1*Math.random()));
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        counter++;
                        System.out.println(counter);
                    }
                }
            }));
        }

        for (Thread thread :
                threads) {
            thread.start();
            //thread.join();
        }
        //System.out.println(counter);
    }

}
