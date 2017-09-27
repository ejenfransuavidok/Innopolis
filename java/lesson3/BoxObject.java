package lesson3;

/**
 * Created by vidok on 20.09.17.
 */
public class BoxObject {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void print(){
        System.out.println(value);
    }
}
