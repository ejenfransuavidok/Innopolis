package lesson3;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vidok on 21.09.17.
 */

class SourceFileFormatException extends Exception {
    public SourceFileFormatException(String s) {
        super(s);
    }
}

public class SerializatorDeserializator {

    private static ArrayList<Student> serializeStudents;
    private static ArrayList<Student> deserializeStudents;

    public static void collectFromTextFile(String fname) throws SourceFileFormatException {
        String data = Commander.readFromFile(fname);
        String [] explode = data.split("\n");
        if(explode.length >0){
            serializeStudents = new ArrayList<Student>();
            for(String s : explode){
                Pattern r = Pattern.compile("Name=([A-Za-z]+) age=([\\d]+)");
                Matcher m = r.matcher(s);
                if(m.find() && m.groupCount() == 2){
                    String name = m.group(1);
                    String age = m.group(2);
                    serializeStudents.add(new Student(name, Integer.getInteger(age)));
                }
                else {
                    throw new SourceFileFormatException("Source file must have format like: Name=Somebody age=Something");
                }
            }
        }
    }

    public static void serializeCollection(String fname){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname)))
        {
            if(serializeStudents.size() != 0){
                oos.writeObject(serializeStudents);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }

    public static void deSerializeCollection(String fname){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname)))
        {
            deserializeStudents = (ArrayList<Student>)ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
