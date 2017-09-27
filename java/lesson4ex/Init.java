package lesson4ex;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by vidok on 21.09.17.
 */
public class Init {

    private ArrayList<Lesson> lessonsAll;
    private ArrayList<Group> groupsAll;
    private final String fnameStudents = "students.txt";
    private final String fnameLessons = "lessons.txt";
    private final int MAX_GROUP_SIZE = 8;

    public Init(){
        readStudents(fnameStudents);
        readLessons(fnameLessons);
    }

    private Group getModifiedGroup(Group group){
        return new Group(group);
    }

    private void readLessons(String fname){
        lessonsAll = new ArrayList<>();
        ArrayList<String> lessons = textFileReader(fname);
        if(lessons.size()>0) {
            for (String one : lessons) {
                String [] explode = one.split("!");
                if(explode.length == 4){
                    for(Group group : groupsAll) {
                        lessonsAll.add(
                                new Lesson(
                                        Date.parse(explode[1].trim()),
                                        explode[0].trim(),
                                        Short.parseShort(explode[2].trim()),
                                        explode[3].trim(),
                                        getModifiedGroup(group)
                                )
                        );
                    }
                }
                else{
                    System.out.println("Ошибка в файле ["+fname+":"+ explode.length +"]: " + one);
                }
            }
        }
    }

    private void readStudents(String fname){
        groupsAll = new ArrayList<>();
        ArrayList<String> students = textFileReader(fname);
        if(students.size()>0) {
            short num = 0;
            int groupcounter = 0;
            Group newgroup = null;
            for (String one : students) {
                if(num % MAX_GROUP_SIZE == 0){
                    if(newgroup != null){
                        groupsAll.add(newgroup);
                    }
                    newgroup = new Group(++groupcounter, "Группа №" + groupcounter);
                }
                String [] explode = one.split(" ");
                if(explode.length == 4){
                    newgroup.addStudent(
                            new Student(
                                    num++,
                                    explode[0].trim(),
                                    explode[1].trim(),
                                    explode[2].trim(),
                                    Date.parse(explode[3].trim())
                            )
                    );
                }
                else{
                    System.out.println("Ошибка в файле ["+fname+"]: " + one);
                }
            }
        }
    }

    public ArrayList<Lesson> getLessons() {
        return this.lessonsAll;
    }

    public ArrayList<String> textFileReader(String fname){
        ArrayList<String> result = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(fname);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
            )
        {
            String temp;
            while((temp = bufferedReader.readLine()) != null) {
                result.add(temp.trim());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
