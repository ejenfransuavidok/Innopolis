package lesson4ex;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Group implements Serializable {

    private final int groupNum;
    private final String groupName;
    private Set<Student> students;

    public Group(){
        groupNum    = 0;
        groupName   = "";
    }

    public Group(int groupNum, String groupName){
        this.groupNum   = groupNum;
        this.groupName  = groupName;
        this.students   = new HashSet<>();
    }

    public Group(Group group){
        this.groupNum = group.getGroupNum();
        this.groupName = group.getGroupName();
        this.students = new HashSet<>();
        for(Student student : group.students){
            Student newStudent = new Student(student);
            int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            newStudent.setPresent(randomNum == 0);
            this.addStudent(newStudent);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupNum != group.groupNum) return false;
        return groupName.equals(group.groupName);
    }

    @Override
    public int hashCode() {
        int result = groupNum;
        result = 31 * result + groupName.hashCode();
        return result;
    }

    public boolean containsStudent(Student student){
        return students.contains(student);
    }

    public boolean removeStudent(Student student){
        return students.remove(student);
    }

    public boolean addStudent(Student student){
        return students.add(student);
    }

    public int getGroupNum() {
        return groupNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
