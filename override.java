 class override
{

    public void add()
    {
        int a=10;
        int b=20;
        System.out.println(a+b);
    }
}

class overload extends override
{
    public void add()
    {
        int a=30;
        int b=40;
        System.out.println(a+b);
    }

    public static void main(String args[])
    {
        System.out.println("Test");
        overload t=new overload();
        t.add();
    }
}