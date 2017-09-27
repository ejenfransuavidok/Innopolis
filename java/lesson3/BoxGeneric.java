package lesson3;

/**
 * Created by vidok on 20.09.17.
 */
public class BoxGeneric<T extends Comparable> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void print(){
        System.out.println(value);
    }

    public int compareTo(T o){
        return this.value.compareTo(o);
    }

}
