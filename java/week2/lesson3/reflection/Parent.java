package week2.lesson3.reflection;

public class Parent extends GrandFather {
    private int privateInt;
    public int publicInt;
    protected int protectedInt;

    private String prvateFunction(){
        return "private";
    }
    public String publicFunction(){
        return "public";
    }
    protected String protectedFunction(){
        return "protected";
    }

}
