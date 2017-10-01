package lesson4ex;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by vidok on 21.09.17.
 */
public class Student implements Serializable {
    private short num;
    private String firstName;
    private String secondName;
    private String familyName;
    private boolean present;
    private final long bdate;

    public Student(){
        bdate = 0;
    }

    public Student(short num, String fName, String sName,
                   String familyName, long bdate) {
        this.num = num;
        this.firstName = fName;
        this.secondName = sName;
        this.familyName = familyName;
        this.bdate = bdate;
        /**
         * @ по умолчанию присутствовал
         */
        this.present = true;
    }

    public Student(Student student){
        this(
                student.getNum(),
                student.getFirstName(),
                student.getSecondName(),
                student.getFamilyName(),
                student.getBdate()
            );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return bdate == student.bdate;
    }

    @Override
    public int hashCode() {
        return (int) (bdate ^ (bdate >>> 32));
    }

    public short getNum() {
        return num;
    }

    public void setNum(short num) {
        this.num = num;
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

    public long getBdate() {
        return bdate;
    }

    public boolean getPresent() { return this.present; }

    public void setPresent(boolean present) { this.present = present; }

    private int getAge() {
        Date date = new Date(bdate);
        Calendar calendarStudent = Calendar.getInstance();
        Calendar calendarCurrent = Calendar.getInstance();
        calendarStudent.setTime(date);
        calendarCurrent.setTime(new Date());
        return calendarCurrent.get(Calendar.YEAR) - calendarStudent.get(Calendar.YEAR);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        /**
         * @ не пишем в отчет студентов младше 18 лет
         */
        if(getAge() >= 18) {
            out.writeObject(this);
        }
    }

    private void readObject(java.io.ObjectInputStream inp) throws IOException, ClassNotFoundException {
        /**
         * @ не читаем если старше 60 лет
         */
        if(getAge() <= 60) {
            inp.readObject();
        }
    }
}
