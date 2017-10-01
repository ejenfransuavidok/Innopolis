package lesson4ex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by vidok on 22.09.17.
 */
public class Reporter {

    private ArrayList<PresentStudent> presentStudents;
    private ArrayList<PresentStudent> reportByStudent;
    private ArrayList<PresentStudent> reportByGroup;
    private ArrayList<PresentStudent> reportByLesson;

    public Reporter(ArrayList<PresentStudent> presentStudents) throws Exception {
        this.presentStudents = presentStudents;
        /**
         * @ создаем 3 отчета по первому студенту, по первой группе, по первому уроку
         */
        reportByStudent = byStudent();
        reportByLesson = byLesson();
        reportByGroup = byGroup();
        Journal.serializePresentStudents("journal_student.dat", reportByStudent);
        Journal.serializePresentStudents("journal_group.dat", reportByGroup);
        Journal.serializePresentStudents("journal_lesson.dat", reportByLesson);
    }

    public ArrayList<PresentStudent> byGroup(){
        Group group = this.presentStudents.get(0).getGroup();
        ArrayList<PresentStudent> result = new ArrayList<>();
        for(PresentStudent ps : this.presentStudents){
            if(group.equals(ps.getGroup())){
                result.add(ps);
            }
        }
        return result;
    }

    public ArrayList<PresentStudent> byStudent(){
        Student student = this.presentStudents.get(0).getStudent();
        ArrayList<PresentStudent> result = new ArrayList<>();
        for(PresentStudent ps : this.presentStudents){
            if(student.equals(ps.getStudent())){
                result.add(ps);
            }
        }
        return result;
    }

    public ArrayList<PresentStudent> byLesson(){
        Lesson lesson = this.presentStudents.get(0).getLesson();
        ArrayList<PresentStudent> result = new ArrayList<>();
        for(PresentStudent ps : this.presentStudents){
            if(lesson.equals(ps.getLesson())){
                result.add(ps);
            }
        }
        return result;
    }

}
