package week2.lesson3.reflection;

public class Child extends Parent{
    private Long privateLong;
    public Long publicLong;
    protected Long protectedLong;

    private void prvateProcedure(int iValue){
        System.out.println("prvateProcedure");
    }
    public void publicProcedure(String sValue){

    }
    protected void protectedProcedure(int iValue, String sValue){

    }

    @Override
    public String publicFunction() {
        return super.publicFunction();
    }

    @Override
    protected String protectedFunction() {
        return super.protectedFunction();
    }
}