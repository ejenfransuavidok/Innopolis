package week2.Lesson1.WarAndPeace;

/**
 * Created by vidok on 25.09.17.
 */
public class Consumer extends Thread {

    public static int counter = 0;
    public static volatile boolean stop = false;

    public void run(){
        while(!Producer.stop) {
            while(Producer.data.size() != 0){
                try {
                    String value = Producer.data.take();
                    for(String item : value.split(" ")){
                        counter += item.replaceAll("[^a-яА-Я]", "").equals("страдание") ? 1 : 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Consumer stop...");
        stop = true;
    }

}
