class test 
{
    public void add() {
        int a,b;
        a=10;
        b=20;
        System.out.println(a+b);
    }
    public void add(int a)
    {
        int b=20;
        System.out.println(a+b);
    }
}
public class overload 
{
    public static void main(String args[])
    {
        test test=new test();
        test.add();
        test.add(0);
    }
}