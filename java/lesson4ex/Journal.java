package lesson4ex;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by vidok on 21.09.17.
 */
public class Journal implements Serializable {

    ArrayList<Lesson> lessons;
    ArrayList<PresentStudent> presentStudents;

    public Journal(ArrayList<Lesson> lessons){
        presentStudents = new ArrayList<>();
        this.lessons = lessons;
        if(this.lessons.size() > 0){
            for(Lesson lesson : this.lessons){
                for(Student student : lesson.getGroup().getStudents()) {
                    PresentStudent presentStudent =
                            new PresentStudent(student, lesson.getGroup(), lesson);
                    presentStudents.add(presentStudent);
                }
            }
        }
    }

    public ArrayList<PresentStudent> getPresentStudents(){
        return this.presentStudents;
    }

    public static ArrayList<PresentStudent> deSerializeCollection(String fname){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname)))
        {
            return (ArrayList<PresentStudent>)ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void serializePresentStudents(String fname, ArrayList<PresentStudent> presentStudents){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname)))
        {
            if(presentStudents.size() != 0){
                oos.writeObject(presentStudents);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }
    }

}
