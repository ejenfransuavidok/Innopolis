package lesson4ex;

import week2.lesson3.reflection.Child;
import week2.lesson3.reflection.Parent;

import java.io.File;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**
 * Created by vidok on 21.09.17.
 */
public class Main {

    private static final String PRESENT_STUDENTS_DAT = "presentStudents.dat";

    public static ArrayList<String> getGenericName(Field field){
        ArrayList<String> result = new ArrayList<String>();
        Type genericFieldType = field.getGenericType();
        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for(Type fieldArgType : fieldArgTypes){
                Class fieldArgClass = (Class) fieldArgType;
                result.add(new String(fieldArgClass.toString()));
            }
        }
        return result;
    }

    public static void printClass(Class<?> cls, String tab){
        for(Field f: cls.getDeclaredFields()){
            System.out.println(tab + "VARIAB: " + f.getName() + " " + f.getType().getName() + " " + f.getModifiers());
            for(String genericName : getGenericName(f)) {
                System.out.println(tab + "GENERIC: " + genericName);
            }
            printClass(f.getType(), tab + "\t");
        }
        for(Method m: cls.getDeclaredMethods()){
            System.out.print(tab + "METHOD: " + m.getName() + " " + m.getReturnType().getName() + "(");
            for(Parameter p: m.getParameters()){
                System.out.print(tab + p.getName() + ", " + p.getType().getName());
            }
            System.out.println(");");
        }
    }

    public static void main(String[] args) {
        /**
         * Рефлексия
         */
        printClass(Journal.class, "");
        exit(0);
        /**
         * Рефлексия конец!
         */
        File file = new File("presentStudents.dat");
        if(!file.exists()) {
            Init init = new Init();
            Journal journal = new Journal(init.getLessons());
            Journal.serializePresentStudents(PRESENT_STUDENTS_DAT, journal.getPresentStudents());
        }
        else{
            System.out.println("File " + PRESENT_STUDENTS_DAT + " exist!");
            /**
             * @ создаем 3 отчета по первому студенту, по первой группе, по первому уроку
             */
            ArrayList<PresentStudent> presentStudents = Journal.deSerializeCollection(PRESENT_STUDENTS_DAT);
            Reporter reporter = new Reporter(presentStudents);
            System.out.println("journal_student.dat, journal_group.dat, journal_lesson.dat done!");
        }
        System.out.println("END");
    }

}
