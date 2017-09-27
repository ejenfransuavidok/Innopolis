public abstract class lesson_01_3
{
    public int getNum()
    {
        return 45;
    }
    public abstract class Bar
    {
        public int getNum()
        {
            return 38;
        }
    }
    public static void main (String [] args)
    {
        lesson_01_3 t;
        t = new lesson_01_3()
        {
            public int getNum()
            {
                return 22;
            }
        };
        lesson_01_3.Bar f = t.new Bar()
        {
            public int getNum()
            {
                return 57;
            }
        };

        System.out.println(f.getNum() + " " + t.getNum());
    }
}