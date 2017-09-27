package week2.lesson3.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        printParent();
//        Child child = new Child();
//        printChaild(child);
//        modifyPrivate(child);
//        printChaild(child);
        invokePrivate(new Child());
    }

    public static void invokePrivate(Child child) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = child.getClass().getDeclaredMethod("prvateProcedure", int.class);
        method.setAccessible(true);
        method.invoke(child, 5);
    }

    public static void modifyPrivate(Child child) throws NoSuchFieldException, IllegalAccessException {
        Field field = child.getClass().getDeclaredField("privateLong");
        field.setAccessible(true);
        field.set(child, 100L);
    }

    public static void printParent(){
        for(Field f: Parent.class.getDeclaredFields()){
            System.out.println(f.getName() + " " + f.getType().getName() + " " + f.getModifiers());
        }
        for(Method m: Parent.class.getDeclaredMethods()){
            System.out.println(m.getName() + " " + m.getReturnType().getName());
            for(Parameter p: m.getParameters()){
                System.out.println("\t" + p.getName() + " " + p.getType().getName());
            }
        }
    }

    public static void printChaild(Child child){
        for(Field f: child.getClass().getFields()){
            System.out.println(f.getName() + " " + f.getType().getName() + " " + f.getModifiers());
        }
        for(Method m: child.getClass().getMethods()){
            System.out.println(m.getName() + " " + m.getReturnType().getName());
            for(Parameter p: m.getParameters()){
                System.out.println("\t" + p.getName() + " " + p.getType().getName());
            }
        }
    }

}
