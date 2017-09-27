package lesson3;

import java.io.*;

/**
 * Created by vidok on 21.09.17.
 */
public class Student implements Serializable {

    private String name;
    private Integer age;

    public Student(String n, Integer a){
        name = n;
        age = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
