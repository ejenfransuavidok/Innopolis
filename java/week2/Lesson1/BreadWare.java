package week2.Lesson1;

/**
 * Created by vidok on 25.09.17.
 */
public class BreadWare {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
        System.out.println("Bread are " + counter);
    }
}
