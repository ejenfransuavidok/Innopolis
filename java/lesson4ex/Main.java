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
    private static final String PRESENT_STUDENTS_XML = "presentStudents.xml";

    public static void main(String[] args) throws Exception {

        File file = new File(PRESENT_STUDENTS_XML);
        if(!file.exists()) {
            Init init = new Init();
            JournalXML journal = new JournalXML(init.getLessons());
            JournalXML.serializePresentStudents(PRESENT_STUDENTS_XML, journal.getPresentStudents());
        }
        else{
            System.out.println("File " + PRESENT_STUDENTS_XML + " exist!, deserializing...");
            ArrayList<PresentStudent> presentStudents = JournalXML.deSerializeCollection(PRESENT_STUDENTS_XML);
//            ArrayList<PresentStudent> presentStudents = Journal.deSerializeCollection(PRESENT_STUDENTS_DAT);
//            Reporter reporter = new Reporter(presentStudents);
//            System.out.println("journal_student.dat, journal_group.dat, journal_lesson.dat done!");
        }
        System.out.println("END");
    }

}
