/**
 * Created by vidok on 18.09.17.
 */
import java.util.*;

public class lesson_01 {
    static Boolean b1 = new Boolean("true");
    public static void main(String [] varargs) {
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);
        System.out.println(b1);
        First a = new First();
        First b = null;
        System.out.println(~220);
        System.out.println(!true);
        TreeSet<Integer> tree = new TreeSet<Integer>();
        tree.add(10);
        tree.add(2);
        tree.add(5);
        tree.add(11);
        for(Integer elem : tree){
            System.out.println(elem);
        }
        tree.stream().forEach(e->System.out.println(e.toString()));
        try {
            b = a.clone();
        }
        catch(CloneNotSupportedException exp){
            System.out.println("into lesson_01 try{}catch(CloneNotSupportedException){}");
        }
        System.out.println(b.getClass());
        System.out.println(a instanceof Second);
    }
}

class First implements Cloneable {
    First(){
        System.out.println("First constructor");
    }

    protected First clone() throws CloneNotSupportedException{
        First ret = null;
        try {
            System.out.println("want to clone super is " + super.getClass());
            ret = (First)super.clone();
        }
        catch(CloneNotSupportedException exc){
            System.out.println("into First try{}catch(CloneNotSupportedException){}");
        }
        return ret;
    }
}

class Second extends First {
    Second(){
        System.out.println("Second constructor");
    }
}

class Third extends Second {
    Third(){
        System.out.println("Third constructor");
    }

    void fun(){
        System.out.println("Third class fun");
    }
}