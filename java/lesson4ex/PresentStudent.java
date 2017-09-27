package lesson4ex;

import java.io.Serializable;

/**
 * Created by vidok on 22.09.17.
 */
public class PresentStudent implements Serializable {
    private Student student;
    private Group group;
    private Lesson lesson;

    public PresentStudent(Student student, Group group, Lesson lesson){
        this.student = student;
        this.group = group;
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
