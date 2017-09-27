package week2.Lesson1;

/**
 * Created by vidok on 25.09.17.
 */
public class Summator extends Thread {
    private final static BreadWare breadW = new BreadWare();
    private final static MilkWare milkW = new MilkWare();
    private final static BearWare bearW = new BearWare();
    private int countBread = 0;
    private int countMilk = 0;
    private int countBear = 0;
    final static int MAX_THREADS = 1;
    private static int breadSemafor = MAX_THREADS;
    private static int milkSemafor = MAX_THREADS;
    private static int bearSemafor = MAX_THREADS;

    public Summator(int countBread, int countMilk, int countBear){
        this.countBread = countBread;
        this.countMilk = countMilk;
        this.countBear = countBear;
    }

    public void run(){
        addProduct();
    }

    public void addProduct(){
        addBread(countBread);
        addMilk(countMilk);
        addBear(countBear);
    }

    public static void addBread(int count){
        //synchronized (breadW){
        while(breadSemafor == 0) {}
        breadSemafor--;
        breadW.setCounter(breadW.getCounter() + count);
        breadSemafor++;
        //}
    }

    public static void addMilk(int count){
        //synchronized (milkW){
        while(milkSemafor == 0) {}
        milkSemafor--;
        milkW.setCounter(milkW.getCounter() + count);
        milkSemafor++;
        //}
    }

    public static void addBear(int count){
        //synchronized (bearW){
        while(bearSemafor == 0) {}
        bearSemafor--;
        bearW.setCounter(bearW.getCounter() + count);
        bearSemafor++;
        //}
    }

}
