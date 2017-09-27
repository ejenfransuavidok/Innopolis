package lesson3;

/**
 * Created by vidok on 20.09.17.
 */
public class Pair<T extends Number> {

    private T field1;
    private T field2;

    public Pair(T one, T two){
        field1 = one;
        field2 = two;
    }

    public Number plus(){
        return field1.doubleValue() + field2.doubleValue();
    }

    public Number minus(){
        return field1.doubleValue() - field2.doubleValue();
    }

    public Number mul(){
        return field1.doubleValue() * field2.doubleValue();
    }

    public Number div(){
        if(field2.doubleValue() == 0)
            throw new IllegalArgumentException();
        return field1.doubleValue() / field2.doubleValue();
    }

    public static void main(String[] args) {
        Pair<Integer> p = new Pair(5, 9.5);
        System.out.println("Div   = " + p.div());
        System.out.println("Mul   = " + p.mul());
        System.out.println("Minus = " + p.minus());
        System.out.println("Plus  = " + p.plus());
        int a = 1/0;
        System.out.println("a = " + a);
    }

}
