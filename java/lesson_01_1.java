/**
 * Created by vidok on 18.09.17.
 */
class A { A() { System.out.println("A"); } }
class B { B() { System.out.println("B"); } }
class C { C() { System.out.println("C"); } }
public class lesson_01_1 extends C {
    private A objA = new A();
    private static B objB = new B();
    public lesson_01_1() { System.out.println("D"); }
    public static void main(String [] args){
        new lesson_01_1();
    }
}
