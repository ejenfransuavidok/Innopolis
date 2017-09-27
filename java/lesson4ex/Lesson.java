package lesson4ex;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vidok on 21.09.17.
 */
public class Lesson implements Serializable {
    private final long dateTime;
    private final String topic;
    private final short roomNum;
    private final String teacher;
    /**
     * @ Группа, присутствующая на занятиях
     */
    private Group group;
    /**
     * @ Студенты, по факту присутствовавшие на занятии
     */
    private HashSet<Student> presented;

    public Lesson(long dateTime, String topic, short roomNum, String teacher, Group group){
        this.dateTime = dateTime;
        this.topic = topic;
        this.roomNum = roomNum;
        this.teacher = teacher;
        this.group = group;
        this.presented = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (dateTime != lesson.dateTime) return false;
        if (roomNum != lesson.roomNum) return false;
        if (!topic.equals(lesson.topic)) return false;
        return teacher.equals(lesson.teacher);
    }

    @Override
    public int hashCode() {
        int result = (int) (dateTime ^ (dateTime >>> 32));
        result = 31 * result + topic.hashCode();
        result = 31 * result + (int) roomNum;
        result = 31 * result + teacher.hashCode();
        return result;
    }

    public boolean appendStudentPresented(Student student){
        return this.presented.add(student);
    }

    public boolean checkStudentPresented(Student student){
        return  this.presented.contains(student);
    }

    public long getDateTime() {
        return dateTime;
    }

    public String getTopic() {
        return topic;
    }

    public short getRoomNum() {
        return roomNum;
    }

    public String getTeacher() {
        return teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public HashSet<Student> getPresented() {
        return presented;
    }

    public void setPresented(HashSet<Student> presented) {
        this.presented = presented;
    }
}
