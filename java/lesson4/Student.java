package lesson4;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private short num;
    private String firstName;
    private String secondName;
    private String familyName;
    private final long bdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (getNum() != student.getNum()) return false;
        return getBdate() == student.getBdate();
    }

    @Override
    public int hashCode() {
        int result = (int) getNum();
        result = 31 * result + (int)getBdate();
        return result;
    }

    public Student(short num, String fName, String sName,
                   String familyName, long bdate) {
        this.num = num;
        this.firstName = fName;
        this.secondName = sName;
        this.familyName = familyName;
        this.bdate = bdate;
    }

    public short getNum() { return num; }

    public void setNum(short num) {
        this.num = num;
    }

    public long getBdate() {
        return bdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}