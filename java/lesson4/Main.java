package lesson4;

import java.io.IOException;
import java.sql.Date;

/**
 * Created by vidok on 21.09.17.
 */
public class Main {
    public static void main(String[] args) {
        Group group01 = new Group(
                1, "Группа 1");
        Student s01 = new Student(
                (short)1, "Иван", "Иванович", "Иванов", Date.parse("1995/07/07"));
        Student s02 = new Student(
                (short)1, "Петр", "Петрович", "Петров", Date.parse("1900/07/07"));
        Student s03 = new Student(
                (short)1, "Александр", "Александрович", "Александров", Date.parse("1950/07/07"));
        group01.addStudent(s01);
        group01.addStudent(s02);
        group01.addStudent(s03);
        Lesson lesson1 = new Lesson(Date.parse("1922/02/23"), "Урок какой-то", (short)1, "Артем");
        Journal journal = new Journal(lesson1, group01.getStudents());
        try {
            MySerializationAction.serializeGroup(group01);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Group restoredGroup = MySerializationAction.readGroup("group.txt");
            System.out.println(restoredGroup.getGroupName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
