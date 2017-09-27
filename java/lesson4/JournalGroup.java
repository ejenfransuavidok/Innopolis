package lesson4;

import java.io.IOException;
import java.sql.Date;

/**
 * Created by vidok on 21.09.17.
 */
public class JournalGroup {

    public static void main(String[] args) {
        Group groupAll = new Group(
                1, "Группа 1 всех студентов");
        Group groupPresent = new Group(
                1, "Группа 1 присутствующих студентов");

        Student s01 = new Student(
                (short)1, "Иван", "Иванович", "Иванов", Date.parse("1995/07/07"));
        Student s02 = new Student(
                (short)1, "Петр", "Петрович", "Петров", Date.parse("1900/07/07"));
        Student s03 = new Student(
                (short)1, "Александр", "Александрович", "Александров", Date.parse("1950/07/07"));
        /**
         * @ Всего в группе
         */
        groupAll.addStudent(s01);
        groupAll.addStudent(s02);
        groupAll.addStudent(s03);
        /**
         * @ Кто посетил сегодня
         */
        groupPresent.addStudent(s01);
        groupPresent.addStudent(s03);
        Lesson lesson1 = new Lesson(Date.parse("1922/02/23"), "Урок какой-то", (short)1, "Артем");
        Journal journal = new Journal(lesson1, groupPresent.getStudents());
        try {
            MySerializationAction.serializeJournal(journal);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
